package agents.application.remove_agent

import shared.application.BaseUseCase
import zio._
import agents.domain.repository.AgentRepository
import java.util.UUID

class RemoveAgentUseCase (
  using agentRepository:AgentRepository
) extends BaseUseCase[RequestRemoveAgent, ResponseRemoveAgent]:

  override def execute(request: RequestRemoveAgent): Task[ResponseRemoveAgent] = 
    ZIO.succeed {
      agentRepository.removeAgent(
        UUID.fromString(request.id)
      )
    }.map(ResponseRemoveAgent.apply)
