package agents.application.get_client

case class RequestGetClient (
  id: Option[String],
  identificationCode: Option[String],
  email: Option[String]
)
