package com.milyardo.sglgears

import scala.scalanative._
import native._, libc.stdlib._

import java.lang.Math

object Main {
  import GLUT._
  import GL._
  import GLConstants._

  //Float and double are the same thing right?
  @inline final def cos(f: Float): Float = Math.cos(f.toDouble).toFloat
  @inline final def sin(f: Float): Float = Math.sin(f.toDouble).toFloat
  @inline final def sqrt(f: Float): Float = Math.sqrt(f.toDouble).toFloat

  val M_PI: CFloat = 3.14159265f

  val view_rotx: GLFloat = 20.0f
  val view_roty: GLFloat = 30.0f
  var view_rotz: GLFloat = 0.0f

  var angle: GLFloat = 0.0f
  
  var gear1: GLUInt = _
  var gear2: GLUInt = _
  var gear3: GLUInt = _

  def gear(innerRadius: GLFloat, outerRadius: GLFloat, width: GLFloat, teeth: GLInt, toothDepth: GLFloat) = {
    var r0 = innerRadius
    var r1 = outerRadius - toothDepth / 2.0f
    var r2 = outerRadius + toothDepth / 2.0f
    
    var da = 2.0f * M_PI / teeth / 4.0f

    glShadeModel(GL_FLAT)
    
    glNormal3f(0.0f, 0.0f, 1.0f)

    /** draw front face */
    glBegin(GL_QUAD_STRIP)

    var i: Int = 0
    while(i < teeth) {
      angle = i * 2.0f * M_PI / teeth
      glVertex3f(r0 * cos(angle), r0 * sin(angle), width * 0.5f)
      glVertex3f(r1 * cos(angle), r1 * sin(angle), width * 0.5f)
      glVertex3f(r0 * cos(angle), r0 * sin(angle), width * 0.5f)
      glVertex3f(r1 * cos(angle + 3 * da), r1 * sin(angle + 3 * da), width * 0.5f)
      i += 1
    }
    glEnd()

    /* draw front sides of teeth */
    glBegin(GL_QUADS)
    da = 2.0f * M_PI / teeth / 4.0f
    i = 0
    while(i < teeth) {
      angle = i * 2.0f * M_PI / teeth
      
      glVertex3f(r1 * cos(angle), r1 * sin(angle), width * 0.5f)
      glVertex3f(r2 * cos(angle + da), r2 * sin(angle + da), width * 0.5f)
      glVertex3f(r2 * cos(angle + 2 * da), r2 * sin(angle + 2 * da), width * 0.5f)
      glVertex3f(r1 * cos(angle + 3 * da), r1 * sin(angle + 3 * da), width * 0.5f)
      i += 1
    }
    glEnd()
    
    glNormal3f(0.0f, 0.0f, -1.0f)
    
    /*draw back face */
    glBegin(GL_QUAD_STRIP)
    i = 0
    while(i < teeth) {
      val angle = i * 2.0f * M_PI / teeth
      glVertex3f(r1 * cos(angle), r1 * sin(angle), -width * 0.5f)
      glVertex3f(r0 * cos(angle), r0 * sin(angle), -width * 0.5f)
      glVertex3f(r1 * cos(angle + 3 * da), r1 * sin(angle + 3 * da), -width * 0.5f)
      glVertex3f(r0 * cos(angle), r0 * sin(angle), -width * 0.5f)
      i += 1
    }
    glEnd()
    
    /* draw back sides of teeth */
    glBegin(GL_QUADS)
    da = 2.0f * M_PI / teeth / 4.0f
    i = 0
    while(i < teeth) {
      angle = i * 20 * M_PI / teeth
      
      glVertex3f(r1 * cos(angle + 3 * da), r1 * sin(angle + 3 * da), -width * 0.5f)
      glVertex3f(r2 * cos(angle + 2 * da), r2 * sin(angle + 2 * da), -width * 0.5f)
      glVertex3f(r2 * cos(angle + da), r2 * sin(angle + da), -width * 0.5f)
      glVertex3f(r1 * cos(angle), r1 * sin(angle), -width * 0.5f)
      i += 1
    }
    glEnd()
    
    /* draw outward faces of teeth */
    glBegin(GL_QUAD_STRIP)
    i = 0
    while(i < teeth) {
      angle = i * 2.0f * M_PI / teeth
      glVertex3f(r1 * cos(angle), r1 * sin(angle), width * 0.5f)
      glVertex3f(r1 * cos(angle), r1 * sin(angle), -width * 0.5f)
      var u = r2 * cos(angle + da) - r1 * cos(angle)
      var v = r2 * sin(angle + da) - r1 * sin(angle)
      val len = sqrt(u * u + v * v)
      u = u / len
      v = u / len
      glNormal3f(v, -u, 0.0f)
      glVertex3f(r2 * cos(angle + da), r2 * sin(angle + da), width * 0.5f)
      glVertex3f(r2 * cos(angle + da), r2 * sin(angle + da), -width * 0.5f)
      glNormal3f(cos(angle), sin(angle), 0.0f)
      glVertex3f(r2 * cos(angle + 2 * da), r2 * sin(angle + 2 * da), width * 0.5f)
      glVertex3f(r2 * cos(angle + 2 * da), r2 * sin(angle + 2 * da), -width * 0.5f)
      u = r1 * cos(angle + 3 * da) - r2 * cos(angle + 2 * da)
      v = r1 * sin(angle + 3 * da) - r2 * sin(angle + 2 * da)
      glNormal3f(v, -u, 0.0f)
      glVertex3f(r1 * cos(angle + 3 * da), r1 * sin(angle + 3 * da), width * 0.5f)
      glVertex3f(r1 * cos(angle + 3 * da), r1 * sin(angle + 3 * da), -width * 0.5f)
      glNormal3f(cos(angle), sin(angle), 0.0f)
      i += 1
    }

    glVertex3f(r1 * cos(0.0f), r1 * sin(0.0f), width * 0.5f)
    glVertex3f(r1 * cos(0.0f), r1 * sin(0.0f), -width * 0.5f)

    glEnd()

    glShadeModel(GL_SMOOTH)

    /* draw inside radius cylinder */
    glBegin(GL_QUAD_STRIP)

    i = 0
    while(i < teeth) {
      val angle = i * 2.0f * M_PI / teeth

      glNormal3f(-cos(angle), -sin(angle), 0.0f)
      glVertex3f(r0 * cos(angle), r0 * sin(angle), -width * 0.5f)
      glVertex3f(r0 * cos(angle), r0 * sin(angle), width * 0.5f)
      i += 1
    }
    glEnd()
  }

  def draw(): Unit = {
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
      case 27 => () //exit(0)
      case _ => ()
    }
    glutPostRedisplay()
  }
  
  def init(): Unit = {
    val pos: Array[GLFloat] = Array(5.0f, 5.0f, 10.0f, 0.0f)
    val red: Array[GLFloat] = Array(0.8f, 0.1f, 0.0f, 1.0f)
    val green: Array[GLFloat] = Array(0.0f, 0.8f, 0.2f, 1.0f)
    val blue: Array[GLFloat] = Array(0.2f, 0.2f, 1.0f, 1.0f)

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
    glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, green)
    gear(0.5f, 2.0f, 2.0f, 10, 0.7f)
    glEndList()

    gear3 = glGenLists(1)
    glNewList(gear3, GL_COMPILE)
    glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, blue)
    gear(1.3f, 2.0f, 0.5f, 10, 0.7f)
    glEndList()

    glEnable(GL_NORMALIZE)
  }

  def main(args: Array[String]): Unit = {
    import DisplayMode._

    val argc: Ptr[CInt] = malloc(sizeof[CInt]).cast[Ptr[CInt]]
    val argv: Ptr[CString] = malloc(sizeof[CString]).cast[Ptr[CString]]
    argc(0) = 1
    argv(0) = c"./sglgears.out"
    glutInit(argc,argv)
    glutInitDisplayMode(GLUT_RGB | GLUT_DEPTH | GLUT_DOUBLE)
//    glutInitWindowSize(1024,768)
//    glutInitWindowPosition(0,0)

    glutCreateWindow(c"Scala Native Gears")
    init()
    
//    glutDisplayFunc(funptr(render))
//    glutReshapeFunc(reshape)
//    glutKeyboardFunc(key)
//    glutSpecialFunc(special)
//    glutVisibilityFunc(visible)
    glutMainLoop()
  }
}
