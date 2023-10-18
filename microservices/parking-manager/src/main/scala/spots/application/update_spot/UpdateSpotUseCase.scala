package spots.application.remove_spot

import shared.application.BaseUseCase
import zio._
import spots.domain.repository.SpotRepository
import spots.domain.entity.Spot
import java.util.UUID

class UpdateSpotUseCase(spotId: String) (
  using spotRepository:SpotRepository
) extends BaseUseCase[RequestUpdateSpot, ResponseUpdateSpot]:

  override def execute(request: RequestUpdateSpot): Task[ResponseUpdateSpot] = 
    ZIO.succeed {
      spotRepository.updateSpot(
        Spot (
          id = UUID.fromString(spotId), 
          identificationCode = Some(request.identificationCode),
          name = request.name,
          lastName = request.lastName,
          phone = request.phone,
          email = request.email
        )
      ) 
    }.map(ResponseUpdateSpot(_))
