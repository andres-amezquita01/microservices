package accesses.application.get_accesses

import accesses.domain.entity.Access
import shared.responses.PaginatedResponse
import shared.responses.Meta

sealed class ResponseGetAccesses(
  data: List[Access],
  request: RequestGetAccesses,
  total: Long
) extends PaginatedResponse[Access](
  data,
  Meta(
    currentPage = request.page,
    lastPage = request.getLastPage(total),
    from = request.from,
    to = request.getTo(total),
    total = total
  )
)
