name := "compression"

version := "0.0.9"

versionScheme := Some("early-semver")

scalaVersion := "3.2.1"

enablePlugins(ScalaNativePlugin)

nativeLinkStubs := true

nativeMode := "debug"

nativeLinkingOptions := Seq(s"-L${baseDirectory.value}/native-lib")

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:postfixOps",
  "-language:implicitConversions",
  "-language:existentials",
)

organization := "io.github.spritzsn"

githubOwner := "spritzsn"

githubRepository := name.value

Global / onChangedBuildSource := ReloadOnSourceChanges

resolvers += Resolver.githubPackages("edadma")

licenses := Seq("ISC" -> url("https://opensource.org/licenses/ISC"))

homepage := Some(url("https://github.com/spritzsn/" + name.value))

//libraryDependencies += "org.scalatest" %%% "scalatest" % "3.2.13" % "test"

libraryDependencies ++= Seq(
  "io.github.spritzsn" %%% "spritz" % "0.0.47",
  "io.github.spritzsn" %%% "libbrotli" % "0.0.3",
)

//libraryDependencies ++= Seq(
//)

publishMavenStyle := true

Test / publishArtifact := false
