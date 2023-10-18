package spots.application.get_spots

import shared.requests.PaginatedRequest

case class RequestGetAgents (
  page: Int,
  perPage: Int
) extends PaginatedRequest(page, perPage)
