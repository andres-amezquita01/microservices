package authentications.application.create_user

import shared.application.BaseUseCase
import authentications.domain.repository.AuthenticationRepository
import authentications.domain.entity.User
import agents.domain.repository.AgentRepository
import agents.domain.entity.Agent
import authentications.domain.service.HashService
import zio.ZIO
import java.util.UUID
import traces.domain.service.LoggingTraceService
import traces.domain.entity.SystemAction

class CreateUserUseCase() (using 
  authenticationRepository:AuthenticationRepository, 
  agentRepository: AgentRepository,
  hashService: HashService,
  loggingTraceService: LoggingTraceService
) extends BaseUseCase[RequestCreateUser,ResponseCreateUser]:

  private val EMPTY_ID = UUID.randomUUID()
  private val INTERNAL_AUTH_SOURCE = "internal"

  override def execute(request: RequestCreateUser) =

  for 
    encryptedPassword <- ZIO.fromOption(hashService.hashPassword(request.password))
      .mapError(_ => new Throwable("Invalid hash password generation"))

    agent <- ZIO.attempt(agentRepository.insertAgent(
      Agent(
        id = EMPTY_ID,
        identificationCode = request.identificationCode,
        name = request.name,
        lastName= request.lastName,
        phone = request.phone,
        email = request.email
      )
    ))

    user <- ZIO.attempt(authenticationRepository.createUser(
      User (
        id = EMPTY_ID,
        username = Some(request.userName),
        password = Some(encryptedPassword),
        description = request.description,
        source = INTERNAL_AUTH_SOURCE,
        agentId = agent.id,
        externalId = None
      )
    ))
    .logError
    .orElseFail(
      agentRepository.removeAgent(agent.id)
    )
    .mapError(removedAgent => new Throwable(s"Can't create user, reverted agent creation of ${removedAgent.id}"))

    _ <- loggingTraceService.logSystemAction( 
        SystemAction.SignUp(userName = user.username.getOrElse("Unknown"), userId = user.id.toString)
    )
  yield(
    ResponseCreateUser(username = user.username.get, agentId = agent.id.toString, userId = user.id.toString, email = agent.email)
  )
  end execute
