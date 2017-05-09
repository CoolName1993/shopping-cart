import sbt._

name := "shopping-cart"

version := "999-SNAPSHOT"

lazy val `shopping-cart` = project in file(".")

scalaVersion := "2.11.10"

val appDependencies = Seq(
)

val testDependencies = Seq(
  "org.scalacheck" %% "scalacheck" % "1.13.4" % Test,
  "org.scalatest" %% "scalatest" % "3.0.1" % Test
)

libraryDependencies ++= appDependencies ++ testDependencies
