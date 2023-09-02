package agents.application.get_client

import shared.application.BaseUseCase
import zio._
import agents.domain.repository.AgentRepository
import java.util.UUID

class GetClientUseCase (
  using agentRepository:AgentRepository
) extends BaseUseCase[RequestGetClient, ResponseGetClient]:

  override def execute(request: RequestGetClient): Task[ResponseGetClient] =
    ZIO.fromOption(request match {
      case RequestGetClient(Some(id), _, _) => agentRepository.getAgent(UUID.fromString(id))
      case RequestGetClient(_, Some(identificationCode), _) => agentRepository.getAgentByIdentificationCode(identificationCode)
      case RequestGetClient(_, _, Some(email)) => agentRepository.getAgentByEmail(email)
      case _ => None
    }).mapError(_ => new Throwable("Can't find Agent"))
      .map(ResponseGetClient(_))
