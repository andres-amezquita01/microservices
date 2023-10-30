package spots.application.get_spots

import spots.domain.entity.Spot
import shared.responses.PaginatedResponse
import shared.responses.Meta

sealed class ResponseGetSpots (
  data:List[Spot],
  request: RequestGetSpots,
  total: Long
) extends PaginatedResponse[Spot](
  data,
  Meta(
    currentPage = request.page,
    lastPage= request.getLastPage(total),
    from =  request.from,
    to = request.getTo(total),
    total = total
  )
)
