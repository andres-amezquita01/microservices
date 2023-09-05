package traces.application.save_trace

import zio.Task
import traces.domain.repository.LoggingTraceRepository
import traces.domain.entity.LoggingTrace
import zio.json._
import zio.ZIO

class SaveTraceUseCase()(
 using loggingTraceRepository: LoggingTraceRepository 
) {

  def execute(
    encodedTrace: String
  ): Task[String] = 
    for 
      trace <- ZIO.fromEither(encodedTrace.fromJson[LoggingTrace])
        .mapError(_ => Throwable("Can't deserialize logging trace from Json"))
      response <- loggingTraceRepository.saveLoggingTrace(trace)
      _ <- ZIO.logInfo("DB id: " + response)
    yield(response)
}
