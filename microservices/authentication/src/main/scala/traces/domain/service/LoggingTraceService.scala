package traces.domain.service

import traces.domain.entity.LoggingTrace
import traces.domain.entity.SystemAction
import zio.*
import zio.kafka.producer.Producer
import org.apache.kafka.clients.producer.RecordMetadata

trait LoggingTraceService:
  val loggingLayer: ZLayer[_, _, _] 
  def logSystemAction(systemAction: SystemAction): Task[String]
