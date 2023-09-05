import zio.kafka.consumer.Consumer
import zio.stream.ZStream
import zio.kafka.consumer.Subscription
import zio.kafka.consumer.ConsumerSettings
import zio.*
import traces.infrastructure.controller.LoggingTraceController

object Main extends ZIOAppDefault with DI {
  lazy val consumerController = LoggingTraceController()

  override def run =
   consumerController
      .consumer
      .runDrain
      .provide(LoggingTraceController.consumerLayer)
}
