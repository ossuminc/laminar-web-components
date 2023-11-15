import scala.sys.process.Process

Global / onChangedBuildSource := ReloadOnSourceChanges

lazy val lwc = project.in(file(".")).aggregate(material, ossum)

lazy val material = project.in(file("material"))
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins (ScalaJSBundlerPlugin)
  .enablePlugins(ScalablyTypedConverterPlugin)
  .enablePlugins(SbtNativePackager, JavaAppPackaging)
  .settings(
    name := "laminar-web-components-material",
    normalizedName := "laminar-web-components-material",
    organization := "com.ossuminc",
    githubOwner := "ossuminc",
    githubRepository := "laminar-web-components",
    scalaVersion := "3.3.1",
    ThisBuild / dynverVTagPrefix := false,
    // NEVER  SET  THIS: version := "0.1"
    // IT IS HANDLED BY: sbt-dynver
    ThisBuild / dynverSeparator := "-",
    // Tell Scala.js that this is just a library with no main
    scalaJSUseMainModuleInitializer := false,

    useYarn := true,

    // For ScalablyTyped
//     externalNpm := {
//      Process(Seq("npm", "install"), baseDirectory.value).!
//      baseDirectory.value
//    },
    Compile / npmDependencies.withRank(KeyRanks.Invisible) := Seq(
      "material-components-web" -> "14.0.0"
    ),

    libraryDependencies += "com.raquo" %%% "laminar" % "16.0.0",

    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    Compile/ fastOptJS / scalaJSLinkerConfig  ~= {   _.withSourceMap(false) },
  )

lazy val ossum = project.in(file("ossum"))
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(ScalaJSBundlerPlugin)
  .enablePlugins(SbtNativePackager, JavaAppPackaging)
  .settings(
    name := "laminar-web-components-material",
    normalizedName := "laminar-web-components-ossum",
    organization := "com.ossuminc",
    githubOwner := "ossuminc",
    githubRepository := "laminar-web-components",
    scalaVersion := "3.3.1",
    ThisBuild / dynverVTagPrefix := false,
    // NEVER  SET  THIS: version := "0.1"
    // IT IS HANDLED BY: sbt-dynver
    ThisBuild / dynverSeparator := "-",

    // Tell Scala.js that this is an application with a main method
    scalaJSUseMainModuleInitializer := false,

    libraryDependencies += "com.raquo" %%% "laminar" % "16.0.0",
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.CommonJSModule)
    },
    Compile / fastOptJS / scalaJSLinkerConfig ~= {
      _.withSourceMap(false)
    },
    useYarn := true,
    Compile / npmDependencies ++= Components()
  )
