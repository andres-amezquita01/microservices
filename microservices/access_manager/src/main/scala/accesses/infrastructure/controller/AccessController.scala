package accesses.infrastructure.controller

import io.circe.generic.auto._
import sttp.tapir.generic.auto._
import sttp.tapir.ztapir._
import sttp.tapir.Endpoint
import sttp.tapir.PublicEndpoint
import sttp.tapir.json.circe._
import zio._

import shared.BaseController
import authentications.domain.service.JwtService
import accesses.domain.repository.AccessRepository
import shared.responses._
import accesses.domain.entity.Access
import shared.mapper.endpoints.Exposer._
import authentications.domain.entity.UserContext
import authorizations.domain.entity.PermissionContext
import accesses.application.get_accesses._
import accesses.application.remove_access._
import accesses.application.create_access._
import accesses.application.update_access._
import accesses.application.get_access._

class AccessController
(using 
  jwtService: JwtService, 
  accessRepository: AccessRepository)
extends BaseController:

  private val getAccesses = 
    secureEndpoint
    .in("accesses")
    .get
    .in(query[Int]("page").and(query[Int]("per_page")))
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[PaginatedResponse[Access]])
    .exposeSecure

  private val getAccessesRoute: ZServerEndpoint[Any, Any] =
    getAccesses.serverLogic{ (user:UserContext, permission:PermissionContext) => (page:Int, perPage:Int) =>
      GetAccessesUseCase().execute(
        RequestGetAccesses(page, perPage) 
      )
      .mapError(throwableErrorMapper)
    }.expose

  private val getAccess = 
    secureEndpoint
    .in("access")
    .get
    .in(query[Option[String]]("id"))
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[ResponseGetAccess])
    .exposeSecure

  private val getAccessRoute: ZServerEndpoint[Any, Any] =
    getAccess.serverLogic{ (user:UserContext, permission:PermissionContext) => id:Option[String] =>
      GetAccessUseCase().execute(
        RequestGetAccess(id)
      )
      .mapError(throwableErrorMapper)
    }.expose

  private val removeAccess = 
    secureEndpoint
    .in("accesses" / path[String]("id"))
    .delete
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[ResponseRemoveAccess])
    .exposeSecure

  private val removeAccessRoute: ZServerEndpoint[Any, Any] =
    removeAccess.serverLogic{ (user:UserContext, permission:PermissionContext) => id:String =>
      RemoveAccessUseCase().execute(
        RequestRemoveAccess(id)
      )
      .mapError(throwableErrorMapper)
    }.expose

  private val createAccess = 
    secureEndpoint
    .in("accesses")
    .in(jsonBody[RequestCreateAccess])
    .post
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[ResponseCreateAccess])
    .exposeSecure

  private val createAccessRoute: ZServerEndpoint[Any, Any] =
    createAccess.serverLogic{ (user:UserContext, permission:PermissionContext) => request =>
      CreateAccessUseCase().execute(
        request
      )
      .mapError(throwableErrorMapper)
    }.expose

  private val updateAccess = 
    secureEndpoint
    .in("accesses" / path[String]("id"))
    .put
    .in(jsonBody[RequestUpdateAccess])
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[ResponseUpdateAccess])
    .exposeSecure

  private val updateAccessRoute: ZServerEndpoint[Any, Any] =
    updateAccess.serverLogic{ (user:UserContext, permission:PermissionContext) => (id: String, request: RequestUpdateAccess) =>
      UpdateAccessUseCase(id).execute(
        request
      )
      .mapError(throwableErrorMapper)
    }.expose

  