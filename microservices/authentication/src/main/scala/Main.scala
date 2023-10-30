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
import shared.db.DbMigrator
import javax.sql.DataSource
import io.getquill.JdbcContextConfig
import io.getquill.jdbczio.Quill
import io.getquill.util.LoadConfig
import com.zaxxer.hikari.HikariConfig

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

  val apiServer = Server
      .serve(routes.withDefaultErrorResponse)
      .provide(
          Server.defaultWithPort(8090)
      ).exitCode

  private def availableSourceSchedule(sourceName: String, sourcePath: Option[String] = None) = Schedule
    .fixed(2000.milliseconds)
    .tapOutput(o =>
      ZIO.logInfo(
        s"Waiting for $sourceName to be available ${sourcePath.map(path => s"at ${path}").getOrElse("")}, retry count: $o"
      )
    )

  private val jdbcContextLayer: TaskLayer[JdbcContextConfig] = for {
    res <- ZLayer {
      ZIO
        .attempt(LoadConfig("live-database"))
        .map(JdbcContextConfig.apply)
        .tap(cfg => ZIO.attempt(new HikariConfig(cfg.configProperties).validate()))
    }
    _ <- ZLayer.fromZIO(ZIO.logInfo("DATA: " + res.get.toString))
  } yield (res)

  private val dataSourceLayer: RLayer[JdbcContextConfig, DataSource] = ZLayer(
    ZIO.service[JdbcContextConfig]
  ).flatMap { env =>
      Quill.DataSource.fromJdbcConfig(env.get).retry(availableSourceSchedule("Database"))
  }

  val main = for{
      migrator <- ZIO.service[DbMigrator]
      _ <- migrator.migrate()
      server <- apiServer
      _ <- ZIO.never
  } yield()

  override def run: URIO[Any, ExitCode] = for {
    res <- main.provide(DbMigrator.live, dataSourceLayer, jdbcContextLayer).orDie
    exitCode <- ZIO.succeed(ExitCode.success)
  } yield exitCode
