package agents.domain.repository

import agents.domain.entity.Agent
import java.util.UUID

trait AgentRepository:
  def getAgent(id: UUID): Option[Agent]
  def getAgentByEmail(email: String): Option[Agent]
  def getAgentByIdentificationCode(identificationCode: String): Option[Agent]
  def getAgents(from: Int, to: Int): List[Agent]
  def getTotalAmountOfAgents():Long
  def insertAgent(agent:Agent): Agent
  def updateAgent(agent:Agent): Agent
  def removeAgent(id: UUID): Agent
