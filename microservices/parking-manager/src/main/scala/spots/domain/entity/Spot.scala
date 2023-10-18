package agents.domain.entity

import java.time.Instant
import java.util.UUID

case class Spot(
   id: UUID,
   floor_id: Option[String],
   name: String,
   location_x: Option[String],
   location_y: Option[String],
   spot_type_id: String
)
