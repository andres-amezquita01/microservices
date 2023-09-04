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

object LoggingTraceServiceImpl extends LoggingTraceService:

  private val QUEUE_TOPIC = "logging"
  private val BOOSTRAP_SERVERS = List(
    Properties.envOrElse("KAFKA_URL", "localhost") + ":" + Properties.envOrElse("KAFKA_PORT", "9092")
  )

  override val loggingLayer:ZLayer[Any, Throwable, Producer] = 
    ZLayer.scoped(
      Producer.make(
        ProducerSettings(BOOSTRAP_SERVERS)
      )
    )

  override def logSystemAction(systemAction: SystemAction): Task[RecordMetadata] = 
    for
      sendMetaData <- sendLogginTraceSystemAction(systemAction)
      _ <- ZIO.logInfo(sendMetaData.toString)
    yield (sendMetaData)

  private def sendLogginTraceSystemAction(systemAction: SystemAction): Task[RecordMetadata] = ZIO.attempt {
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
  }.flatMap { loggingTrace => 
    Producer.produce[Any, Long, String](
      topic = QUEUE_TOPIC,
      key = Random.nextLong,
      value = loggingTrace.toString,
      keySerializer = Serde.long,
      valueSerializer = Serde.string
    )
  }.provideLayer(loggingLayer)

