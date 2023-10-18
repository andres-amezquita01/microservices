package authentications.application.create_external_user

case class RequestCreateExternalUser(
  name: String,
  lastName: Option[String],
  phone: Option[String],
  email: String,
  externalId: String,
  externalSource: String
)
