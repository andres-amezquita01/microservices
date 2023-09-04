import zio._
import zio.kafka.consumer.Consumer
import zio.stream.ZStream
import zio.kafka.serde.Serde
import zio.kafka.consumer.Subscription
import zio.kafka.consumer.ConsumerSettings
import scala.util.Properties

object Main extends ZIOAppDefault: 

  private val QUEUE_TOPIC = Properties.envOrElse("TOPIC_NAME", "logtrace")
  private val BOOSTRAP_SERVERS = List(
    Properties.envOrElse("KAFKA_URL", "localhost") + ":" + Properties.envOrElse("KAFKA_PORT", "29092")
  )

  val consumer: ZStream[Consumer, Throwable, Nothing] =
    Consumer
      .plainStream(Subscription.topics(QUEUE_TOPIC), Serde.long, Serde.string)
      .tap(r => Console.printLine(r.value))
      .map(_.offset)
      .aggregateAsync(Consumer.offsetBatches)
      .mapZIO(_.commit)
      .drain

  def consumerLayer =
    ZLayer.scoped(
      Consumer.make(
        ConsumerSettings(BOOSTRAP_SERVERS).withGroupId("group")
      )
    )

  override def run =
    consumer
      .runDrain
      .provide(consumerLayer)
