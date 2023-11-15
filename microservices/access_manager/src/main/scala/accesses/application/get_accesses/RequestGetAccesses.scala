package accesses.application.get_access

import shared.requests.PaginatedRequest

case class RequestGetAccesses (
  page: Int,
  perPage: Int
) extends PaginatedRequest(page, perPage)
