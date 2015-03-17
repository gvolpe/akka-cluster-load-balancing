import sbt._
import Keys._

organization := "kamkor"

version := "1.0"

scalaVersion := "2.11.5"

name := "akka-cluster-load-balancing"

lazy val root = (project in file("."))

lazy val akkaVer = "2.4-SNAPSHOT"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVer,
  "com.typesafe.akka" %% "akka-cluster" % akkaVer,
  "com.typesafe.akka" %% "akka-cluster-metrics" % akkaVer,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVer,
  "org.scalacheck" %% "scalacheck" % "1.12.0",
  "ch.qos.logback" % "logback-classic" % "1.0.13",
  "org.fusesource" % "sigar" % "1.6.4",
  "org.scalatest" %% "scalatest" % "2.1.6" % Test,
  "com.typesafe.akka" %% "akka-testkit" % akkaVer % Test
)



