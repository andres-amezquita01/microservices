val scala3Version = "3.3.0"

ThisBuild / scalaVersion := scala3Version
ThisBuild / version := "1.0"
ThisBuild / organization := "com.nojipiz"

val unnamedJavaOptions = List(
  "-XX:+IgnoreUnrecognizedVMOptions",
  "--add-opens=java.base/java.lang=ALL-UNNAMED",
  "--add-opens=java.base/java.lang.invoke=ALL-UNNAMED",
  "--add-opens=java.base/java.lang.reflect=ALL-UNNAMED",
  "--add-opens=java.base/java.io=ALL-UNNAMED",
  "--add-opens=java.base/java.net=ALL-UNNAMED",
  "--add-opens=java.base/java.nio=ALL-UNNAMED",
  "--add-opens=java.base/java.util=ALL-UNNAMED",
  "--add-opens=java.base/java.util.concurrent=ALL-UNNAMED",
  "--add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED",
  "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED",
  "--add-opens=java.base/sun.nio.cs=ALL-UNNAMED",
  "--add-opens=java.base/sun.security.action=ALL-UNNAMED",
  "--add-opens=java.base/sun.util.calendar=ALL-UNNAMED",
  "--add-opens=java.security.jgss/sun.security.krb5=ALL-UNNAMED"
)

libraryDependencySchemes += "com.softwaremill.sttp.apispec" %% "openapi-model" % "early-semver"
libraryDependencySchemes += "com.softwaremill.sttp.apispec" %% "apispec-model" % "early-semver"

val zioVersion = "2.0.15"
val zioHttpVersion = "3.0.0-RC2"
val zioLoggingVersion = "2.1.14"
val tapirVersion = "1.7.3"

val zioDependencies = Seq(
  "dev.zio" %% "zio" % zioVersion,
  "dev.zio" %% "zio-http" % zioHttpVersion,
  "dev.zio" %% "zio-logging" % zioLoggingVersion,
)

val zioKafkaVersion = "2.4.2"

val kafkaDependencies = Seq(
  "dev.zio" %% "zio-kafka" % zioKafkaVersion
)

val tapirDependencies = Seq(
  "com.softwaremill.sttp.tapir" %% "tapir-zio" % tapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-zio-http-server" % tapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % tapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % tapirVersion ,
  "com.softwaremill.sttp.tapir" %% "tapir-sttp-stub-server" % tapirVersion
)

val testingDependencies = Seq(
  "dev.zio" %% "zio-test"          % zioVersion % Test,
  "dev.zio" %% "zio-test-sbt"      % zioVersion % Test,
  "dev.zio" %% "zio-test-magnolia" % zioVersion % Test,
)

val quillDependencies = Seq(
  "io.getquill" %% "quill-jdbc-zio" % "4.6.0.1",
  "org.postgresql" % "postgresql" % "42.5.4",
  "org.flywaydb" % "flyway-core" % "9.22.3"
)

val authDependencies = Seq(
  "com.github.jwt-scala" %% "jwt-core" % "9.2.0",
  ("com.github.t3hnar" %% "scala-bcrypt" % "4.3.0").cross(CrossVersion.for3Use2_13)
)

val emailDependencies = Seq(
  "com.github.daddykotex" %% "courier" % "3.2.0"
)

lazy val root = project
.in(file("."))
.settings(
  name := "Authentication Microservice",
  version := "1.0",

  libraryDependencies ++= zioDependencies,
  libraryDependencies ++= quillDependencies,
  libraryDependencies ++= tapirDependencies,
  libraryDependencies ++= authDependencies,
  libraryDependencies ++= emailDependencies,
  libraryDependencies ++= testingDependencies,
  libraryDependencies ++= kafkaDependencies,

  run / fork := true,
  run / javaOptions ++= unnamedJavaOptions
)

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")

assembly / assemblyMergeStrategy := {
    case path if path.contains("META-INF/services") =>
      MergeStrategy.concat
    case PathList("META-INF", "maven", "org.webjars", "swagger-ui", "pom.properties") =>
        MergeStrategy.singleOrError
    case PathList("META-INF", "resources", "webjars", "swagger-ui", xs@_*) =>
          MergeStrategy.first
    case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
            case _                        => MergeStrategy.first
  }

assembly / assemblyJarName := "authentication.jar"

