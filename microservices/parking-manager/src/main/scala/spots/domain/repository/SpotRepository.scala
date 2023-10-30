package agents.domain.repository

import spots.domain.entity.Spot
import java.util.UUID

trait SpotRepository:
  def getSpot(id: UUID): Option[Spot]
  def insertSpot(spot:Spot): Spot
  def updateSpot(spot:Spot): Spot
  def removeSpot(id: UUID): Spot
