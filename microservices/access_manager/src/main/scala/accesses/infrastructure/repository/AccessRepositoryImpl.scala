package accesses.infrastructure.repository

import accesses.domain.repository.AccessRepository
import accesses.domain.entity.Access
import io.getquill._
import shared.BaseRepository
import java.util.UUID

class AccessRepositoryImpl extends AccessRepository with BaseRepository:

  import ctx._

  override def getAccesses(from: Int, to: Int): List[Access] =
    ctx.run(
      query[Access]
        .sortBy(_.name)(Ord.ascNullsLast)
        .take(lift(to))
        .drop(lift(from))
    )

  override def removeAccess(id: UUID): Access =
    ctx.run(
      query[Access]
        .filter(_.id == lift(id))
        .delete
        .returning(r => r)
    )

  override def updateAccess(access: Access): Access = 
    ctx.run(
      query[Access]
        .filter(_.id == lift(access.id))
        .updateValue(lift(access))
        .returning(r => r)
    )

  override def insertAccess(access: Access): Access = 
    ctx.run(
      query[Access]
        .insertValue(lift(access))
        .returning(r => r)
    )

  override def getAccess(id: UUID): Option[Access] = 
    ctx.run(
      query[Access]
      .filter(_.id == lift(id))
    ).headOption

  override def getAccessByName(name: String): Option[Access] = 
    ctx.run(
      query[Access]
      .filter(_.name == lift(name))
    ).headOption

  override def getAccessByFloorId(floorId: String): Option[Access] = 
    ctx.run(
      query[Access]
      .filter(_.floorId.contains(lift(floorId)))
    ).headOption

  override def getTotalAmountOfAccesses(): Long = 
    ctx.run(
      query[Access].size
    )
