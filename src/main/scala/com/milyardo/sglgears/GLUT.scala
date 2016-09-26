package com.milyardo.sglgears

import scala.scalanative.native._

/**
  * Created by zpowers on 5/15/16.
  */
@extern
@link("glut")
object GLUT {
  final def glutInit(argc: Ptr[CInt], argv: Ptr[Ptr[CChar]]): Unit        = extern
  final def glutInitDisplayMode(mode: DisplayMode.Type): Unit             = extern
  final def glutInitWindowSize(width: CInt, height: CInt): Unit           = extern
  final def glutInitWindowPosition(x: CInt, y: CInt): Unit                = extern
  final def glutCreateWindow(title: CString): CInt                        = extern
  final def glutMainLoop(): Unit                                          = extern
  final def glutDisplayFunc(func: FunctionPtr0[Unit]): Unit               = extern
  final def glutReshapeFunc(func: FunctionPtr2[CInt, CInt, Unit])         = extern
  final def glutKeyboardFunc(func: FunctionPtr3[CChar, CInt, CInt, Unit]) = extern
  final def glutSpecialFunc(func: FunctionPtr3[CInt, CInt, CInt, Unit])   = extern
  final def glutVisibilityFunc(func: FunctionPtr1[CInt, Unit])            = extern
  final def glutIdleFunc(func: FunctionPtr0[Unit])                        = extern
  final def glutSwapBuffers(): Unit                                       = extern
  final def glutPostRedisplay(): Unit                                     = extern
}

object GLUTConstants {

  /** directional keys **/
  final val GLUT_KEY_LEFT      = 100
  final val GLUT_KEY_UP        = 101
  final val GLUT_KEY_RIGHT     = 102
  final val GLUT_KEY_DOWN      = 103
  final val GLUT_KEY_PAGE_UP   = 104
  final val GLUT_KEY_PAGE_DOWN = 105
  final val GLUT_KEY_HOME      = 106
  final val GLUT_KEY_END       = 107
  final val GLUT_KEY_INSERT    = 108

  /** Visibility State **/
  /* Visibility  state. */
  final val GLUT_NOT_VISIBLE = 0
  final val GLUT_VISIBLE     = 1
}

@extern
@link("GL")
object GL {
  type GLBitField = CInt //CUnsignedInt
  type GLFloat    = CFloat
  type GLInt      = CInt
  type GLEnum     = CInt //CUnsignedInt
  type GLVoid     = Unit
  type GLUInt     = CInt //CUnsignedInt
  type GLSizeI    = CInt
  type GLDouble   = CDouble

  final def glBegin(mode: GLEnum): Unit                                           = extern
  final def glClear(mask: GLBitField): Unit                                       = extern
  final def glEnable(cap: GLEnum): Unit                                           = extern
  final def glEnd(): Unit                                                         = extern
  final def glShadeModel(mode: GLEnum): Unit                                      = extern
  final def glNormal3f(nx: GLFloat, ny: GLFloat, nz: GLFloat): Unit               = extern
  final def glVertex3f(x: GLFloat, y: GLFloat, z: GLFloat): Unit                  = extern
  final def glPushMatrix(): Unit                                                  = extern
  final def glPopMatrix(): Unit                                                   = extern
  final def glRotatef(angle: GLFloat, x: GLFloat, y: GLFloat, z: GLFloat): Unit   = extern
  final def glTranslatef(x: GLFloat, y: GLFloat, z: GLFloat): Unit                = extern
  final def glCallList(list: GLUInt): Unit                                        = extern
  final def glLightfv(light: GLEnum, pname: GLEnum, params: Ptr[GLFloat]): Unit   = extern
  final def glGenLists(range: GLSizeI): GLUInt                                    = extern
  final def glNewList(list: GLUInt, mode: GLEnum): Unit                           = extern
  final def glMaterialfv(face: GLEnum, pname: GLEnum, params: Ptr[GLFloat]): Unit = extern
  final def glEndList(): Unit                                                     = extern
  final def glLoadIdentity(): Unit                                                = extern
  final def glViewport(x: GLInt, y: GLInt, width: GLInt, height: GLInt) =
    extern
  final def glMatrixMode(mode: GLEnum): Unit = extern
  final def glFrustum(left: GLDouble,
                      right: GLDouble,
                      bottom: GLDouble,
                      top: GLDouble,
                      zNear: GLDouble,
                      zFar: GLDouble) = extern
}

object GLConstants {
  /* BeginMode */
  final val GL_POINTS         = 0x0000
  final val GL_LINES          = 0x0001
  final val GL_LINE_LOOP      = 0x0002
  final val GL_LINE_STRIP     = 0x0003
  final val GL_TRIANGLES      = 0x0004
  final val GL_TRIANGLE_STRIP = 0x0005
  final val GL_TRIANGLE_FAN   = 0x0006
  final val GL_QUADS          = 0x0007
  final val GL_QUAD_STRIP     = 0x0008
  final val GL_POLYGON        = 0x0009

  // Shade Mode
  final val GL_FLAT   = 0x1D00
  final val GL_SMOOTH = 0x1D01

  /* MaterialParameter */
  final val GL_EMISSION            = 0x1600
  final val GL_SHININESS           = 0x1601
  final val GL_AMBIENT_AND_DIFFUSE = 0x1602
  final val GL_COLOR_INDEXES       = 0x1603

  /* DrawBufferMode */
  final val GL_NONE           = 0
  final val GL_FRONT_LEFT     = 0x0400
  final val GL_FRONT_RIGHT    = 0x0401
  final val GL_BACK_LEFT      = 0x0402
  final val GL_BACK_RIGHT     = 0x0403
  final val GL_FRONT          = 0x0404
  final val GL_BACK           = 0x0405
  final val GL_LEFT           = 0x0406
  final val GL_RIGHT          = 0x0407
  final val GL_FRONT_AND_BACK = 0x0408
  final val GL_AUX0           = 0x0409
  final val GL_AUX1           = 0x040A
  final val GL_AUX2           = 0x040B
  final val GL_AUX3           = 0x040C

  /* ListMode */
  final val GL_COMPILE             = 0x1300
  final val GL_COMPILE_AND_EXECUTE = 0x1301

  /* EnableMode */
  final val GL_CULL_FACE  = 0x0B44
  final val GL_LIGHTING   = 0x0B50
  final val GL_DEPTH_TEST = 0x0B71
  final val GL_NORMALIZE  = 0x0BA1

  /* LightParameter */
  final val GL_AMBIENT               = 0x1200
  final val GL_DIFFUSE               = 0x1201
  final val GL_SPECULAR              = 0x1202
  final val GL_POSITION              = 0x1203
  final val GL_SPOT_DIRECTION        = 0x1204
  final val GL_SPOT_EXPONENT         = 0x1205
  final val GL_SPOT_CUTOFF           = 0x1206
  final val GL_CONSTANT_ATTENUATION  = 0x1207
  final val GL_LINEAR_ATTENUATION    = 0x1208
  final val GL_QUADRATIC_ATTENUATION = 0x1209

  /* LightName */
  final val GL_LIGHT0 = 0x4000
  final val GL_LIGHT1 = 0x4001
  final val GL_LIGHT2 = 0x4002
  final val GL_LIGHT3 = 0x4003
  final val GL_LIGHT4 = 0x4004
  final val GL_LIGHT5 = 0x4005
  final val GL_LIGHT6 = 0x4006
  final val GL_LIGHT7 = 0x4007

  /* AttribMask */
  final val GL_CURRENT_BIT         = 0x00000001
  final val GL_POINT_BIT           = 0x00000002
  final val GL_LINE_BIT            = 0x00000004
  final val GL_POLYGON_BIT         = 0x00000008
  final val GL_POLYGON_STIPPLE_BIT = 0x00000010
  final val GL_PIXEL_MODE_BIT      = 0x00000020
  final val GL_LIGHTING_BIT        = 0x00000040
  final val GL_FOG_BIT             = 0x00000080
  final val GL_DEPTH_BUFFER_BIT    = 0x00000100
  final val GL_ACCUM_BUFFER_BIT    = 0x00000200
  final val GL_STENCIL_BUFFER_BIT  = 0x00000400
  final val GL_VIEWPORT_BIT        = 0x00000800
  final val GL_TRANSFORM_BIT       = 0x00001000
  final val GL_ENABLE_BIT          = 0x00002000
  final val GL_COLOR_BUFFER_BIT    = 0x00004000
  final val GL_HINT_BIT            = 0x00008000
  final val GL_EVAL_BIT            = 0x00010000
  final val GL_LIST_BIT            = 0x00020000
  final val GL_TEXTURE_BIT         = 0x00040000
  final val GL_SCISSOR_BIT         = 0x00080000
  final val GL_ALL_ATTRIB_BITS     = 0x000fffff

  /* MatrixMode */
  final val GL_MODELVIEW  = 0x1700
  final val GL_PROJECTION = 0x1701
  final val GL_TEXTURE    = 0x1702
}

object DisplayMode {
  type Type = CInt //CUnsignedInt
  final val GLUT_RGB: DisplayMode.Type    = 0
  final val GLUT_DOUBLE: DisplayMode.Type = 2
  final val GLUT_DEPTH: DisplayMode.Type  = 16
}
