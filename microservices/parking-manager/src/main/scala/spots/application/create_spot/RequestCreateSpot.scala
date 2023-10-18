package spots.application.create_spot

case class RequestCreateSpot (
   floor_id: Option[String],
   name: String,
   location_x: Option[String],
   location_y: Option[String],
   spot_type_id: String
)
