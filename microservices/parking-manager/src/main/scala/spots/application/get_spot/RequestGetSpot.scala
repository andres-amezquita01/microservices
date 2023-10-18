package spots.application.get_spot

case class RequestGetSpot (
  id: Option[String],
  location_x: Option[String],
  location_y: Option[String]
)
