package agents.application.update_agent

case class RequestUpdateAgent (
  identificationCode: String, 
  name: String,
  lastName: Option[String],
  phone: Option[String],
  email: String
)
