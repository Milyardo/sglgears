package com.milyardo.sglgears

import scala.scalanative._
import native._, stdlib._

object Main {
  import GLUT._
  import GLUTConstants._
  import GL._
  import GLConstants._
  import math._

  val M_PI = 3.14159265f

  var view_rotx: GLFloat = 20.0f
  var view_roty: GLFloat = 30.0f
  var view_rotz: GLFloat = 0.0f

  var angle: GLFloat = 0.0f

  var gear1: GLUInt = _
  var gear2: GLUInt = _
  var gear3: GLUInt = _

  final def gear(innerRadius: GLFloat,
           outerRadius: GLFloat,
           width: GLFloat,
           teeth: GLInt,
           toothDepth: GLFloat) = {
    val r0 = innerRadius
    val r1 = outerRadius - toothDepth / 2.0f
    val r2 = outerRadius + toothDepth / 2.0f

    var da = 2.0f * M_PI / teeth / 4.0f

    glShadeModel(GL_FLAT)

    glNormal3f(0.0f, 0.0f, 1.0f)

    /** draw front face */
    glBegin(GL_QUAD_STRIP)

    var i: Int = 0
    while (i < teeth) {
      angle = i * 2.0f * M_PI / teeth
      glVertex3f(r0 * cosf(angle), r0 * sinf(angle), width * 0.5f)
      glVertex3f(r1 * cosf(angle), r1 * sinf(angle), width * 0.5f)
      glVertex3f(r0 * cosf(angle), r0 * sinf(angle), width * 0.5f)
      glVertex3f(r1 * cosf(angle + 3 * da), r1 * sinf(angle + 3 * da), width * 0.5f)
      i += 1
    }
    glEnd()

    /* draw front sides of teeth */
    glBegin(GL_QUADS)
    da = 2.0f * M_PI / teeth / 4.0f
    i = 0
    while (i < teeth) {
      angle = i * 2.0f * M_PI / teeth

      glVertex3f(r1 * cosf(angle), r1 * sinf(angle), width * 0.5f)
      glVertex3f(r2 * cosf(angle + da), r2 * sinf(angle + da), width * 0.5f)
      glVertex3f(r2 * cosf(angle + 2 * da), r2 * sinf(angle + 2 * da), width * 0.5f)
      glVertex3f(r1 * cosf(angle + 3 * da), r1 * sinf(angle + 3 * da), width * 0.5f)
      i += 1
    }
    glEnd()

    glNormal3f(0.0f, 0.0f, -1.0f)

    /*draw back face */
    glBegin(GL_QUAD_STRIP)
    i = 0
    while (i < teeth) {
      angle = i * 2.0f * M_PI / teeth
      glVertex3f(r1 * cosf(angle), r1 * sinf(angle), -width * 0.5f)
      glVertex3f(r0 * cosf(angle), r0 * sinf(angle), -width * 0.5f)
      glVertex3f(r1 * cosf(angle + 3 * da), r1 * sinf(angle + 3 * da), -width * 0.5f)
      glVertex3f(r0 * cosf(angle), r0 * sinf(angle), -width * 0.5f)
      i += 1
    }
    glEnd()

    /* draw back sides of teeth */
    glBegin(GL_QUADS)
    da = 2.0f * M_PI / teeth / 4.0f
    i = 0
    while (i < teeth) {
      angle = i * 20 * M_PI / teeth

      glVertex3f(r1 * cosf(angle + 3 * da), r1 * sinf(angle + 3 * da), -width * 0.5f)
      glVertex3f(r2 * cosf(angle + 2 * da), r2 * sinf(angle + 2 * da), -width * 0.5f)
      glVertex3f(r2 * cosf(angle + da), r2 * sinf(angle + da), -width * 0.5f)
      glVertex3f(r1 * cosf(angle), r1 * sinf(angle), -width * 0.5f)
      i += 1
    }
    glEnd()

    /* draw outward faces of teeth */
    glBegin(GL_QUAD_STRIP)
    i = 0
    while (i < teeth) {
      angle = i * 2.0f * M_PI / teeth
      glVertex3f(r1 * cosf(angle), r1 * sinf(angle), width * 0.5f)
      glVertex3f(r1 * cosf(angle), r1 * sinf(angle), -width * 0.5f)
      var u = r2 * cosf(angle + da) - r1 * cosf(angle)
      var v = r2 * sinf(angle + da) - r1 * sinf(angle)
      val len = sqrtf(u * u + v * v)
      u = u / len
      v = u / len
      glNormal3f(v, -u, 0.0f)
      glVertex3f(r2 * cosf(angle + da), r2 * sinf(angle + da), width * 0.5f)
      glVertex3f(r2 * cosf(angle + da), r2 * sinf(angle + da), -width * 0.5f)
      glNormal3f(cosf(angle), sinf(angle), 0.0f)
      glVertex3f(r2 * cosf(angle + 2 * da), r2 * sinf(angle + 2 * da), width * 0.5f)
      glVertex3f(r2 * cosf(angle + 2 * da), r2 * sinf(angle + 2 * da), -width * 0.5f)
      u = r1 * cosf(angle + 3 * da) - r2 * cosf(angle + 2 * da)
      v = r1 * sinf(angle + 3 * da) - r2 * sinf(angle + 2 * da)
      glNormal3f(v, -u, 0.0f)
      glVertex3f(r1 * cosf(angle + 3 * da), r1 * sinf(angle + 3 * da), width * 0.5f)
      glVertex3f(r1 * cosf(angle + 3 * da), r1 * sinf(angle + 3 * da), -width * 0.5f)
      glNormal3f(cosf(angle), sinf(angle), 0.0f)
      i += 1
    }

    glVertex3f(r1 * cosf(0.0f), r1 * sinf(0.0f), width * 0.5f)
    glVertex3f(r1 * cosf(0.0f), r1 * sinf(0.0f), -width * 0.5f)

    glEnd()

    glShadeModel(GL_SMOOTH)

    /* draw inside radius cylinder */
    glBegin(GL_QUAD_STRIP)

    i = 0
    while (i < teeth) {
      val angle = i * 2.0f * M_PI / teeth

      glNormal3f(-cosf(angle), -sinf(angle), 0.0f)
      glVertex3f(r0 * cosf(angle), r0 * sinf(angle), -width * 0.5f)
      glVertex3f(r0 * cosf(angle), r0 * sinf(angle), width * 0.5f)
      i += 1
    }
    glEnd()
  }

  final def draw(): Unit = {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

    glPushMatrix()
    glRotatef(view_rotx, 1.0f, 0.0f, 0.0f)
    glRotatef(view_roty, 0.0f, 1.0f, 0.0f)
    glRotatef(view_rotz, 0.0f, 0.0f, 1.0f)

    glPushMatrix()
    glTranslatef(-3.0f, -2.0f, 0.0f)
    glRotatef(angle, 0.0f, 0.0f, 1.0f)
    glCallList(gear1)
    glPopMatrix()

    glPushMatrix()
    glTranslatef(3.1f, -2.0f, 0.0f)
    glRotatef(-2.0f * angle - 9.0f, 0.0f, 0.0f, 1.0f)
    glCallList(gear2)
    glPopMatrix()

    glPushMatrix()
    glTranslatef(-3.1f, 4.2f, 0.0f)
    glRotatef(-2.0f * angle - 25.0f, 0.0f, 0.0f, 1.0f)
    glCallList(gear3)
    glPopMatrix()

    glPopMatrix()

    glutSwapBuffers()
  }

  def idle(): Unit = {
    angle += 2.0f
    glutPostRedisplay()
  }

  def key(k: CChar, x: CInt, y: CInt): Unit = {
    k match {
      case 'z' => view_rotz += 5.0f
      case 'Z' => view_rotz -= 5.0f
      case 27  => () //exit(0)
      case _   => ()
    }
    glutPostRedisplay()
  }

  final def reshape(width: Int, height: Int): Unit = {
    val h: GLFloat = height / width

    glViewport(0, 0, width, height)
    glMatrixMode(GL_PROJECTION)
    glLoadIdentity()
    glFrustum(-1.0, 1.0, -h, h, 5.0, 60.0)
    glMatrixMode(GL_MODELVIEW)
    glLoadIdentity()
    glTranslatef(0.0f, 0.0f, -40.0f)
  }

  final def init(): Unit = {
    //Create pointers until better array support
    val pos = malloc(sizeof[GLFloat] * 4).cast[Ptr[GLFloat]]
    pos(0) = 5.0f
    pos(1) = 5.0f
    pos(2) = 10.0f
    pos(3) = 0.0f

    val red = malloc(sizeof[GLFloat] * 4).cast[Ptr[GLFloat]]
    red(0) = 0.8f
    red(1) = 0.1f
    red(2) = 0.0f
    red(3) = 1.0f

    val green = malloc(sizeof[GLFloat] * 4).cast[Ptr[GLFloat]]
    green(0) = 0.0f
    green(1) = 0.8f
    green(2) = 0.2f
    green(3) = 1.0f

    val blue = malloc(sizeof[GLFloat] * 4).cast[Ptr[GLFloat]]

    blue(0) = 0.2f
    blue(1) = 0.2f
    blue(2) = 1.0f
    blue(3) = 1.0f

    glLightfv(GL_LIGHT0, GL_POSITION, pos)
    glEnable(GL_CULL_FACE)
    glEnable(GL_LIGHTING)
    glEnable(GL_LIGHT0)
    glEnable(GL_DEPTH_TEST)

    /* make the gears */
    gear1 = glGenLists(1)
    glNewList(gear1, GL_COMPILE)
    glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, red)
    gear(1.0f, 4.0f, 1.0f, 20, 0.7f)
    glEndList()

    gear2 = glGenLists(1)
    glNewList(gear2, GL_COMPILE)
    //glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, green)
    gear(0.5f, 2.0f, 2.0f, 10, 0.7f)
    glEndList()

    gear3 = glGenLists(1)
    glNewList(gear3, GL_COMPILE)
    glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, blue)
    gear(1.3f, 2.0f, 0.5f, 10, 0.7f)
    glEndList()

    glEnable(GL_NORMALIZE)
  }

  final def visible(vis: CInt): Unit = {
    if (vis == GLUT_VISIBLE)
      glutIdleFunc(() => idle())
    else
      glutIdleFunc(() => ())
  }

  final def special(k: CInt, x: CInt, y: CInt): Unit = {
    k match {
      case GLUT_KEY_UP =>
        view_rotx += 5.0f
      case GLUT_KEY_DOWN =>
        view_rotx -= 5.0f
      case GLUT_KEY_LEFT =>
        view_roty += 5.0f
      case GLUT_KEY_RIGHT =>
        view_roty -= 5.0f
      case _ => ()
    }
    glutPostRedisplay()
  }

  def main(args: Array[String]): Unit = {
    import DisplayMode._

    val argc: Ptr[CInt]    = malloc(sizeof[CInt]).cast[Ptr[CInt]]
    val argv: Ptr[CString] = malloc(sizeof[CString]).cast[Ptr[CString]]
    argc(0) = 1
    argv(0) = c"./sglgears.out"
    glutInit(argc, argv)
    glutInitDisplayMode(GLUT_RGB | GLUT_DEPTH | GLUT_DOUBLE)

    glutCreateWindow(c"Scala Native Gears")
    init()

    glutDisplayFunc(() => draw())
    glutReshapeFunc((x: CInt, y: CInt) => reshape(x, y))
    glutKeyboardFunc((k: CChar, x: CInt, y: CInt) => key(k, x, y))
    glutSpecialFunc((k: CInt, x: CInt, y: CInt) => special(k, x, y))
    glutVisibilityFunc((state: CInt) => visible(state))
    glutMainLoop()
  }
}
