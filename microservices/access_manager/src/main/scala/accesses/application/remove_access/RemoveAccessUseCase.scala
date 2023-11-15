package accesses.application.remove_access

import shared.application.BaseUseCase
import zio._
import accesses.domain.repository.AccessRepository
import java.util.UUID

class RemoveAccessUseCase(
  using accessRepository: AccessRepository
) extends BaseUseCase[RequestRemoveAccess, ResponseRemoveAccess]:

  override def execute(request: RequestRemoveAccess): Task[ResponseRemoveAccess] = 
    ZIO.succeed {
      accessRepository.removeAccess(
        UUID.fromString(request.ID)
      )
    }.map(ResponseRemoveAccess.apply)
