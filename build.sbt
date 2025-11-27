import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.7.4"

ThisBuild / scalafmtOnCompile := true

lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "piccola-italia-menu",
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.5.0",
    scalaJSUseMainModuleInitializer := true,

    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % "test"

  )
