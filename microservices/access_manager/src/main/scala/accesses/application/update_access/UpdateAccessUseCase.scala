package accesses.application.update_access

import shared.application.BaseUseCase
import zio._
import accesses.domain.repository.AccessRepository
import accesses.domain.entity.Access
import java.util.UUID

class UpdateAccessUseCase(accessId: String)(
  using accessRepository: AccessRepository
) extends BaseUseCase[RequestUpdateAccess, ResponseUpdateAccess]:

  override def execute(request: RequestUpdateAccess): Task[ResponseUpdateAccess] = 
    ZIO.succeed {
      accessRepository.updateAccess(
        Access(
          ID = UUID.fromString(accessId), 
          CheckIn = request.checkIn,
          CheckOut = request.checkOut,
          Name = request.name,
          LastName = request.lastName,
          Phone = request.phone,
          Email = request.email,
        )
      ) 
    }.map(ResponseUpdateAccess(_))
