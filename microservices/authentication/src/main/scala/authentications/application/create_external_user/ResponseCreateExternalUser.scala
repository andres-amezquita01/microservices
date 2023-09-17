package authentications.application.create_external_user

case class ResponseCreateExternalUser (
  username: String,
  agentId: String, 
  userId: String, 
  email: String,
  status: String = "User created successfully"
)
