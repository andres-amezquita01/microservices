package authentications.application.get_session_information

import shared.application.BaseUseCase
import authentications.domain.service.JwtService
import agents.domain.repository.AgentRepository
import zio._
import authorizations.domain.repository.AuthorizationRepository
import java.util.UUID

class GetSessionInformationUseCase 
(using 
  jwtService: JwtService, 
  agentRepository:AgentRepository,
  authorizationRepository: AuthorizationRepository
)
extends BaseUseCase[RequestGetSessionInformation, ResponseGetSessionInformation]:

  override def execute(request: RequestGetSessionInformation): Task[ResponseGetSessionInformation] = 
    ZIO.succeed {
      for
        userAgent <- ZIO.fromOption(agentRepository.getAgent(request.user.agentId))
          .mapError(_ => Throwable(s"Not found agent of id ${request.user.agentId}"))
        roles <- ZIO.attempt(authorizationRepository.getRolesOfUser(request.user.id))
      yield (
        ResponseGetSessionInformation (
          name = userAgent.name,
          lastname = userAgent.lastName,
          username = request.user.username,
          permissions = request.permission.permissions,
          roles = roles
        )
      )
    }.flatten
