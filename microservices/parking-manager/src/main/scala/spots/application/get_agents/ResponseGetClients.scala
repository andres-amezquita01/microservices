package agents.application.get_agents

import agents.domain.entity.Agent
import shared.responses.PaginatedResponse
import shared.responses.Meta

sealed class ResponseGetAgents (
  data:List[Agent],
  request: RequestGetAgents,
  total: Long
) extends PaginatedResponse[Agent](
  data,
  Meta(
    currentPage = request.page,
    lastPage= request.getLastPage(total),
    from =  request.from,
    to = request.getTo(total),
    total = total
  )
)
