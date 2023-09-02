package agents.application.update_client

import shared.application.BaseUseCase
import zio._
import agents.domain.repository.AgentRepository
import agents.domain.entity.Agent
import java.util.UUID

class UpdateClientUseCase(agentId: String) (
  using agentRepository:AgentRepository
) extends BaseUseCase[RequestUpdateClient, ResponseUpdateClient]:

  override def execute(request: RequestUpdateClient): Task[ResponseUpdateClient] = 
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
    }.map(ResponseUpdateClient(_))
