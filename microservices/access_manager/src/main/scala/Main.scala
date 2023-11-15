import zio._
import zio.http._

import io.circe.generic.auto._
import sttp.tapir.generic.auto._
import sttp.tapir.server.ziohttp.ZioHttpInterpreter
import sttp.tapir.swagger.bundle.SwaggerInterpreter
import sttp.tapir.ztapir.ZServerEndpoint
import sttp.tapir.json.circe._

import authentications.infrastructure.controller.AuthenticationController
import java.io.IOException
import sttp.tapir.Endpoint
import shared.mapper.endpoints.Exposer
import shared.mapper.open_api.OpenAPIGenerator
import sttp.tapir.server.ziohttp.ZioHttpServerOptions

import shared.interceptors._
import sttp.tapir.server.interceptor.cors.CORSInterceptor
import agents.infrastructure.controller.AgentController
import roles.infrastructure.controller.RoleController
import permissions.infrastructure.controller.PermissionController

object Main extends ZIOAppDefault with DI:

  AuthenticationController()
  AgentController()
  RoleController()
  PermissionController()

  val serverOptions:ZioHttpServerOptions[Any] =
    ZioHttpServerOptions
      .customiseInterceptors
      .corsInterceptor(CORSInterceptor.customOrThrow(CorsHandling.config))
      .exceptionHandler(ErrorHandling.exceptionHandler)
      .options

  val routes: HttpApp[Any, Throwable] = 
    ZioHttpInterpreter(serverOptions)
      .toHttp(
        Exposer.availableEndpoints.toList ++ 
        OpenAPIGenerator().getDocs()
      )

  override def run: URIO[Any, ExitCode] =
    Server
      .serve(routes.withDefaultErrorResponse)
      .provide(
          Server.defaultWithPort(8090)
      ).exitCode
  end run
