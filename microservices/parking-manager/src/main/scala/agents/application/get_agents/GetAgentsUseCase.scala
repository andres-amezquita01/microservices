package agents.application.get_agents

import shared.application.BaseUseCase
import zio._
import agents.domain.repository.AgentRepository

class GetAgentsUseCase (
  using agentRepository:AgentRepository
) extends BaseUseCase[RequestGetAgents, ResponseGetAgents]:

  override def execute(request: RequestGetAgents): Task[ResponseGetAgents] = 
    ZIO.succeed {
      for
        data <- ZIO.succeed(agentRepository.getAgents(request.from, request.to))
        total <- ZIO.succeed(agentRepository.getTotalAmountOfAgents())
      yield (ResponseGetAgents(data, request, total))
    }.flatten
