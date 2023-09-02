package agents.domain.entity

import java.time.Instant
import java.util.UUID

case class Agent (
  id: UUID,
  identificationCode: Option[String],
  name: String,
  lastName: Option[String],
  phone: Option[String],
  email: String
)
