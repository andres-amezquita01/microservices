package traces.domain.entity

import java.time.Instant

sealed case class LoggingTrace(
  val time: String,
  val owner: String,
  val ownerId: String,
  val action: String,
  val entity: Option[String],
  val information: Option[String]
)
