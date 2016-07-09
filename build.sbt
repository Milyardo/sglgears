enablePlugins(ScalaNativePlugin)

name := "sglgears"
description := "gears.c ported to scala using scala.native."

scalaVersion := "2.11.8"

nativeVerbose := true

nativeClangOptions := Seq(
  "-O2",
  "-I/usr/local/Cellar/bdw-gc/7.4.2/include",
  "-L/usr/local/Cellar/bdw-gc/7.4.2/lib",
  "-F/System/Library/Frameworks/",
  "-framework", "GLUT", "-framework", "OpenGL"
)
