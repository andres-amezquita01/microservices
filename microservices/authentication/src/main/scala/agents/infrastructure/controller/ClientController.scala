package agents.infrastructure.controller

import io.circe.generic.auto._
import sttp.tapir.generic.auto._
import sttp.tapir.ztapir._
import sttp.tapir.Endpoint
import sttp.tapir.PublicEndpoint
import sttp.tapir.json.circe._
import zio._

import shared.BaseController
import authentications.domain.service.JwtService
import agents.domain.repository.AgentRepository
import shared.responses._
import agents.domain.entity.Agent
import shared.mapper.endpoints.Exposer._
import authentications.domain.entity.UserContext
import authorizations.domain.entity.PermissionContext
import agents.application.get_clients._
import agents.application.remove_client._
import agents.application.create_client._
import agents.application.update_client._
import agents.application.get_client._

class ClientController
(using 
  jwtService: JwtService, 
  agentRepository:AgentRepository)
extends BaseController:

  private val getClients =
    secureEndpoint
    .in("clients")
    .get
    .in(
      query[Int]("page").and(query[Int]("per_page"))
    )
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[PaginatedResponse[Agent]])
    .exposeSecure

  private val getClientsRoute: ZServerEndpoint[Any, Any] =
    getClients.serverLogic{ (user:UserContext, permission:PermissionContext) => (page:Int, perPage:Int)=>
      GetClientsUseCase().execute(
        RequestGetClients(page, perPage) 
      )
      .mapError(throwableErrorMapper)
    }.expose

  private val getClient = 
    secureEndpoint
    .in("client")
    .get
    .in(
      query[Option[String]]("id").and(query[Option[String]]("identificationCode")).and(query[Option[String]]("email"))
    )
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[ResponseGetClient])
    .exposeSecure

  private val getClientRoute: ZServerEndpoint[Any, Any] =
    getClient.serverLogic{ (user:UserContext, permission:PermissionContext) => (id:Option[String], identificationCode:Option[String], email:Option[String]) =>
      GetClientUseCase().execute(
          RequestGetClient(id = id, identificationCode = identificationCode, email = email)
      ) 
      .mapError(throwableErrorMapper)
    }.expose

  private val removeClient = 
    secureEndpoint
    .in("clients" / path[String]("id"))
    .delete
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[ResponseRemoveClient])
    .exposeSecure

  private val removeClientRoute: ZServerEndpoint[Any, Any] =
    removeClient.serverLogic{ (user:UserContext, permission:PermissionContext) => (id:String) =>
      RemoveClientUseCase().execute(
          RequestRemoveClient(id)
      )
      .mapError(throwableErrorMapper)
    }.expose

  private val createClient = 
    secureEndpoint
    .in("clients")
    .in(jsonBody[RequestCreateClient])
    .post
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[ResponseCreateClient])
    .exposeSecure

  private val createClientRoute: ZServerEndpoint[Any, Any] =
    createClient.serverLogic{ (user:UserContext, permission:PermissionContext) => request =>
      CreateClientUseCase().execute(
        request
      )
      .mapError(throwableErrorMapper)
    }.expose

  private val updateClient = 
    secureEndpoint
    .in("clients" / path[String]("id"))
    .put
    .in(jsonBody[RequestUpdateClient])
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[ResponseUpdateClient])
    .exposeSecure

  private val updateClientRoute: ZServerEndpoint[Any, Any] =
    updateClient.serverLogic{ (user:UserContext, permission:PermissionContext) => (id: String, request: RequestUpdateClient) =>
      UpdateClientUseCase(id).execute(
        request
      )
      .mapError(throwableErrorMapper)
    }.expose

