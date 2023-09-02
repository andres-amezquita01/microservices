package authentications.domain.entity

import java.util.UUID

case class UserContext(
  id: UUID,
  username: Option[String],
  agentId: UUID
)
