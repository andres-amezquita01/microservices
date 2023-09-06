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

val zioVersion = "2.0.15"
val zioLoggingVersion = "2.1.14"
val zioJsonVersion = "0.6.1"

val zioDependencies = Seq(
  "dev.zio" %% "zio" % zioVersion,
  "dev.zio" %% "zio-logging" % zioLoggingVersion,
  "dev.zio" %% "zio-json" % zioJsonVersion
)

val zioKafkaVersion = "2.4.2"

val kafkaDependencies = Seq(
  "dev.zio" %% "zio-kafka" % zioKafkaVersion
)

val firebaseDependencies = Seq(
  "com.google.firebase" % "firebase-admin" % "9.2.0"
)

val emailDependencies = Seq(
  "com.github.daddykotex" %% "courier" % "3.2.0"
)

lazy val root = project
.in(file("."))
.settings(
  name := "LogTracer Microservice",
  version := "1.0",

  libraryDependencies ++= zioDependencies,
  libraryDependencies ++= kafkaDependencies,
  libraryDependencies ++= emailDependencies,
  libraryDependencies ++= firebaseDependencies,

  run / fork := true,
  run / javaOptions ++= unnamedJavaOptions
)
.settings(
  nativeImageOptions ++= List("--no-fallback", "--initialize-at-run-time=io.netty.channel.kqueue.KQueue,io.netty.handler.ssl.BouncyCastleAlpnSslUtils,io.netty.util.AbstractReferenceCounted,io.netty.channel.DefaultFileRegion", "--initialize-at-build-time=org.apache.logging.log4j,org.apache.logging.slf4j,org.slf4j,org.jline,net.minecrell,com.lmax.disruptor"),
  nativeImageVersion := "22.3.1",
  nativeImageJvm := "graalvm-java17",
  nativeImageCommand := List("native-image")
)
.enablePlugins(NativeImagePlugin)

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

assembly / assemblyJarName := "logtracer.jar"

