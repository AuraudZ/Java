import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjData;
import de.javagl.obj.ObjReader;
import de.javagl.obj.ObjUtils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class OBJRenderer implements GLEventListener, MouseMotionListener, KeyListener {
    private final GLU glu = new GLU();
    private float rtri = 0.0f;

    private float camX, camZ;

    float yaw , pitch;
    Obj obj;


    FloatBuffer vertices;
    FloatBuffer texCoords;
    FloatBuffer normals;



    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        String cwd =  System.getProperty("user.dir");
        File vertexShader = new File("shaders/default.vs");
        File fragmentShader = new File("shaders/default.fs");

        try {
            InputStream objInputStream = new FileInputStream(cwd+ "/src/main/resources/objs/cube.obj");
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
        camera();
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
        float x = (float) e.getX();
        float y = (float) e.getY();
        int width = e.getComponent().getWidth();
        int height = e.getComponent().getHeight();
        int dev_x,dev_y;
        dev_x = (int) ((width/2)-x);
        dev_y = (int) ((height/2)-y);

        /* apply the changes to pitch and yaw*/
        yaw+=(float)dev_x/10.0;
        pitch+=(float)dev_y/10.0;

    }

    private void camera() {
        if(pitch>=70)
            pitch = 70;
        if(pitch<=-60)
            pitch=-60;

        if(forward) {
            camX += (float) Math.sin(Math.toRadians(yaw)) * 0.1f;
        }
        if(backward) {
            camX -= (float) Math.sin(Math.toRadians(yaw)) * 0.1f;
        }
        if(left) {
            camZ += (float) Math.cos(Math.toRadians(yaw)) * 0.1f;
        }
        if(right) {
            camZ -= (float) Math.cos(Math.toRadians(yaw)) * 0.1f;
        }

        GL2 gl = GLContext.getCurrentGL().getGL2();
        gl.glRotatef(-pitch,1.0f,0.0f,0.0f); // Along X axis
        gl.glRotatef(-yaw,0.0f,1.0f,0.0f);    //Along Y axis
        gl.glTranslatef(-camX,0.0f,-camZ);  //new code

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        if(key == 'w'){

        }
    }
    boolean forward,backward,left,right;
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                forward = true;
                break;
            case KeyEvent.VK_S:
                backward = true;
                break;
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }



}
