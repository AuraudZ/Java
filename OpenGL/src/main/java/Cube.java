import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

public class Cube implements GLEventListener {
    private final GLU glu = new GLU();
    private final GLUT glut = new GLUT();
    private float rtri = 0.0f;

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
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);


        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reset The View
        gl.glTranslatef(-0.5f, 0.0f, -6.0f); // Move the triangle
        gl.glRotatef(rtri, 0.0f, 1.0f, 0.0f);


        gl.glBegin(GL2.GL_QUADS);
        // Front Face
        gl.glColor3f(1.0f, 0.0f, 0.0f);     // Red
        gl.glVertex3f(1.0f, 1.0f, 1.0f);   // Top Right Of The Quad (Top)
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);   // Top Left Of The Quad (Top)
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);   // Bottom Left Of The Quad (Top)
        gl.glVertex3f(1.0f, -1.0f, 1.0f);   // Bottom Right Of The Quad (Top)
        gl.glEnd();
        rtri += 0.2f;

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        if (height <= 0)
            height = 1;

        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluPerspective(90.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}
