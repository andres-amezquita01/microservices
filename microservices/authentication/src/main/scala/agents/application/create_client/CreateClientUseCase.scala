package agents.application.create_client

import shared.application.BaseUseCase
import zio._
import agents.domain.repository.AgentRepository
import agents.domain.entity.Agent

class CreateClientUseCase
(using agentRepository:AgentRepository)
extends BaseUseCase[RequestCreateClient, ResponseCreateClient]:

  private val EMPTY_AGENT_ID = ""

  override def execute(request: RequestCreateClient): Task[ResponseCreateClient] = 
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
    }.map(ResponseCreateClient.apply)
