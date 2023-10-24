package traces.infrastructure.service

import traces.domain.service.*
import traces.domain.entity.*
import zio.kafka.producer.{Producer, ProducerSettings}
import scala.util.Properties
import org.apache.kafka.clients.producer.RecordMetadata
import zio.kafka.serde.Serde
import traces.domain.entity.SystemAction.*
import java.time.LocalDateTime
import java.util.UUID
import scala.util.Random
import zio.*
import io.circe.syntax._
import java.util.concurrent.TimeUnit
import io.circe.Encoder
import io.circe.generic.semiauto._

object LoggingTraceServiceImpl extends LoggingTraceService:

  private val QUEUE_TOPIC = Properties.envOrElse("TOPIC_NAME", "logtrace")
  private val ERROR_SENDING = "The message can't be send to the queue"
  private val BOOSTRAP_SERVERS = List(
    Properties.envOrElse("KAFKA_URL", "localhost") + ":" + Properties.envOrElse("KAFKA_PORT", "29092")
  )

  implicit val loggingTraceEncoder: Encoder[LoggingTrace] = deriveEncoder[LoggingTrace]

  override val loggingLayer:ZLayer[Any, Throwable, Producer] = 
    ZLayer.scoped(
      Producer.make(
        ProducerSettings(BOOSTRAP_SERVERS)
      )
    )

  override def logSystemAction(systemAction: SystemAction): Task[String]  = 
    for
      sendMetaData <- sendLogginTraceSystemAction(systemAction)
        .provide(loggingLayer)
        .timeout(2.seconds)
        .map(d => d.toString)
        .orElse(ZIO.succeed("Can't send data to logtracer") )
      _ <- ZIO.logInfo(sendMetaData)
    yield (sendMetaData)

  private def sendLogginTraceSystemAction(systemAction: SystemAction): RIO[Producer, RecordMetadata] = 
    for 
      loggingTrace <- ZIO.attempt {
        systemAction match
        case Login(_, _) | SignUp(_, _) => ZIO.succeed(LoggingTrace(
          time = LocalDateTime.now().toString,
          owner = systemAction.userName,
          ownerId = systemAction.userId,
          action = systemAction.actionId,
          entity = None,
          information = None
        ))
        case DataModification(userName, userId, newDataJson, modificatedEntity) => ZIO.succeed(LoggingTrace(
          time = LocalDateTime.now().toString,
          owner = systemAction.userName,
          ownerId = systemAction.userId,
          action = systemAction.actionId,
          entity = Some(modificatedEntity),
          information = Some(newDataJson)
        )) 
        case null => ZIO.fail(Throwable("Invalid SystemAction"))
      }.flatten
      result <- Producer.produce[Any, Long, String](
        topic = QUEUE_TOPIC,
        key = Random.nextLong,
        value = loggingTrace.asJson.toString,
        keySerializer = Serde.long,
        valueSerializer = Serde.string
      )
    yield(result)
