package accesses.application.create_access

case class RequestCreateAccess(
  checkIn: Option[Timestamp],
  checkOut: Option[Timestamp],
  name: String,
  lastName: String,
  phone: String,
  email: String,
  spotID: Int,
  spotName: String,
  spotFloorID: Int,
  spotLocationX: Option[Float],
  spotLocationY: Option[Float],
  spotTypeID: Int,
  spotTypeName: String,
  floorID: Int,
  floorName: String,
  buildingID: Int,
  buildingName: String
)
