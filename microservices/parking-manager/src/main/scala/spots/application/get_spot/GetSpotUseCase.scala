package spots.application.get_spot

import shared.application.BaseUseCase
import zio._
import spots.domain.repository.SpotRepository
import java.util.UUID

class GetSpottUseCase (
  using spotRepository:SpotRepository
) extends BaseUseCase[RequestGetSpot, ResponseGetSpot]:

  override def execute(request: RequestGetSpot): Task[ResponseGetSpot] =
    ZIO.fromOption(request match {
      case RequestGetSpot(Some(id), _, _) => SpotRepository.getSpot(UUID.fromString(id))
      case RequestGetSpot(_, Some(floor_id), _) => SpotRepository.getSpotByFloorID(floor_id)
      case RequestGetSpot(_, _, Some(name)) => SpotRepository.getSpotByName(name)
      case RequestGetSpot(_, _, _, Some(name)) => SpotRepository.getSpotByName(name)
      case RequestGetSpot(_, _, _, _, Some(name)) => SpotRepository.getSpotByName(name)
      case RequestGetSpot(_, _, _, _, _, Some(name)) => SpotRepository.getSpotByName(name)
      case _ => None
    }).mapError(_ => new Throwable("Can't find Spot"))
      .map(ResponseGetSpot(_))
