package traces.domain.entity

import zio.json._
import java.time.Instant

sealed case class LoggingTrace(
  val time: String,
  val owner: String,
  val ownerId: String,
  val action: String,
  val entity: Option[String],
  val information: Option[String]
)

object LoggingTrace {
  implicit val decoder: JsonDecoder[LoggingTrace] = DeriveJsonDecoder.gen[LoggingTrace]
}
