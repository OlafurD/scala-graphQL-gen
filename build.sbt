lazy val MetaVersion     = "1.7.0"
lazy val ParadiseVersion = "3.0.0-M8"
lazy val scala212        = "2.12.2"
lazy val scalameta       = "org.scalameta" %% "scalameta" % MetaVersion
lazy val contrib         = "org.scalameta" %% "contrib" % MetaVersion
lazy val testkit         = "org.scalameta" %% "testkit" % MetaVersion
lazy val paradise        = "org.scalameta" % "paradise" % ParadiseVersion cross CrossVersion.full


lazy val allSettings = Seq(
  scalaVersion := scala212,
  resolvers += Resolver.bintrayIvyRepo("scalameta", "maven"),resolvers += Resolver.sonatypeRepo("releases"),
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.1" % Test,

    // TODO(olafur) remove after testkit adds this utility
    "com.googlecode.java-diff-utils" % "diffutils" % "1.3.0" % Test,
    "org.sangria-graphql" %% "sangria" % "1.2.1"
  ),
  updateOptions := updateOptions.value.withCachedResolution(true)
)

allSettings

// Macro setting is any module that has macros, or manipulates meta trees
lazy val macroSettings = Seq(
  libraryDependencies += scalameta,
  addCompilerPlugin(paradise),
  scalacOptions += "-Xplugin-require:macroparadise"
)

lazy val macros = project.settings(
  allSettings,
  macroSettings
)


version := "1.0"

scalaVersion := "2.12.2"
    