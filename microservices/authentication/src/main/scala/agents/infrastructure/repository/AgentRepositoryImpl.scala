package agents.infrastructure.repository

import agents.domain.repository.AgentRepository
import agents.domain.entity.Agent
import io.getquill._
import shared.BaseRepository

class AgentRepositoryImpl extends AgentRepository with BaseRepository:

  import ctx._

  override def getAgents(from: Int, to: Int): List[Agent] =
    ctx.run(
      query[Agent]
        .sortBy(_.name)(Ord.ascNullsLast)
        .take(lift(to))
        .drop(lift(from))
    )

  override def removeAgent(id: String): Agent =
    ctx.run(
      query[Agent]
        .filter(_.id == lift(id))
        .delete
        .returning(r => r)
    )

  override def updateAgent(agent: Agent): Agent = 
    ctx.run(
      query[Agent]
        .filter(_.id == lift(agent.id))
        .updateValue(lift(agent))
        .returning(r => r)
    )

  override def insertAgent(agent: Agent): Agent = 
    ctx.run(
      query[Agent]
        .insertValue(lift(agent))
        .returning(r => r)
    )

  override def getAgent(id: String): Option[Agent] = 
    ctx.run(
      query[Agent]
      .filter(_.id == lift(id))
    ).headOption

  override def getAgentByEmail(email: String): Option[Agent] = 
    ctx.run(
      query[Agent]
      .filter(_.email == lift(email))
    ).headOption

  override def getAgentByIdentificationCode(identificationCode: String): Option[Agent] = 
    ctx.run(
      query[Agent]
      .filter(_.identificationCode.contains(lift(identificationCode)))
    ).headOption

  override def getTotalAmountOfAgents():Long = 
    ctx.run(
      query[Agent].size
    )
