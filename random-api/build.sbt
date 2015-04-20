import AssemblyKeys._

Revolver.settings: Seq[sbt.Def.Setting[_]]

com.twitter.scrooge.ScroogeSBT.newSettings

assemblySettings

name := """finagle-playground-ms-random-api"""

version := "0.1.0"

scalaVersion  := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-feature","-language:higherKinds", "-encoding", "utf8")

// scala 2.11: finagleVersion 6.24.0

val finagleVersion = "6.24.0"

libraryDependencies ++= Seq(
  "com.twitter" %% "twitter-server" % "1.9.0",
  "com.twitter" %% "finagle-core" % finagleVersion,
  "com.twitter" %% "finagle-thrift" % finagleVersion,
  "com.twitter" %% "finagle-thriftmux" % finagleVersion,
  "com.twitter" %% "finagle-httpx" % finagleVersion,
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.4.4",
  "com.twitter" %% "scrooge-core" % "3.17.0",
  "com.github.finagle" %% "finch-core" % "0.7.0-SNAPSHOT" changing(),
  "com.github.finagle" %% "finch-json" % "0.7.0-SNAPSHOT"changing(),
  "org.scalatest" %% "scalatest" % "2.2.2" % "test"
)

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  Resolver.sonatypeRepo("snapshots")
)

testOptions in Test += Tests.Argument("-oF")

parallelExecution in Test := false

test in assembly := {}

