package authentications.application.get_users

import shared.application.BaseUseCase
import zio._
import authentications.domain.repository.AuthenticationRepository

class GetUsersUseCase
(using authenticationRepository: AuthenticationRepository)
extends BaseUseCase[RequestGetUsers, ResponseGetUsers]:

  override def execute(request: RequestGetUsers): Task[ResponseGetUsers] =
    for
      users <- ZIO.attempt(authenticationRepository.getUsers(request.from, request.to))
      total <- ZIO.attempt(authenticationRepository.getTotalAmountOfUsers())
    yield(
      ResponseGetUsers(
        data = users.map(UserAgentInformation.apply),
        request = request,
        total = total)
    )
