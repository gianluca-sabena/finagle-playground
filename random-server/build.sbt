import AssemblyKeys._

com.twitter.scrooge.ScroogeSBT.newSettings

Revolver.settings: Seq[sbt.Def.Setting[_]]

assemblySettings

name := """finagle-playground-ms-random-server"""

version := "0.1.0"

scalaVersion  := "2.11.4"

scalacOptions := Seq("-unchecked", "-deprecation", "-feature","-language:higherKinds", "-encoding", "utf8")

// scala 2.11: finagleVersion 6.24.0, twitter server 1.9.0, scrooge 3.17

val finagleVersion = "6.24.0"

libraryDependencies ++= Seq(
  "com.twitter" %% "twitter-server" % "1.9.0",
  "com.twitter" %% "finagle-core" % finagleVersion,
  "com.twitter" %% "finagle-thrift" % finagleVersion,
  "com.twitter" %% "finagle-thriftmux" % finagleVersion,
  "com.twitter" %% "finagle-stats" % finagleVersion,
  "com.twitter" %% "scrooge-core" % "3.17.0",
  "com.typesafe" % "config" % "1.2.1",
  "org.scalatest" %% "scalatest" % "2.2.2" % "test"
)

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

testOptions in Test += Tests.Argument("-oF")

parallelExecution in Test := false

test in assembly := {}

