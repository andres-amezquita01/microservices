package traces.infrastructure.controller

import traces.domain.repository.LoggingTraceRepository
import zio.stream.ZStream
import zio._
import zio.kafka.consumer.*
import scala.util.Properties
import zio.kafka.serde.Serde
import traces.application.save_trace.SaveTraceUseCase
import org.apache.kafka.common.KafkaException

class LoggingTraceController()(
  using loggingTraceRepository: LoggingTraceRepository
){
  private lazy val saveTraceUseCase = SaveTraceUseCase()

  val consumer: ZStream[Consumer, Throwable, Nothing] =
    Consumer
      .plainStream(Subscription.topics(LoggingTraceController.QUEUE_TOPIC), Serde.long, Serde.string)
      .tap(
        record => 
          for 
            savedResponse <- saveTraceUseCase.execute(record.value)
            _ <- Console.printLine(s"Received - Key: ${record.key} Value ${record.value}" )
          yield(savedResponse)
      )
      .map(_.offset)
      .aggregateAsync(Consumer.offsetBatches)
      .mapZIO(_.commit)
      .drain
}

object LoggingTraceController{
  private val QUEUE_TOPIC = Properties.envOrElse("TOPIC_NAME", "logtrace")
  private val KAFKA_URL = Properties.envOrElse("KAFKA_URL", "localhost")
  private val KAFKA_PORT = Properties.envOrElse("KAFKA_PORT", "29092")
  private val BOOSTRAP_SERVERS = List( KAFKA_URL + ":" + KAFKA_PORT )

  def consumerLayer = ZLayer.scoped(
      Consumer.make(
        ConsumerSettings(BOOSTRAP_SERVERS).withGroupId("group")
      )
    ).foldLayer(
      (error:Throwable) => {
        for
          _ <- ZLayer.fromZIO(ZIO.logWarning(s"Can't connect to Kafka topic: ${QUEUE_TOPIC}, Url: ${KAFKA_URL}, Port: ${KAFKA_PORT}"))
          failedLayer <- ZLayer.fail(error)
        yield(failedLayer)
      },
      layer => {
        for 
          _ <- ZLayer.fromZIO(ZIO.log("Connected to Kafka"))
          layer <- ZLayer.succeedEnvironment(layer)
        yield(layer)
      }
    ).retry(Schedule.fixed(10.seconds))
}
