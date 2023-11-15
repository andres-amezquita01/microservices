package accesses.application.get_access

import shared.application.BaseUseCase
import zio._
import accesses.domain.repository.AccessRepository
import java.util.UUID

class GetAccessUseCase(
  using accessRepository: AccessRepository
) extends BaseUseCase[RequestGetAccess, ResponseGetAccess]:

  override def execute(request: RequestGetAccess): Task[ResponseGetAccess] =
    ZIO.fromOption(request match {
      case RequestGetAccess(Some(ID), _, _) => accessRepository.getAccess(UUID.fromString(ID))
      case RequestGetAccess(_, Some(FloorID), _) => accessRepository.getAccessByFloorID(FloorID)
      case RequestGetAccess(_, _, Some(Name)) => accessRepository.getAccessByName(Name)
      case RequestGetAccess(_, _, _, Some(Name)) => accessRepository.getAccessByName(Name)
      case RequestGetAccess(_, _, _, _, Some(Name)) => accessRepository.getAccessByName(Name)
      case RequestGetAccess(_, _, _, _, _, Some(Name)) => accessRepository.getAccessByName(Name)
      case _ => None
    }).mapError(_ => new Throwable("Can't find Access"))
      .map(ResponseGetAccess(_))
