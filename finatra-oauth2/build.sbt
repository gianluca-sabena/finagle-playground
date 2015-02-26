import AssemblyKeys._ // put this at the top of the file

name := """finagle-playground-oauth2"""

organization  := "gianluca-sabena"

version       := "0.0.1"

scalaVersion  := "2.10.4"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

// finatra 1.6.0 -> twitter server 1.7.3, finagle-core 6.20.0, twitter-util 6.19.0, scrooge 3.16.1
// finatra 1.5.4-SNAPSHOT -> twitter server 1.7.1 -> finagleVersion 6.17.0 -> scrooge 3.16.1
// finatra 1.5.4-SNAPSHOT -> twitter server 1.8.0 -> finagleVersion 6.22.0 -> scrooge 3.17 // scala  2.10.4

val finagleVersion = "6.20.0"

libraryDependencies ++= Seq(
  "com.twitter" %% "finagle-stats" % finagleVersion,
  "com.twitter" %% "finagle-thriftmux" % finagleVersion,
  "com.twitter" %% "finatra" % "1.6.0",
  "com.twitter" %% "scrooge-core" % "3.17.0",
  "com.typesafe.play" %% "play-json" % "2.3.5",
  "org.scalatest" %% "scalatest" % "1.9.2" % "test"
)

//"com.twitter" %% "finagle-oauth2" % "0.1.4"

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

testOptions in Test += Tests.Argument("-oF")

parallelExecution in Test := false

assemblySettings

test in assembly := {}

Revolver.settings

net.virtualvoid.sbt.graph.Plugin.graphSettings

com.twitter.scrooge.ScroogeSBT.newSettings