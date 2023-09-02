package authentications.application.get_users

import authentications.domain.entity.User
import shared.responses._
import agents.domain.entity.Agent

sealed class ResponseGetUsers (
  data: List[UserAgentInformation],
  request: RequestGetUsers,
  total: Long
) extends PaginatedResponse[UserAgentInformation](
  data,
  Meta(
    currentPage = request.page,
    lastPage = request.getLastPage(total),
    from = request.from,
    to = request.getTo(total),
    total = total
  )
)

case class UserAgentInformation(
  user: User,
  agent: Agent
)
