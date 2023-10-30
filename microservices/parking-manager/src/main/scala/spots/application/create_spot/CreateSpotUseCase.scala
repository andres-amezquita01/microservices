package spots.application.create_spot

import shared.application.BaseUseCase
import zio._
import spots.domain.repository.SpotRepository
import spots.domain.entity.Spot
import java.util.UUID

class CreateSpotUseCase (
  using spotRepository:SpotRepository
) extends BaseUseCase[RequestCreateSpot, ResponseCreateSpot]:

  private val EMPTY_Spot_ID = UUID.randomUUID

  override def execute(request: RequestCreateSpot): Task[ResponseCreateSpot] = 
    ZIO.succeed {
      spotRepository.insertSpot(
        Spot (
          id = EMPTY_Spot_ID,
          floor_id = Some(request.floor_id),
          name = request.name,
          location_x = request.location_x,
          location_y = request.location_y,
          spot_type_id = request.spot_type_id
        )
      )
    }.map(ResponseCreateSpot.apply)
