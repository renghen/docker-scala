version      := "1.4.3"
name         := "openapi-client"
organization := "org.openapitools"

scalaVersion := "3.3.3"

libraryDependencies ++= Seq(
  "com.softwaremill.sttp.client4"         %% "core"                  % "4.0.0-M16",
  "com.softwaremill.sttp.client4"         %% "okhttp-backend"        % "4.0.0-M16",
  "com.squareup.okhttp3.sample"            % "unixdomainsockets"     % "3.14.9",
  "com.softwaremill.sttp.client4"         %% "jsoniter"              % "4.0.0-M16",
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core"   % "2.30.7",
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "2.30.7" % "compile-internal",
)

logLevel := Level.Warn

scalacOptions := Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  // "-Werror",
)
