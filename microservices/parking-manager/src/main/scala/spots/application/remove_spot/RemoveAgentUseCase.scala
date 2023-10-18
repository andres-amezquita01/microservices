package spots.application.remove_spot

import shared.application.BaseUseCase
import zio._
import spots.domain.repository.SpotRepository
import java.util.UUID

class RemoveSpotUseCase (
  using spotRepository:SpotRepository
) extends BaseUseCase[RequestRemoveSpot, ResponseRemoveSpot]:

  override def execute(request: RequestRemoveSpot): Task[ResponseRemoveSpot] = 
    ZIO.succeed {
      SpotRepository.removeSpot(
        UUID.fromString(request.id)
      )
    }.map(ResponseRemoveSpot.apply)
