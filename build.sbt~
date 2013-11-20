name := "chat"

version := "1.0-SNAPSHOT"

resolvers ++= Seq(
  "Typesafe" at "http://repo.typesafe.com/typesafe/releases",
  "fwbrasil.net" at "http://fwbrasil.net/maven"
)

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  "net.fwbrasil" %% "activate-play" % "1.4.1" exclude("org.scala-stm", "scala-stm_2.10.0"),
  "net.fwbrasil" %% "activate-jdbc-async" % "1.4.1" exclude("org.scala-stm", "scala-stm_2.10.0"),
  "org.postgresql" % "postgresql" % "9.2-1003-jdbc4",
  "org.webjars" % "jquery" % "1.10.2",
  "org.webjars" % "bootstrap" % "3.0.0" exclude("org.webjars", "jquery"),
  "org.webjars" % "requirejs" % "2.1.1",
  "org.webjars" % "angularjs" % "1.2.0-rc.3",
  "org.webjars" %% "webjars-play" % "2.2.0"
)

play.Project.playScalaSettings
