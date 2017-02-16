enablePlugins(ScalaNativePlugin)

name := "sglgears"
description := "gears.c ported to scala using scala.native."

scalaVersion := "2.11.8"

nativeClangOptions ++= Seq(
  //"-O2",
  "-F/Applications/Xcode.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX10.12.sdk/System/Library/Frameworks/",
  "-framework", "GLUT", "-framework", "OpenGL"
)
