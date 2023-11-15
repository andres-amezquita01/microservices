import zio._
import zio.test._
import zio.test.Assertion._

import sttp.tapir.server.stub.TapirStubInterpreter
import shared.mapper.endpoints.Exposer
import sttp.client3.testing.SttpBackendStub
import sttp.tapir.ztapir.RIOMonadError

import authorizations.* 
import agents.domain.repository.AgentRepository
import agents.AgentMockedRepository
import sttp.client3.UriContext

import io.circe.syntax._
import scala.util.Random
import authentications.infrastructure.controller.AuthenticationController

object ZioTapirSampleTest extends ZIOSpecDefault, DI {

  private val serverUrl = "http://localhost:8090/"

  val authContoller = AuthenticationController()

  def serverRoute(route: String) = uri"${serverUrl}${route}"

  private val loginStub = TapirStubInterpreter(SttpBackendStub.apply(new RIOMonadError[Any]))
        .whenServerEndpoint(authContoller.loginUserRoute)
        .thenRunLogic()
        .backend()
  
  private val signupStub = TapirStubInterpreter(SttpBackendStub.apply(new RIOMonadError[Any]))
        .whenServerEndpoint(authContoller.createUserRoute)
        .thenRunLogic()
        .backend()

  def spec = suite("Authentication")(
    completeSpec,
    loginSpec
  )

  private def completeSpec = suite("Full Signup Login")(
    test("Signup & Login Valid Credentials") {
      for
        documentNumber <- ZIO.succeed(Random.nextInt).map(_.abs)

        singUpRequest <- ZIO.succeed(
          Map(
            "document" -> documentNumber.toString.takeRight(7), 
            "documentType" -> "",
            "name" -> "Testing User",
            "lastName" -> "TestingDriguez",
            "phone" -> "320202010",
            "email" -> s"$documentNumber@testing.com",
            "userName" -> documentNumber.toString,
            "password" -> "12345",
          ).asJson.noSpaces
        )

        loginRequest <- ZIO.succeed(
          Map(
            "usernameOrEmail" -> s"${documentNumber}@testing.com",
            "password" -> "12345"
          ).asJson.noSpaces
        )

        singUpResult <- sttp.client3.basicRequest
          .post(serverRoute("signup"))
          .body(singUpRequest)
          .send(signupStub)
          .map(_.body)
        
        loginResult <- sttp.client3.basicRequest
          .post(serverRoute("login"))
          .body(loginRequest)
          .send(loginStub)
          .map(_.body)
        
      yield (assertTrue(singUpResult.isRight && loginResult.isRight))
    },
  )

  private def loginSpec = suite("Login")(
    test("Invalid Credentials") {
      val body = 
        sttp.client3.basicRequest
          .post(serverRoute("login"))
          .body(
            Map("usernameOrEmail" -> "nonrealuser", "password" -> "nonrealpassword").asJson.noSpaces
          )
            .send(loginStub)
            .map(_.body)

      for
        result <- body
      yield (assertTrue(result.isLeft))
    },
  )
}
