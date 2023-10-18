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
import agents.application.get_agents._
import agents.application.remove_agent._
import agents.application.create_agent._
import agents.application.update_agent._
import agents.application.get_agent._

class AgentController
(using 
  jwtService: JwtService, 
  agentRepository:AgentRepository)
extends BaseController:

  private val getAgents =
    secureEndpoint
    .in("agents")
    .get
    .in(
      query[Int]("page").and(query[Int]("per_page"))
    )
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[PaginatedResponse[Agent]])
    .exposeSecure

  private val getAgentsRoute: ZServerEndpoint[Any, Any] =
    getAgents.serverLogic{ (user:UserContext, permission:PermissionContext) => (page:Int, perPage:Int)=>
      GetAgentsUseCase().execute(
        RequestGetAgents(page, perPage) 
      )
      .mapError(throwableErrorMapper)
    }.expose

  private val getAgent = 
    secureEndpoint
    .in("agent")
    .get
    .in(
      query[Option[String]]("id").and(query[Option[String]]("identificationCode")).and(query[Option[String]]("email"))
    )
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[ResponseGetAgent])
    .exposeSecure

  private val getAgentRoute: ZServerEndpoint[Any, Any] =
    getAgent.serverLogic{ (user:UserContext, permission:PermissionContext) => (id:Option[String], identificationCode:Option[String], email:Option[String]) =>
      GetAgentUseCase().execute(
          RequestGetAgent(id = id, identificationCode = identificationCode, email = email)
      ) 
      .mapError(throwableErrorMapper)
    }.expose

  private val removeAgent = 
    secureEndpoint
    .in("agents" / path[String]("id"))
    .delete
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[ResponseRemoveAgent])
    .exposeSecure

  private val removeAgentRoute: ZServerEndpoint[Any, Any] =
    removeAgent.serverLogic{ (user:UserContext, permission:PermissionContext) => (id:String) =>
      RemoveAgentUseCase().execute(
          RequestRemoveAgent(id)
      )
      .mapError(throwableErrorMapper)
    }.expose

  private val createAgent = 
    secureEndpoint
    .in("agents")
    .in(jsonBody[RequestCreateAgent])
    .post
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[ResponseCreateAgent])
    .exposeSecure

  private val createAgentRoute: ZServerEndpoint[Any, Any] =
    createAgent.serverLogic{ (user:UserContext, permission:PermissionContext) => request =>
      CreateAgentUseCase().execute(
        request
      )
      .mapError(throwableErrorMapper)
    }.expose

  private val updateAgent = 
    secureEndpoint
    .in("agents" / path[String]("id"))
    .put
    .in(jsonBody[RequestUpdateAgent])
    .errorOutVariant[ApplicationError](oneOfVariant(jsonBody[ErrorResponse]))
    .out(jsonBody[ResponseUpdateAgent])
    .exposeSecure

  private val updateAgentRoute: ZServerEndpoint[Any, Any] =
    updateAgent.serverLogic{ (user:UserContext, permission:PermissionContext) => (id: String, request: RequestUpdateAgent) =>
      UpdateAgentUseCase(id).execute(
        request
      )
      .mapError(throwableErrorMapper)
    }.expose

