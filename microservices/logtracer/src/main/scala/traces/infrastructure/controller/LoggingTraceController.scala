package traces.infrastructure.controller

import traces.domain.repository.LoggingTraceRepository
import zio.stream.ZStream
import zio._
import zio.kafka.consumer.*
import scala.util.Properties
import zio.kafka.serde.Serde
import traces.application.save_trace.SaveTraceUseCase

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
  private val BOOSTRAP_SERVERS = List(
    Properties.envOrElse("KAFKA_URL", "localhost") + ":" + Properties.envOrElse("KAFKA_PORT", "29092")
  )

  def consumerLayer =
    ZLayer.scoped(
      Consumer.make(
        ConsumerSettings(BOOSTRAP_SERVERS).withGroupId("group")
      )
    )
}
