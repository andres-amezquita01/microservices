package agents.application.update_client

case class RequestUpdateClient (
  identificationCode: String, 
  name: String,
  lastName: Option[String],
  phone: Option[String],
  email: String
)
