package agents.application.create_agent

import shared.application.BaseUseCase
import zio._
import agents.domain.repository.AgentRepository
import agents.domain.entity.Agent
import java.util.UUID

class CreateAgentUseCase (
  using agentRepository:AgentRepository
) extends BaseUseCase[RequestCreateAgent, ResponseCreateAgent]:

  private val EMPTY_AGENT_ID = UUID.randomUUID

  override def execute(request: RequestCreateAgent): Task[ResponseCreateAgent] = 
    ZIO.succeed {
      agentRepository.insertAgent(
        Agent (
          id = EMPTY_AGENT_ID,
          identificationCode = Some(request.identificationCode),
          name = request.name,
          lastName = request.lastName,
          phone = request.phone,
          email = request.email
        )
      )
    }.map(ResponseCreateAgent.apply)
