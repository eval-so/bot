name := "eval-so"

version := "1.0.0"

scalaVersion := "2.10.1"

scalacOptions ++= Seq(
  "-deprecation",
  "-Xexperimental"
)

libraryDependencies ++= Seq(
  "pircbot" % "pircbot" % "1.5.0"
)
