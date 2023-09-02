package agents.application.create_client

case class RequestCreateClient (
  identificationCode: String, 
  name: String,
  lastName: Option[String],
  phone: Option[String],
  email: String
)
