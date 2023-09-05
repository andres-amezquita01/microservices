package traces.domain.repository

import traces.domain.entity.LoggingTrace
import zio.Task

trait LoggingTraceRepository {

  def saveLoggingTrace(trace: LoggingTrace): Task[String]
}
