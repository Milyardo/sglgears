package com.milyardo.sglgears

import scala.scalanative._
import native._, libc.stdlib._

import java.lang.Math.{sin, cos, sqrt}

object Main {
  import GLUT._
  import GL._
  import GLConstants._

  
  val M_PI = 3.14159265

  val view_rotx: GLFloat = 20.0
  val view_roty: GLFloat = 30.0
  var view_rotz: GLFloat = 0.0

  var angle: GLFloat = 0.0

  var gear1: GLUInt = _
  var gear2: GLUInt = _
  var gear3: GLUInt = _

  def gear(innerRadius: GLFloat,
           outerRadius: GLFloat,
           width: GLFloat,
           teeth: GLInt,
           toothDepth: GLFloat) = {
    var r0 = innerRadius
    var r1 = outerRadius - toothDepth / 2.0
    var r2 = outerRadius + toothDepth / 2.0

    var da = 2.0 * M_PI / teeth / 4.0

    glShadeModel(GL_FLAT)

    glNormal3f(0.0, 0.0, 1.0)

    /** draw front face */
    glBegin(GL_QUAD_STRIP)

    var i: Int = 0
    while (i < teeth) {
      angle = i * 2.0 * M_PI / teeth
      glVertex3f(r0 * cos(angle), r0 * sin(angle), width * 0.5)
      glVertex3f(r1 * cos(angle), r1 * sin(angle), width * 0.5)
      glVertex3f(r0 * cos(angle), r0 * sin(angle), width * 0.5)
      glVertex3f(
          r1 * cos(angle + 3 * da), r1 * sin(angle + 3 * da), width * 0.5)
      i += 1
    }
    glEnd()

    /* draw front sides of teeth */
    glBegin(GL_QUADS)
    da = 2.0 * M_PI / teeth / 4.0
    i = 0
    while (i < teeth) {
      angle = i * 2.0 * M_PI / teeth

      glVertex3f(r1 * cos(angle), r1 * sin(angle), width * 0.5)
      glVertex3f(r2 * cos(angle + da), r2 * sin(angle + da), width * 0.5)
      glVertex3f(
          r2 * cos(angle + 2 * da), r2 * sin(angle + 2 * da), width * 0.5)
      glVertex3f(
          r1 * cos(angle + 3 * da), r1 * sin(angle + 3 * da), width * 0.5)
      i += 1
    }
    glEnd()

    glNormal3f(0.0, 0.0, -1.0)

    /*draw back face */
    glBegin(GL_QUAD_STRIP)
    i = 0
    while (i < teeth) {
      val angle = i * 2.0 * M_PI / teeth
      glVertex3f(r1 * cos(angle), r1 * sin(angle), -width * 0.5)
      glVertex3f(r0 * cos(angle), r0 * sin(angle), -width * 0.5)
      glVertex3f(
          r1 * cos(angle + 3 * da), r1 * sin(angle + 3 * da), -width * 0.5)
      glVertex3f(r0 * cos(angle), r0 * sin(angle), -width * 0.5)
      i += 1
    }
    glEnd()

    /* draw back sides of teeth */
    glBegin(GL_QUADS)
    da = 2.0 * M_PI / teeth / 4.0
    i = 0
    while (i < teeth) {
      angle = i * 20 * M_PI / teeth

      glVertex3f(
          r1 * cos(angle + 3 * da), r1 * sin(angle + 3 * da), -width * 0.5)
      glVertex3f(
          r2 * cos(angle + 2 * da), r2 * sin(angle + 2 * da), -width * 0.5)
      glVertex3f(r2 * cos(angle + da), r2 * sin(angle + da), -width * 0.5)
      glVertex3f(r1 * cos(angle), r1 * sin(angle), -width * 0.5)
      i += 1
    }
    glEnd()

    /* draw outward faces of teeth */
    glBegin(GL_QUAD_STRIP)
    i = 0
    while (i < teeth) {
      angle = i * 2.0 * M_PI / teeth
      glVertex3f(r1 * cos(angle), r1 * sin(angle), width * 0.5)
      glVertex3f(r1 * cos(angle), r1 * sin(angle), -width * 0.5)
      var u = r2 * cos(angle + da) - r1 * cos(angle)
      var v = r2 * sin(angle + da) - r1 * sin(angle)
      val len = sqrt(u * u + v * v)
      u = u / len
      v = u / len
      glNormal3f(v, -u, 0.0)
      glVertex3f(r2 * cos(angle + da), r2 * sin(angle + da), width * 0.5)
      glVertex3f(r2 * cos(angle + da), r2 * sin(angle + da), -width * 0.5)
      glNormal3f(cos(angle), sin(angle), 0.0)
      glVertex3f(
          r2 * cos(angle + 2 * da), r2 * sin(angle + 2 * da), width * 0.5)
      glVertex3f(
          r2 * cos(angle + 2 * da), r2 * sin(angle + 2 * da), -width * 0.5)
      u = r1 * cos(angle + 3 * da) - r2 * cos(angle + 2 * da)
      v = r1 * sin(angle + 3 * da) - r2 * sin(angle + 2 * da)
      glNormal3f(v, -u, 0.0)
      glVertex3f(
          r1 * cos(angle + 3 * da), r1 * sin(angle + 3 * da), width * 0.5)
      glVertex3f(
          r1 * cos(angle + 3 * da), r1 * sin(angle + 3 * da), -width * 0.5)
      glNormal3f(cos(angle), sin(angle), 0.0)
      i += 1
    }

    glVertex3f(r1 * cos(0.0), r1 * sin(0.0), width * 0.5)
    glVertex3f(r1 * cos(0.0), r1 * sin(0.0), -width * 0.5)

    glEnd()

    glShadeModel(GL_SMOOTH)

    /* draw inside radius cylinder */
    glBegin(GL_QUAD_STRIP)

    i = 0
    while (i < teeth) {
      val angle = i * 2.0 * M_PI / teeth

      glNormal3f(-cos(angle), -sin(angle), 0.0)
      glVertex3f(r0 * cos(angle), r0 * sin(angle), -width * 0.5)
      glVertex3f(r0 * cos(angle), r0 * sin(angle), width * 0.5)
      i += 1
    }
    glEnd()
  }

  def draw(): Unit = {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

    glPushMatrix()
    glRotatef(view_rotx, 1.0, 0.0, 0.0)
    glRotatef(view_roty, 0.0, 1.0, 0.0)
    glRotatef(view_rotz, 0.0, 0.0, 1.0)

    glPushMatrix()
    glTranslatef(-3.0, -2.0, 0.0)
    glRotatef(angle, 0.0, 0.0, 1.0)
    glCallList(gear1)
    glPopMatrix()

    glPushMatrix()
    glTranslatef(3.1, -2.0, 0.0)
    glRotatef(-2.0 * angle - 9.0, 0.0, 0.0, 1.0)
    glCallList(gear2)
    glPopMatrix()

    glPushMatrix()
    glTranslatef(-3.1, 4.2, 0.0)
    glRotatef(-2.0 * angle - 25.0, 0.0, 0.0, 1.0)
    glCallList(gear3)
    glPopMatrix()

    glPopMatrix()

    glutSwapBuffers()
  }

  def idle(): Unit = {
    angle += 2.0
    glutPostRedisplay()
  }

  def key(k: CChar, x: CInt, y: CInt): Unit = {
    k match {
      case 'z' => view_rotz += 5.0
      case 'Z' => view_rotz -= 5.0
      case 27 => () //exit(0)
      case _ => ()
    }
    glutPostRedisplay()
  }

  def init(): Unit = {
    //Create pointers until better array support
    val pos = malloc(sizeof[GLFloat] * 4).cast[Ptr[GLFloat]]
    pos(0) = 5.0
    pos(1) = 5.0
    pos(2) = 10.0
    pos(3) = 0.0

    val red = malloc(sizeof[GLFloat] * 4).cast[Ptr[GLFloat]]
    red(0) = 0.8
    red(1) = 0.1
    red(2) = 0.0
    red(3) =1.0

    val green = malloc(sizeof[GLFloat] * 4).cast[Ptr[GLFloat]]
    green(0) = 0.0
    green(1) = 0.8
    green(2) = 0.2
    green(3) = 1.0

    val blue = malloc(sizeof[GLFloat] * 4).cast[Ptr[GLFloat]]

    blue(0) = 0.2
    blue(1) = 0.2
    blue(2) = 1.0
    blue(3) = 1.0

    glLightfv(GL_LIGHT0, GL_POSITION, pos)
    glEnable(GL_CULL_FACE)
    glEnable(GL_LIGHTING)
    glEnable(GL_LIGHT0)
    glEnable(GL_DEPTH_TEST)

    /* make the gears */
    gear1 = glGenLists(1)
    glNewList(gear1, GL_COMPILE)
    glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, red)
    gear(1.0, 4.0, 1.0, 20, 0.7)
    glEndList()

    gear2 = glGenLists(1)
    glNewList(gear2, GL_COMPILE)
    glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, green)
    gear(0.5, 2.0, 2.0, 10, 0.7)
    glEndList()

    gear3 = glGenLists(1)
    glNewList(gear3, GL_COMPILE)
    glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, blue)
    gear(1.3, 2.0, 0.5, 10, 0.7)
    glEndList()

    glEnable(GL_NORMALIZE)
  }

  def main(args: Array[String]): Unit = {
    import DisplayMode._

    val argc: Ptr[CInt] = malloc(sizeof[CInt]).cast[Ptr[CInt]]
    val argv: Ptr[CString] = malloc(sizeof[CString]).cast[Ptr[CString]]
    argc(0) = 1
    argv(0) = c"./sglgears.out"
    glutInit(argc, argv)
    glutInitDisplayMode(GLUT_RGB | GLUT_DEPTH | GLUT_DOUBLE)
    glutInitWindowSize(1024,768)
    glutInitWindowPosition(0,0)

    glutCreateWindow(c"Scala Native Gears")
    init()
    draw()
//    glutDisplayFunc(funptr(render))
//    glutReshapeFunc(reshape)
//    glutKeyboardFunc(key)
//    glutSpecialFunc(special)
//    glutVisibilityFunc(visible)
    glutMainLoop()
  }
}
