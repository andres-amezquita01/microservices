package agents.application.remove_client

import shared.application.BaseUseCase
import zio._
import agents.domain.repository.AgentRepository
import java.util.UUID

class RemoveClientUseCase 
(using agentRepository:AgentRepository)
extends BaseUseCase[RequestRemoveClient, ResponseRemoveClient]:

  override def execute(request: RequestRemoveClient): Task[ResponseRemoveClient] = 
    ZIO.succeed {
      agentRepository.removeAgent(
        UUID.fromString(request.id)
      )
    }.map(ResponseRemoveClient.apply)
