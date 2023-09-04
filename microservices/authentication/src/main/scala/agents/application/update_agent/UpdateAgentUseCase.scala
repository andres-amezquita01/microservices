package agents.application.update_agent

import shared.application.BaseUseCase
import zio._
import agents.domain.repository.AgentRepository
import agents.domain.entity.Agent
import java.util.UUID

class UpdateAgentUseCase(agentId: String) (
  using agentRepository:AgentRepository
) extends BaseUseCase[RequestUpdateAgent, ResponseUpdateAgent]:

  override def execute(request: RequestUpdateAgent): Task[ResponseUpdateAgent] = 
    ZIO.succeed {
      agentRepository.updateAgent(
        Agent (
          id = UUID.fromString(agentId), 
          identificationCode = Some(request.identificationCode),
          name = request.name,
          lastName = request.lastName,
          phone = request.phone,
          email = request.email
        )
      ) 
    }.map(ResponseUpdateAgent(_))
