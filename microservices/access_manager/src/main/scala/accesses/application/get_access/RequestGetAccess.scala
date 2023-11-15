package accesses.application.get_access

import java.util.UUID


case class RequestGetAccess(
  ID: Option[String],
  FloorID: Option[Int],
  BuildingID: Option[Int],
)
