import traces.domain.repository.LoggingTraceRepository
import traces.infrastructure.repository.LoggingTraceRepositoryImpl

trait DI:
  given loggingTraceRepository: LoggingTraceRepository = LoggingTraceRepositoryImpl()
