package com.milyardo.sglgears

import scala.scalanative.native._

/**
  * Created by zpowers on 5/15/16.
  */
@extern
object GLUT {
  final def glutInit(argc: Ptr[CInt], argv: Ptr[Ptr[CChar]]): Unit               = extern
  final def glutInitDisplayMode(mode: DisplayMode.Type): Unit                    = extern
  final def glutInitWindowSize(width: CInt, height: CInt): Unit                  = extern
  final def glutInitWindowPosition(x: CInt, y: CInt): Unit                       = extern
  final def glutCreateWindow(title: CString): CInt                               = extern
  final def glutMainLoop(): Unit                                                 = extern
  final def glutDisplayFunc(func: CFunctionPtr0[Unit]): Unit                     = extern
  final def glutReshapeFunc(func: CFunctionPtr2[CInt, CInt, Unit]): Unit         = extern
  final def glutKeyboardFunc(func: CFunctionPtr3[CChar, CInt, CInt, Unit]): Unit = extern
  final def glutSpecialFunc(func: CFunctionPtr3[CInt, CInt, CInt, Unit]): Unit   = extern
  final def glutVisibilityFunc(func: CFunctionPtr1[CInt, Unit]): Unit            = extern
  final def glutIdleFunc(func: CFunctionPtr0[Unit]): Unit                        = extern
  final def glutSwapBuffers(): Unit                                              = extern
  final def glutPostRedisplay(): Unit                                            = extern
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
object GL {
  type GLBitField = CUnsignedInt
  type GLFloat    = CFloat
  type GLInt      = CInt
  type GLEnum     = CUnsignedInt
  type GLVoid     = Unit
  type GLUInt     = CUnsignedInt
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
  final def glViewport(x: GLInt, y: GLInt, width: GLInt, height: GLInt): Unit =
    extern
  final def glMatrixMode(mode: GLEnum): Unit = extern
  final def glFrustum(left: GLDouble,
                      right: GLDouble,
                      bottom: GLDouble,
                      top: GLDouble,
                      zNear: GLDouble,
                      zFar: GLDouble): Unit = extern
}

object GLConstants {
  /* BeginMode */
  final val GL_POINTS         = 0x0000.toUInt
  final val GL_LINES          = 0x0001.toUInt
  final val GL_LINE_LOOP      = 0x0002.toUInt
  final val GL_LINE_STRIP     = 0x0003.toUInt
  final val GL_TRIANGLES      = 0x0004.toUInt
  final val GL_TRIANGLE_STRIP = 0x0005.toUInt
  final val GL_TRIANGLE_FAN   = 0x0006.toUInt
  final val GL_QUADS          = 0x0007.toUInt
  final val GL_QUAD_STRIP     = 0x0008.toUInt
  final val GL_POLYGON        = 0x0009.toUInt

  // Shade Mode
  final val GL_FLAT   = 0x1D00.toUInt
  final val GL_SMOOTH = 0x1D01.toUInt

  /* MaterialParameter */
  final val GL_EMISSION            = 0x1600.toUInt
  final val GL_SHININESS           = 0x1601.toUInt
  final val GL_AMBIENT_AND_DIFFUSE = 0x1602.toUInt
  final val GL_COLOR_INDEXES       = 0x1603.toUInt

  /* DrawBufferMode */
  final val GL_NONE           = 0.toUInt
  final val GL_FRONT_LEFT     = 0x0400.toUInt
  final val GL_FRONT_RIGHT    = 0x0401.toUInt
  final val GL_BACK_LEFT      = 0x0402.toUInt
  final val GL_BACK_RIGHT     = 0x0403.toUInt
  final val GL_FRONT          = 0x0404.toUInt
  final val GL_BACK           = 0x0405.toUInt
  final val GL_LEFT           = 0x0406.toUInt
  final val GL_RIGHT          = 0x0407.toUInt
  final val GL_FRONT_AND_BACK = 0x0408.toUInt
  final val GL_AUX0           = 0x0409.toUInt
  final val GL_AUX1           = 0x040A.toUInt
  final val GL_AUX2           = 0x040B.toUInt
  final val GL_AUX3           = 0x040C.toUInt

  /* ListMode */
  final val GL_COMPILE             = 0x1300.toUInt
  final val GL_COMPILE_AND_EXECUTE = 0x1301.toUInt

  /* EnableMode */
  final val GL_CULL_FACE  = 0x0B44.toUInt
  final val GL_LIGHTING   = 0x0B50.toUInt
  final val GL_DEPTH_TEST = 0x0B71.toUInt
  final val GL_NORMALIZE  = 0x0BA1.toUInt

  /* LightParameter */
  final val GL_AMBIENT               = 0x1200.toUInt
  final val GL_DIFFUSE               = 0x1201.toUInt
  final val GL_SPECULAR              = 0x1202.toUInt
  final val GL_POSITION              = 0x1203.toUInt
  final val GL_SPOT_DIRECTION        = 0x1204.toUInt
  final val GL_SPOT_EXPONENT         = 0x1205.toUInt
  final val GL_SPOT_CUTOFF           = 0x1206.toUInt
  final val GL_CONSTANT_ATTENUATION  = 0x1207.toUInt
  final val GL_LINEAR_ATTENUATION    = 0x1208.toUInt
  final val GL_QUADRATIC_ATTENUATION = 0x1209.toUInt

  /* LightName */
  final val GL_LIGHT0 = 0x4000.toUInt
  final val GL_LIGHT1 = 0x4001.toUInt
  final val GL_LIGHT2 = 0x4002.toUInt
  final val GL_LIGHT3 = 0x4003.toUInt
  final val GL_LIGHT4 = 0x4004.toUInt
  final val GL_LIGHT5 = 0x4005.toUInt
  final val GL_LIGHT6 = 0x4006.toUInt
  final val GL_LIGHT7 = 0x4007.toUInt

  /* AttribMask */
  final val GL_CURRENT_BIT         = 0x00000001.toUInt
  final val GL_POINT_BIT           = 0x00000002.toUInt
  final val GL_LINE_BIT            = 0x00000004.toUInt
  final val GL_POLYGON_BIT         = 0x00000008.toUInt
  final val GL_POLYGON_STIPPLE_BIT = 0x00000010.toUInt
  final val GL_PIXEL_MODE_BIT      = 0x00000020.toUInt
  final val GL_LIGHTING_BIT        = 0x00000040.toUInt
  final val GL_FOG_BIT             = 0x00000080.toUInt
  final val GL_DEPTH_BUFFER_BIT    = 0x00000100.toUInt
  final val GL_ACCUM_BUFFER_BIT    = 0x00000200.toUInt
  final val GL_STENCIL_BUFFER_BIT  = 0x00000400.toUInt
  final val GL_VIEWPORT_BIT        = 0x00000800.toUInt
  final val GL_TRANSFORM_BIT       = 0x00001000.toUInt
  final val GL_ENABLE_BIT          = 0x00002000.toUInt
  final val GL_COLOR_BUFFER_BIT    = 0x00004000.toUInt
  final val GL_HINT_BIT            = 0x00008000.toUInt
  final val GL_EVAL_BIT            = 0x00010000.toUInt
  final val GL_LIST_BIT            = 0x00020000.toUInt
  final val GL_TEXTURE_BIT         = 0x00040000.toUInt
  final val GL_SCISSOR_BIT         = 0x00080000.toUInt
  final val GL_ALL_ATTRIB_BITS     = 0x000fffff.toUInt

  /* MatrixMode */
  final val GL_MODELVIEW  = 0x1700.toUInt
  final val GL_PROJECTION = 0x1701.toUInt
  final val GL_TEXTURE    = 0x1702.toUInt
}

object DisplayMode {
  type Type = CUnsignedInt
  final val GLUT_RGB: DisplayMode.Type    = 0.toUInt
  final val GLUT_DOUBLE: DisplayMode.Type = 2.toUInt
  final val GLUT_DEPTH: DisplayMode.Type  = 16.toUInt
}
