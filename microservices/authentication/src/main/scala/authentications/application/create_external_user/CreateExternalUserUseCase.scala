package authentications.application.create_external_user

import authentications.domain.repository.AuthenticationRepository
import agents.domain.repository.AgentRepository
import traces.domain.service.LoggingTraceService
import shared.application.BaseUseCase
import authentications.application.create_external_user.*
import zio.ZIO
import java.util.UUID
import agents.domain.entity.Agent
import authentications.domain.entity.User
import traces.domain.entity.SystemAction

class CreateExternalUserUseCase() (using 
  authenticationRepository:AuthenticationRepository, 
  agentRepository: AgentRepository,
  loggingTraceService: LoggingTraceService
) extends BaseUseCase[RequestCreateExternalUser,ResponseCreateExternalUser]:

  private val EMPTY_ID = UUID.randomUUID()

  override def execute(request: RequestCreateExternalUser) = for 
    agent <- ZIO.attempt(agentRepository.insertAgent(
      Agent(
        id = EMPTY_ID,
        identificationCode = None,
        name = request.name,
        lastName= request.lastName,
        phone = request.phone,
        email = request.email
      )
    ))

    user <- ZIO.attempt(authenticationRepository.createUser(
      User (
        id = EMPTY_ID,
        username = None,
        password = None,
        description = None,
        source = request.externalSource,
        agentId = agent.id,
        externalId = Some(request.externalId)
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
    ResponseCreateExternalUser(username = user.username.get, agentId = agent.id.toString, userId = user.id.toString, email = agent.email)
  )
  end execute
