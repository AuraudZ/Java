import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjData;
import de.javagl.obj.ObjReader;
import de.javagl.obj.ObjUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class OBJRenderer implements GLEventListener, MouseMotionListener, KeyListener {
  private static final float TO_RADIANS = (float) (Math.PI / 180.0f);
  private final GLU glu = new GLU();
  Camera camera = new Camera();
  float yaw, pitch;
  Obj obj;
  FloatBuffer vertices;
  FloatBuffer texCoords;
  FloatBuffer normals;
  String cwd = System.getProperty("user.dir");
  boolean forward, backward, left, right;
  private float camX, camZ;

  @Override
  public void init(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();

    File vertexShader = new File("shaders/default.vs");
    File fragmentShader = new File("shaders/default.fs");

    try {
      InputStream objInputStream = new FileInputStream(cwd + "/src/main/resources/objs/cube.obj");
      obj = ObjUtils.convertToRenderable(ObjReader.read(objInputStream));
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
  public void dispose(GLAutoDrawable drawable) {}

  @Override
  public void display(GLAutoDrawable drawable) {

    GL2 gl = drawable.getGL().getGL2();
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
    gl.glLoadIdentity();
    camera();
    gl.glEnableClientState(GL2ES1.GL_TEXTURE_COORD_ARRAY);
    gl.glTexCoordPointer(2, GL2.GL_FLOAT, 0, texCoords);
    gl.glPushMatrix();

    Texture texture = null;
    if (texture == null) {
      try {
        texture = loadTexture(cwd + "/src/main/resources/textures/cube.png");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    texture.enable(gl);
    texture.bind(gl);

    gl.glBegin(GL2.GL_QUADS);
    gl.glTexCoord2f(0.0f, 0.0f);
    gl.glVertex3f(-50.0f, -5.0f, -50.0f);
    gl.glTexCoord2f(25.0f, 0.0f);
    gl.glVertex3f(50.0f, -5.0f, -50.0f);
    gl.glTexCoord2f(25.0f, 25.0f);
    gl.glVertex3f(50.0f, -5.0f, 50.0f);
    gl.glTexCoord2f(0.0f, 25.0f);
    gl.glVertex3f(-50.0f, -5.0f, 50.0f);

    texture.disable(gl);
    gl.glEnd();
    gl.glDisable(GL2.GL_TEXTURE_2D);
    gl.glPopMatrix();

    gl.glPushMatrix();
    gl.glEnable(GL2.GL_LIGHTING);
    gl.glEnable(GL2.GL_LIGHT0);
    gl.glEnable(GL2.GL_NORMALIZE);

    float[] ambientLight = {0.1f, 5.f, 0.f, 0f}; // weak RED ambient
    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambientLight, 0);

    gl.glBegin(GL2.GL_TRIANGLES);
    for (int i = 0; i < vertices.capacity(); i += 3) {
      gl.glColor3d(1.0, 1.0, 1.0);
      gl.glNormal3f(normals.get(i), normals.get(i + 1), normals.get(i + 2));
      gl.glVertex3f(vertices.get(i), vertices.get(i + 1), vertices.get(i + 2));
    }
    gl.glEnd();
    gl.glPopMatrix();
  }

  private Texture loadTexture(String file) throws GLException, IOException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    ImageIO.write(ImageIO.read(new File(file)), "png", os);
    InputStream fis = new ByteArrayInputStream(os.toByteArray());
    return TextureIO.newTexture(fis, true, TextureIO.PNG);
  }

  @Override
  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    final GL2 gl = drawable.getGL().getGL2();
    if (height <= 0) height = 1;
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
    float width = e.getComponent().getWidth();
    float height = e.getComponent().getHeight();
    float angleX = 0.0f;
    float angleY = 0.0f;
    float x = e.getX();
    float y = e.getY();
    x -= width / 2;
    y -= height / 2;
    // Calculate the angle of the mouse movement
    angleX = (float) (x * 180.0 / width);
    angleY = (float) (y * 180.0 / height);
    // Rotate the camera
    pitch = pitch + angleX;
    yaw = yaw + angleY;
  }

  private void camera() {
    if (pitch >= 70) pitch = 70;
    if (pitch <= -60) pitch = -60;
    if (forward) {
      camX = (float) (camX + Math.sin(Math.toRadians(yaw)) * 0.1);
      camZ = (float) (camZ - Math.cos(Math.toRadians(yaw)) * 0.1);
    }
    if (backward) {
      camX = (float) (camX - Math.sin(Math.toRadians(yaw)) * 0.1);
      camZ = (float) (camZ + Math.cos(Math.toRadians(yaw)) * 0.1);
    }
    if (left) {
      camX = (float) (camX + Math.sin(Math.toRadians(yaw - 90)) * 0.1);
      camZ = (float) (camZ - Math.cos(Math.toRadians(yaw - 90)) * 0.1);
    }
    if (right) {
      camX = (float) (camX + Math.sin(Math.toRadians(yaw + 90)) * 0.1);
      camZ = (float) (camZ - Math.cos(Math.toRadians(yaw + 90)) * 0.1);
    }

    GL2 gl = GLContext.getCurrentGL().getGL2();
    gl.glRotatef(-pitch, 1.0f, 0.0f, 0.0f); // Along X axis
    gl.glRotatef(-yaw, 0.0f, 1.0f, 0.0f); // Along Y axis
    gl.glTranslatef(-camX, 1, -camZ);
  }

  @Override
  public void mouseMoved(MouseEvent e) {

    //        Convert x and y to cords from the center of the scree n

  }

  @Override
  public void keyTyped(KeyEvent e) {
    char key = e.getKeyChar();
    if (key == 'w') forward = true;
    if (key == 's') backward = true;
    if (key == 'a') left = true;
    if (key == 'd') right = true;

    System.out.println(key);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    char key = e.getKeyChar();
    if (key == 'w') forward = false;
    if (key == 's') backward = false;
    if (key == 'a') left = false;
    if (key == 'd') right = false;

    System.out.println(key);
  }

  @Override
  public void keyReleased(KeyEvent e) {
    char key = e.getKeyChar();
    if (key == 'w') {
      forward = false;
    }
    if (key == 's') {
      backward = false;
    }
    if (key == 'a') {
      left = false;
    }
    if (key == 'd') {
      right = false;
    }
    if (key == 'r') {
      reset();
    }
  }

  void reset() {
    // Reset the camera
    camX = 0.0f;
    camZ = 0.0f;
    pitch = 0.0f;
    yaw = 0.0f;
  }

  static class Camera {
    Vector2f u;
    Vector2f v;

    Camera() {
      u = new Vector2f(1, 0);
      v = new Vector2f(0, 1);
    }

    Camera(Vector2f u, Vector2f v) {
      this.u = u;
      this.v = v;
    }
  }

  static class Vector2f {
    float x, y;

    Vector2f(float x, float y) {
      this.x = x;
      this.y = y;
    }
  }
}
