package accesses.application.get_access

import shared.application.BaseUseCase
import zio._
import accesses.domain.repository.AccessRepository

class GetAccessesUseCase(
  using accessRepository: AccessRepository
) extends BaseUseCase[RequestGetAccesses, ResponseGetAccesses]:

  override def execute(request: RequestGetAccesses): Task[ResponseGetAccesses] = 
    ZIO.succeed {
      for
        data <- ZIO.succeed(accessRepository.getAccesses(request.from, request.to))
        total <- ZIO.succeed(accessRepository.getTotalAmountOfAccesses())
      yield (ResponseGetAccesses(data, request, total))
    }.flatten
