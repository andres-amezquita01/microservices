package authentications.application.create_user

import authentications.domain.entity.User
import agents.domain.entity.Agent

case class ResponseCreateUser (
  username: String,
  agentId: String, 
  userId: String, 
  email: String,
  status: String = "User created successfully"
)
