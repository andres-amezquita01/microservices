package authentications.domain.entity

case class UserContext(
  id: String,
  username: Option[String],
  agentId: String
)
