package agents.application.get_agents

import shared.requests.PaginatedRequest

case class RequestGetAgents (
  page: Int,
  perPage: Int
) extends PaginatedRequest(page, perPage)
