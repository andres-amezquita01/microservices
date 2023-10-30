package spots.infrastructure.repository

import spots.domain.repository.SpotRepository
import spots.domain.entity.Spot
import io.getquill._
import shared.BaseRepository
import java.util.UUID

class SpotRepositoryImpl extends SpotRepository with BaseRepository:

  import ctx._

  override def getSpots(from: Int, to: Int): List[Spot] =
    ctx.run(
      query[Spot]
        .sortBy(_.name)(Ord.ascNullsLast)
        .take(lift(to))
        .drop(lift(from))
    )

  override def removeSpot(id: UUID): Spot =
    ctx.run(
      query[Spot]
        .filter(_.id == lift(id))
        .delete
        .returning(r => r)
    )

  override def updateSpot(Spot: Spot): Spot = 
    ctx.run(
      query[Spot]
        .filter(_.id == lift(Spot.id))
        .updateValue(lift(Spot))
        .returning(r => r)
    )

  override def insertSpot(Spot: Spot): Spot = 
    ctx.run(
      query[Spot]
        .insertValue(lift(Spot))
        .returning(r => r)
    )

  override def getSpot(id: UUID): Option[Spot] = 
    ctx.run(
      query[Spot]
      .filter(_.id == lift(id))
    ).headOption

  override def getSpotByName(name: String): Option[Spot] = 
    ctx.run(
      query[Spot]
      .filter(_.name == lift(name))
    ).headOption

  override def getSpotByFloorId(FloorId: String): Option[Spot] = 
    ctx.run(
      query[Spot]
      .filter(_.FloorId.contains(lift(FloorId)))
    ).headOption

  override def getTotalAmountOfSpots():Long = 
    ctx.run(
      query[Spot].size
    )
