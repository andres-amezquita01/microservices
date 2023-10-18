import authentications.domain.service.JwtService
import authentications.infrastructure.service.JwtServiceImpl
import authentications.infrastructure.repository.AuthenticationRepositoryImpl
import authentications.domain.repository.AuthenticationRepository
import spots.infrastructure.repository.SpotRepositoryImpl
import spots.domain.repository.SpotRepository
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
  given spotRepository: SpotRepository = new SpotRepositoryImpl()
  given hashService: HashService = new HashServiceImpl()
 
