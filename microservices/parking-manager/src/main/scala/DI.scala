import authentications.domain.service.JwtService
import authentications.infrastructure.service.JwtServiceImpl
import authentications.infrastructure.repository.AuthenticationRepositoryImpl
import authentications.domain.repository.AuthenticationRepository
import agents.infrastructure.repository.AgentRepositoryImpl
import agents.domain.repository.AgentRepository
import authentications.domain.service.HashService
import authentications.infrastructure.service.HashServiceImpl
import authorizations.infrastructure.repository.AuthorizationRepositoryImpl
import authorizations.domain.repository.AuthorizationRepository
import roles.domain.repository._
import roles.infrastructure.repository._
import permissions.domain.repository.PermissionRepository
import permissions.infrastructure.repository.PermissionRepositoryImpl
import traces.domain.service.LoggingTraceService
import traces.infrastructure.service.LoggingTraceServiceImpl

trait DI:
  given jwtService: JwtService = new JwtServiceImpl()
  given roleRepository: RoleRepository = new RoleRepositoryImpl()
  given userRoleRepository: UserRoleRepository = new UserRoleRepositoryImpl()
  given permissionRepository: PermissionRepository = new PermissionRepositoryImpl()
  given authenticationRepository: AuthenticationRepository = new AuthenticationRepositoryImpl()
  given authorizationRepository: AuthorizationRepository = new AuthorizationRepositoryImpl()
  given agentRepository: AgentRepository = new AgentRepositoryImpl()
  given hashService: HashService = new HashServiceImpl()
  given loggingTraceService: LoggingTraceService = LoggingTraceServiceImpl
