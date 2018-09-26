enablePlugins(ScalaNativePlugin)

name := "sglgears"
description := "gears.c ported to scala using scala.native."

scalaVersion := "2.11.12"

nativeCompileOptions ++= Seq(
  "-O2"
)

nativeLinkingOptions ++= Seq(
  "-framework", "GLUT", "-framework", "OpenGL"
)
