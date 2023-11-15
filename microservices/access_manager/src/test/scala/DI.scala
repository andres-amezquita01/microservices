import authentications.infrastructure.controller.AuthenticationController
import authentications.domain.service.JwtService
import authentications.infrastructure.service.JwtServiceImpl
import authentications.domain.service.HashService
import authentications.infrastructure.service.HashServiceImpl
import authentications.domain.repository.AuthenticationRepository
import authentications.AuthenticationMockedRepository
import authorizations.domain.repository.AuthorizationRepository
import agents.*
import agents.domain.repository.AgentRepository
import authorizations.AuthorizationMockedRepository

trait DI{
  given jwtService: JwtService = JwtServiceImpl()
  given hashService: HashService = HashServiceImpl()
  given authenticationRepository: AuthenticationRepository = AuthenticationMockedRepository()
  given authorizationRepository: AuthorizationRepository = AuthorizationMockedRepository()
  given agentRepository: AgentRepository = AgentMockedRepository()
}
