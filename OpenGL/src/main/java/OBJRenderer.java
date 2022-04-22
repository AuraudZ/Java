import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import de.javagl.obj.*;
import de.javagl.obj.ObjReader;
import de.javagl.obj.ObjUtils;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class OBJRenderer implements GLEventListener, MouseMotionListener {
    private final GLU glu = new GLU();
    private float rtri = 0.0f;
    private float xrot = 0.0f;
    private float yrot = 0.0f;
    private float zrot = 0.0f;
    Obj obj;


    FloatBuffer vertices;
    FloatBuffer texCoords;
    FloatBuffer normals;



    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        File vertexShader = new File("shaders/default.vs");
        File fragmentShader = new File("shaders/default.fs");

        try {
            InputStream objInputStream = new FileInputStream("C:\\Users\\aubte\\Desktop\\cube2.obj");
            obj = ObjUtils.convertToRenderable(
                    ObjReader.read(objInputStream));
            IntBuffer indices = ObjData.getFaceVertexIndices(obj);
            vertices = ObjData.getVertices(obj);
            texCoords = ObjData.getTexCoords(obj, 2);
            normals = ObjData.getNormals(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        gl.glRotatef(rtri, xrot, yrot, zrot);
        gl.glBegin(GL2.GL_TRIANGLES);


        for (int i = 0; i < vertices.capacity() - 3; i++) {
            gl.glColor3f(1.0f, 0.0f, 0.0f);
            gl.glVertex3f(vertices.get(i), vertices.get(i + 1), vertices.get(i + 2));
            gl.glNormal3fv(normals);
        }
        gl.glEnd();
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
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(0.0, 0.0, 5.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Convert e.getX() and e.getY() to a float between -1 and 1 and apply that to a rotation in to allow up and down roation
        float x = (float) e.getX() / (float) e.getComponent().getWidth() * 2 - 1;
        float y = (float) e.getY() / (float) e.getComponent().getHeight() * 2 - 1;
        float z = (float) e.getY() / (float) e.getComponent().getHeight() * 2 - 1 + e.getX() / (float) e.getComponent().getWidth() * 2 - 1;
        xrot += y;
        yrot += x;
        zrot += z;


        rtri += 0.5f;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}