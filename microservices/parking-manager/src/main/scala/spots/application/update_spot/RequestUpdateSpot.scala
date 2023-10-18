package spots.application.update_spot

case class RequestUpdateSpot (
   floor_id: Option[String],
   name: String,
   location_x: Option[String],
   location_y: Option[String],
   spot_type_id: String
)
