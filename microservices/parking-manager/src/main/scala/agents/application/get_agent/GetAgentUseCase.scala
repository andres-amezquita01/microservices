package agents.application.get_agent

import shared.application.BaseUseCase
import zio._
import agents.domain.repository.AgentRepository
import java.util.UUID

class GetAgentUseCase (
  using agentRepository:AgentRepository
) extends BaseUseCase[RequestGetAgent, ResponseGetAgent]:

  override def execute(request: RequestGetAgent): Task[ResponseGetAgent] =
    ZIO.fromOption(request match {
      case RequestGetAgent(Some(id), _, _) => agentRepository.getAgent(UUID.fromString(id))
      case RequestGetAgent(_, Some(identificationCode), _) => agentRepository.getAgentByIdentificationCode(identificationCode)
      case RequestGetAgent(_, _, Some(email)) => agentRepository.getAgentByEmail(email)
      case _ => None
    }).mapError(_ => new Throwable("Can't find Agent"))
      .map(ResponseGetAgent(_))
