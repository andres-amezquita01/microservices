package agents.application.get_agent

case class RequestGetAgent (
  id: Option[String],
  identificationCode: Option[String],
  email: Option[String]
)
