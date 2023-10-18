package spots.application.get_spots

import shared.application.BaseUseCase
import zio._
import spots.domain.repository.SpotRepository

class GetspotsUseCase (
  using spotRepository:SpotRepository
) extends BaseUseCase[RequestGetspots, ResponseGetspots]:

  override def execute(request: RequestGetspots): Task[ResponseGetspots] = 
    ZIO.succeed {
      for
        data <- ZIO.succeed(spotRepository.getspots(request.from, request.to))
        total <- ZIO.succeed(spotRepository.getTotalAmountOfspots())
      yield (ResponseGetspots(data, request, total))
    }.flatten
