package agents.domain.entity

import java.time.Instant

case class Agent (
  id: String,
  identificationCode: Option[String],
  name: String,
  lastName: Option[String],
  phone: Option[String],
  email: String
)
