package authentications.application.create_user

case class RequestCreateUser (
  identificationCode: Option[String],
  name: String,
  lastName: Option[String],
  phone: Option[String],
  email: String,

  userName: String,
  password: String,
  description: Option[String],
)
