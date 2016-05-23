enablePlugins(ScalaNativePlugin)

name := "sglgears"
description := "gears.c ported to scala using scala.native."

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scala-native" %% "scalalib" % "0.1-SNAPSHOT",
  "org.scala-native" %% "clib" % "0.1-SNAPSHOT",
  "org.scala-native" %% "scalalib" % "0.1-SNAPSHOT",
  "org.scala-native" %% "javalib" % "0.1-SNAPSHOT",
  "org.scala-native" %% "nativelib" % "0.1-SNAPSHOT"
)

nativeVerbose := true

nativeClangOptions := Seq(
  "-O2",
  "-I/usr/local/Cellar/bdw-gc/7.4.2/include",
  "-L/usr/local/Cellar/bdw-gc/7.4.2/lib",
  "-F/System/Library/Frameworks/",
  "-framework", "GLUT", "-framework", "OpenGL", "-framework", "Cocoa"
)
