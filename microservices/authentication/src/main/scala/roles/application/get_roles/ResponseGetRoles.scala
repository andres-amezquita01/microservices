package roles.application.get_roles

import roles.domain.entity.Role
import shared.responses._

sealed class ResponseGetRoles (
  data: List[Role],
  request: RequestGetRoles,
  total: Long
) extends PaginatedResponse[Role](
  data,
  Meta(
    currentPage = request.page,
    lastPage = request.getLastPage(total),
    from = request.from,
    to = request.getTo(total),
    total = total
  )
)
