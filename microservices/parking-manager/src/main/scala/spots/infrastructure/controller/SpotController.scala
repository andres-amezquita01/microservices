package spots.infrastructure.controller

import io.circe.generic.auto._
import sttp.tapir.generic.auto._
import sttp.tapir.ztapir._
import sttp.tapir.Endpoint
import sttp.tapir.PublicEndpoint
import sttp.tapir.json.circe._
import zio._

import shared.BaseController
import authentications.domain.service.JwtService
import spots.domain.repository.SpotRepository
import shared.responses._
import spots.domain.entity.Spot
import shared.mapper.endpoints.Exposer._
import authentications.domain.entity.UserContext
import authorizations.domain.entity.PermissionContext
import spots.application.get_spots._
import spots.application.remove_spot._
import spots.application.create_spot._
import spots.application.update_spot._
import spots.application.get_spot._

class SpotController
(using 
  jwtService: JwtService, 
  SpotRepository:SpotRepository)
extends BaseController:

  private val getspots =
    secureEndpoint
    .in("spots")
    .get
    .in(
      query[Int]("page").and(query[Int]("per_page"))
    )
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[PaginatedResponse[Spot]])
    .exposeSecure

  private val getspotsRoute: ZServerEndpoint[Any, Any] =
    getspots.serverLogic{ (user:UserContext, permission:PermissionContext) => (page:Int, perPage:Int)=>
      GetspotsUseCase().execute(
        RequestGetspots(page, perPage) 
      )
      .mapError(throwableErrorMapper)
    }.expose

  private val getSpot = 
    secureEndpoint
    .in("spot")
    .get
    .in(
      query[Option[String]]("id").and(query[Option[String]]("id")).and(query[Option[String]]("floor_id"))
    )
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[ResponseGetSpot])
    .exposeSecure

  private val getSpotRoute: ZServerEndpoint[Any, Any] =
    getSpot.serverLogic{ (user:UserContext, permission:PermissionContext) => (id:Option[String], name:Option[String], floor_id:Option[String]) =>
      GetSpotUseCase().execute(
          RequestGetSpot(id = id, name = name, floor_id = floor_id)
      ) 
      .mapError(throwableErrorMapper)
    }.expose

  private val removeSpot = 
    secureEndpoint
    .in("spots" / path[String]("id"))
    .delete
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[ResponseRemoveSpot])
    .exposeSecure

  private val removeSpotRoute: ZServerEndpoint[Any, Any] =
    removeSpot.serverLogic{ (user:UserContext, permission:PermissionContext) => (id:String) =>
      RemoveSpotUseCase().execute(
          RequestRemoveSpot(id)
      )
      .mapError(throwableErrorMapper)
    }.expose

  private val createSpot = 
    secureEndpoint
    .in("spots")
    .in(jsonBody[RequestCreateSpot])
    .post
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[ResponseCreateSpot])
    .exposeSecure

  private val createSpotRoute: ZServerEndpoint[Any, Any] =
    createSpot.serverLogic{ (user:UserContext, permission:PermissionContext) => request =>
      CreateSpotUseCase().execute(
        request
      )
      .mapError(throwableErrorMapper)
    }.expose

  private val updateSpot = 
    secureEndpoint
    .in("spots" / path[String]("id"))
    .put
    .in(jsonBody[RequestUpdateSpot])
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[ResponseUpdateSpot])
    .exposeSecure

  private val updateSpotRoute: ZServerEndpoint[Any, Any] =
    updateSpot.serverLogic{ (user:UserContext, permission:PermissionContext) => (id: String, request: RequestUpdateSpot) =>
      UpdateSpotUseCase(id).execute(
        request
      )
      .mapError(throwableErrorMapper)
    }.expose

