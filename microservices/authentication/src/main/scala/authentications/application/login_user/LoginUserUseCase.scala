package authentications.application.login_user

import shared.application.BaseUseCase
import authentications.domain.service.JwtService
import authentications.domain.repository.AuthenticationRepository
import authentications.domain.service.HashService
import authentications.domain.entity.TokenInfo

import authorizations.domain.repository.AuthorizationRepository
import zio.ZIO

class LoginUserUseCase()
(using 
  jwtService: JwtService, 
  authenticationRepository:AuthenticationRepository,
  authorizationRepository: AuthorizationRepository,
  hashService: HashService
) extends BaseUseCase[RequestLoginUser, ResponseLoginUser]:

  override def execute(request: RequestLoginUser) = 
    for
      user <- ZIO.fromOption(authenticationRepository
        .getUserByUsername(request.usernameOrEmail)
        .orElse(
          authenticationRepository.getUserByEmail(request.usernameOrEmail)
        )
      ).mapError(_ => Throwable("User don't exists"))

      userPassword <- ZIO.fromOption(user.password)
        .mapError(_ => Throwable("This user is already registered with an external auth service"))

      userContext <- ZIO.attempt(
        hashService.areSamePassword(
          plainPassword = request.password,
          bcryptedPassword = userPassword
        )
      ).fold(
        { ZIO.fail(_) }, 
        { _ match{
            case true =>  ZIO.succeed(user.getUserContext())
            case _ => ZIO.fail(Throwable("Incorrect Password"))
          }
        }
      ).flatten

      permissionContext <- ZIO.attempt(authorizationRepository
        .getPermissionContextOfUser(userContext.id))

      (token, expirationTime) = jwtService.encodeUserInfo(
        TokenInfo(
          userContext,
          permissionContext
        )
      )

    yield(
      ResponseLoginUser(
        accessToken = token,
        expiresIn = expirationTime, 
      )
    )
  end execute
