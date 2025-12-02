import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport.*

import scala.collection.Seq

ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.7.4"

ThisBuild / scalafmtOnCompile := true

val siteOutput = "public"

// Task to copy resources to the site output directory
val copyResourcesToSite = Def.task {
  val from = baseDirectory.value / "src" / "main" / "resources"
  val to = baseDirectory.value / "target" / siteOutput
  if (from.exists) {
    IO.delete(to)
    IO.copyDirectory(from, to)
    Seq(to)
  } else Seq.empty[File]
}

// Task to copy js files after linking
def linkAndCopy(
    link: TaskKey[sbt.Attributed[org.scalajs.linker.interface.Report]]
) = Def.task {
  val outDir = (Compile / link / scalaJSLinkerOutputDirectory).value
  val res = (Compile / link).value
  copyJSFilesTask.value(
    baseDirectory.value / "target" / siteOutput / "js",
    outDir
  )
  res
}
val copyJSFilesTask = Def.task {
  (jsOutputDir: File, compiledPath: File) =>
    IO.createDirectory(jsOutputDir)
    val jsFiles = (compiledPath ** "*").get.filter(_.isFile)
    jsFiles.foreach(f => IO.copyFile(f, jsOutputDir / f.getName))
}

lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "piccola-italia-menu",
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest" % "3.2.19" % "test",
      "org.scala-js" %%% "scalajs-dom" % "2.5.0",
      "com.raquo" %%% "laminar" % "17.0.0",
      "io.circe" %%% "circe-core" % "0.14.3",
      "io.circe" %%% "circe-generic" % "0.14.3",
      "io.circe" %%% "circe-parser" % "0.14.3"
    ),

    // Consistent output directory for Scala.js artifacts
    Compile / resourceGenerators += copyResourcesToSite.taskValue,
    crossTarget := baseDirectory.value / "target" / "scala-output",
    Compile / fastLinkJS := linkAndCopy(fastLinkJS).value,
    Compile / fullLinkJS := linkAndCopy(fullLinkJS).value
  )
