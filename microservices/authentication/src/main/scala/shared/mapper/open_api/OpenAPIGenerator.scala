package shared.mapper.open_api

import zio._
import shared.mapper.endpoints.Exposer
import sttp.tapir.ztapir.ZServerEndpoint
import sttp.tapir.swagger.bundle.SwaggerInterpreter
import sttp.apispec.openapi.Info

class OpenAPIGenerator:

  def getDocs(): List[ZServerEndpoint[Any, Any]] = 
    SwaggerInterpreter.apply()
      .fromEndpoints[Task](
        endpoints = Exposer.availableRoutes.toList ++ Exposer.availableSecuredRoutes.toList.map(_.endpoint),
        info = Info(
          title = "Authentication Microservice",
          version = "1.0"
        )
      )
