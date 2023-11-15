package access.domain.entity

import java.time.Instant
import java.util.UUID

case class Access(
   id: Int,
   checkIn: Option[Timestamp],
   checkOut: Option[Timestamp],
   name: String,
   lastName: String,
   phone: String,
   email: String,
   spotId: Int,
   spotName: String,
   spotFloorId: Int,
   spotLocationX: Option[Float],
   spotLocationY: Option[Float],
   spotTypeId: Int,
   spotTypeName: String,
   floorId: Int,
   floorName: String,
   buildingId: Int,
   buildingName: String
)
