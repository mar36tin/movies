name := """movies"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.15"


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"


libraryDependencies ++= Seq(
  // Play Framework Core
  guice,
  "com.typesafe.play" %% "play-json" % "2.9.4",

  // Testing: ScalaTest with Play Framework
  "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.0" % Test,

  // Functional Programming Libraries
  "org.typelevel" %% "cats-core" % "2.10.0",
  "org.typelevel" %% "cats-effect" % "3.5.1",

  // Doobie for Database Access
  "org.tpolecat" %% "doobie-core" % "1.0.0-RC2",
  "org.tpolecat" %% "doobie-hikari" % "1.0.0-RC2",
  "org.tpolecat" %% "doobie-postgres" % "1.0.0-RC2",
  "org.tpolecat" %% "doobie-scalatest" % "1.0.0-RC2" % Test,
  "org.tpolecat" %% "doobie-core" % "1.0.0-RC2",
  "org.tpolecat" %% "doobie-hikari" % "1.0.0-RC2",
  "org.tpolecat" %% "doobie-scalatest" % "1.0.0-RC2" % Test,
  "com.h2database" % "h2" % "2.2.220" % Test,

  // PostgreSQL Driver
  "org.postgresql" % "postgresql" % "42.6.0",

  // Testing Libraries
  "org.scalatest" %% "scalatest" % "3.2.16" % Test,
  "org.mockito" %% "mockito-scala" % "1.17.37" % Test

)

resolvers += "Typesafe Repository" at "https://repo.typesafe.com/typesafe/releases/"
