package accesses.application.create_access

import shared.application.BaseUseCase
import zio._
import accesses.domain.repository.AccessRepository
import accesses.domain.entity.Access
import java.util.UUID

class CreateAccessUseCase(
  using accessRepository: AccessRepository
) extends BaseUseCase[RequestCreateAccess, ResponseCreateAccess]:

  private val EMPTY_ACCESS_ID = UUID.randomUUID

  override def execute(request: RequestCreateAccess): Task[ResponseCreateAccess] = 
    ZIO.succeed {
      accessRepository.insertAccess(
        Access(
          ID = EMPTY_ACCESS_ID,
          CheckIn = request.checkIn,
          CheckOut = request.checkOut,
          Name = request.name,
          LastName = request.lastName,
          Phone = request.phone,
          Email = request.email,
          SpotID = request.spotID,
          SpotName = request.spotName,
          SpotFloorID = request.spotFloorID,
          SpotLocationX = request.spotLocationX,
          SpotLocationY = request.spotLocationY,
          SpotTypeID = request.spotTypeID,
          SpotTypeName = request.spotTypeName,
          FloorID = request.floorID,
          FloorName = request.floorName,
          BuildingID = request.buildingID,
          BuildingName = request.buildingName
        )
      )
    }.map(ResponseCreateAccess.apply)
