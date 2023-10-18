package authentications.domain.entity

import java.util.UUID

case class User (
  id: UUID,
  username: Option[String],
  password: Option[String],
  description: Option[String],
  source: String,
  agentId: UUID,
  externalId: Option[String]
){
  def getUserContext():UserContext =
    UserContext(
      id = id, 
      username = username, 
      agentId = agentId
    )
}
