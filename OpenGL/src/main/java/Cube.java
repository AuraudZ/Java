import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

public class Cube implements GLEventListener {
  private final GLU glu = new GLU();

  @Override
  public void init(GLAutoDrawable drawable) {
    final GL2 gl = drawable.getGL().getGL2();
    gl.glShadeModel(GL2.GL_SMOOTH);
    gl.glClearColor(0f, 0f, 0f, 0f);
    gl.glClearDepth(1.0f);
    gl.glEnable(GL2.GL_DEPTH_TEST);
    gl.glDepthFunc(GL2.GL_LEQUAL);
    gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
  }

  @Override
  public void dispose(GLAutoDrawable drawable) {}

  @Override
  public void display(GLAutoDrawable drawable) {
    final GL2 gl = drawable.getGL().getGL2();

    gl.glShadeModel(GL2.GL_SMOOTH);
    gl.glClearColor(0f, 0f, 0f, 0f);
    gl.glClearDepth(1.0f);
    gl.glEnable(GL2.GL_DEPTH_TEST);
    gl.glDepthFunc(GL2.GL_LEQUAL);
    gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

    gl.glBegin(GL2.GL_POLYGON_MODE);

    gl.glColor3f(1.0f, 0.0f, 0.0f);
    gl.glVertex3f(0.5f, -0.5f, -0.5f); // P1 is red
    gl.glColor3f(0.0f, 1.0f, 0.0f);
    gl.glVertex3f(0.5f, 0.5f, -0.5f); // P2 is green
    gl.glColor3f(0.0f, 0.0f, 1.0f);
    gl.glVertex3f(-0.5f, 0.5f, -0.5f); // P3 is blue
    gl.glColor3f(1.0f, 0.0f, 1.0f);
    gl.glVertex3f(-0.5f, -0.5f, -0.5f); // P4 is purple
    gl.glEnd();
    gl.glFlush();
    drawable.swapBuffers();
  }

  @Override
  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
}
