package accesses.application.update_access

case class RequestUpdateAccess(
   FloorID: Option[String],
   Name: String,
   LocationX: Option[String],
   LocationY: Option[String],
   AccessTypeID: String
)
