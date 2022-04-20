import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

import java.util.Arrays;

public class OBJRenderer implements GLEventListener {
    private final GLU glu = new GLU();
    OBJ obj;
    private float rtri = 0.0f;


    @Override
    public void init(GLAutoDrawable drawable) {
        OBJParser parser = new OBJParser();
       obj = parser.parse("C:\\Users\\aubte\\Desktop\\Cube.obj");


        final GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel( GL2.GL_SMOOTH );
        gl.glClearColor( 0f, 0f, 0f, 0f );
        gl.glClearDepth( 1.0f );
        gl.glEnable( GL2.GL_DEPTH_TEST );
        gl.glDepthFunc( GL2.GL_LEQUAL );
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST );
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reset The View
        gl.glTranslatef( -0.5f,0.0f,-6.0f ); // Move the triangle
        gl.glRotatef( rtri, 0.0f, 1.0f, 0.0f );
        gl.glBegin( GL2.GL_TRIANGLES );
        for(Vertex3 v : obj.vertices) {
            gl.glColor3f( 0.0f, 1.0f, 0.0f );
            System.out.println(v.x + " " + v.y + " " + v.z);
            gl.glVertex3f( v.x, v.y, v.z );
        }
        gl.glEnd();
        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        if ( height <= 0 )
            height = 1;
        final float h = ( float ) width / ( float ) height;
        gl.glViewport( 0, 0, width, height );
        gl.glMatrixMode( GL2.GL_PROJECTION );
        gl.glLoadIdentity();
        glu.gluPerspective( 45.0f, h, 1.0, 20.0 );
        gl.glMatrixMode( GL2.GL_MODELVIEW );
        gl.glLoadIdentity();
        glu.gluLookAt( 0.0, 0.0, 5.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0 );
    }

}
