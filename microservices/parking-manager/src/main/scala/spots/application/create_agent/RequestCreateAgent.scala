package agents.application.create_agent

case class RequestCreateAgent (
  identificationCode: String, 
  name: String,
  lastName: Option[String],
  phone: Option[String],
  email: String
)
