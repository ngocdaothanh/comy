// Import xsbt-scalate-generator keys; this must be at top of build.sbt, or SBT will complain
import ScalateKeys._

organization := "tv.cntt"

name         := "comy"

version      := "1.7-SNAPSHOT"

scalaVersion := "2.10.1"

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

// Most Scala projects are published to Sonatype, but Sonatype is not default
// and it takes several hours to sync from Sonatype to Maven Central
resolvers += "SonatypeReleases" at "http://oss.sonatype.org/content/repositories/releases/"

libraryDependencies += "tv.cntt" %% "xitrum" % "2.4"

// An implementation of SLF4J must be provided for Xitrum
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.11"

libraryDependencies += "org.mongodb" %  "mongo-java-driver" % "2.11.1"

// xgettext i18n translation key string extractor is a compiler plugin ---------

autoCompilerPlugins := true

addCompilerPlugin("tv.cntt" %% "xgettext" % "1.0")

scalacOptions += "-P:xgettext:xitrum.I18n"

// xitrum.imperatively uses Scala continuation, also a compiler plugin ---------

addCompilerPlugin("org.scala-lang.plugins" % "continuations" % "2.10.1")

scalacOptions += "-P:continuations:enable"

// Template engine for Xitrum --------------------------------------------------

libraryDependencies += "tv.cntt" %% "xitrum-scalate" % "1.1"

// Precompile Scalate
seq(scalateSettings:_*)

scalateTemplateConfig in Compile := Seq(TemplateConfig(
  file("src") / "main" / "scalate",  // See config/scalate.conf
  Seq(),
  Seq(Binding("helper", "xitrum.Action", true))
))

// Put config directory in classpath for easier development --------------------

// For "sbt console"
unmanagedClasspath in Compile <+= (baseDirectory) map { bd => Attributed.blank(bd / "config") }

// For "sbt run"
unmanagedClasspath in Runtime <+= (baseDirectory) map { bd => Attributed.blank(bd / "config") }
