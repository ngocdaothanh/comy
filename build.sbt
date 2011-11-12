organization := "tv.cntt"

name         := "comy"

version      := "1.4-SNAPSHOT"

scalaVersion := "2.9.1"

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

// Remove this when Netty 4 is released (this must be put before Xitrum below)
libraryDependencies += "org.jboss.netty" % "netty" % "4.0.0.Alpha1-SNAPSHOT" from "http://cloud.github.com/downloads/ngocdaothanh/xitrum/netty-4.0.0.Alpha1-SNAPSHOT.jar"

// For Xitrum
resolvers += "Scala Tools Snapshot" at "http://nexus.scala-tools.org/content/repositories/snapshots"

// Xitrum uses Jerkson: https://github.com/codahale/jerkson
resolvers += "repo.codahale.com"    at "http://repo.codahale.com"

libraryDependencies += "tv.cntt"         %% "xitrum"            % "1.6-SNAPSHOT"

// An implementation of SLF4J must be provided for Xitrum
libraryDependencies += "ch.qos.logback"  %  "logback-classic"   % "1.0.0"

libraryDependencies += "org.mongodb"     %  "mongo-java-driver" % "2.5.3"

addCompilerPlugin("tv.cntt" %% "xitrum-xgettext" % "1.1")

// For "sbt console"
unmanagedClasspath in Compile <+= (baseDirectory) map { bd => Attributed.blank(bd / "config") }

// For "sbt run"
unmanagedClasspath in Runtime <+= (baseDirectory) map { bd => Attributed.blank(bd / "config") }
