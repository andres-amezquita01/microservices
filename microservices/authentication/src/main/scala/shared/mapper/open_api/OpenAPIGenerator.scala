package shared.mapper.open_api

import zio._
import shared.mapper.endpoints.Exposer
import sttp.tapir.ztapir.ZServerEndpoint
import sttp.tapir.swagger.bundle.SwaggerInterpreter

class OpenAPIGenerator:

  def getDocs(): List[ZServerEndpoint[Any, Any]] = 
    SwaggerInterpreter.apply()
      .fromEndpoints[Task](
        Exposer.availableRoutes.toList ++
        Exposer.availableSecuredRoutes.toList.map(_.endpoint),
        "Authentication Microservice",
        "1.0"
      )
