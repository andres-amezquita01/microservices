package authentications.domain.entity

case class User (
  id: String,
  username: Option[String],
  password: Option[String],
  description: Option[String],
  source: String,
  agentId: String,
  externalId: Option[String]
){
  def getUserContext():UserContext =
    UserContext(
      id = id, 
      username = username, 
      agentId = agentId
    )
}
