import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.math.VectorUtil;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;
import com.jogamp.opengl.util.glsl.ShaderUtil;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjData;
import de.javagl.obj.ObjReader;
import de.javagl.obj.ObjUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.text.NumberFormat;
import java.time.Instant;

import static com.jogamp.opengl.GL2ES2.GL_FRAGMENT_SHADER;
import static com.jogamp.opengl.GL2ES2.GL_VERTEX_SHADER;
import static com.jogamp.opengl.glu.GLU.*;


public class OBJRenderer implements GLEventListener, MouseMotionListener, KeyListener {

    private Shader program;
    private final GLU glu = new GLU();

    private GL2 gl;
    ShaderUtil shaderUtil;

    Camera camera;

    Obj obj;
    FloatBuffer vertices;
    FloatBuffer texCoords;
    FloatBuffer normals;
    String cwd = System.getProperty("user.dir");
    boolean[] move = new boolean[6];
    boolean forward, backward, left, right, reset;

    float dealtaTime = 0.0f;
    TextRenderer textRenderer;
    float lastFrame = 0.0f;


    @Override
    public void init(GLAutoDrawable drawable) {

        textRenderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 24));
        GL2 gl = drawable.getGL().getGL2();
        camera = new Camera(gl, glu);

        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(1f, 1f, 1f, 0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
    }

    private String[] readShaderSource(String s) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(s));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString().split("\n");
    }

    private float[] vertexData = {
            -1, -1, 1, 0, 0,
            +0, +2, 0, 0, 1,
            +1, -1, 0, 1, 0};

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        float width = drawable.getSurfaceWidth();
        float height = drawable.getSurfaceHeight();
        camera.move(0.5f,mouseX,mouseY,width,height,move);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glColor3f(1, 0, 0);
        glu.gluSphere(glu.gluNewQuadric(), 0.5f, 32, 32);
        gl.glEnd();

        String cameraString = "Camera: " + camera.position[0] + " " + camera.position[1] + " " + camera.position[2];
        String cameraRot = "Rot: " + camera.front[0] + " " + camera.front[1] + " " + camera.front[2];

        textRenderer.beginRendering((int) width, (int) height);
        textRenderer.setColor(Color.BLACK);

        textRenderer.draw(cameraString, 10, 10);
        textRenderer.draw(cameraRot, 10, 30);
        textRenderer.endRendering();
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
        glu.gluPerspective(90.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(0.0, 0.0, 5.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
    }


    float lastX, lastY;

    @Override
    public void mouseDragged(MouseEvent e) {
        float lastX = e.getX();
        float lastY = e.getY();

        mouseX = lastX;
        mouseY = lastY;

    }

    float cameraSpeed = 0.5f;
    float mouseX, mouseY;

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        if (key == 'w') move[0] = true;
        if (key == 's') move[1] = true;
        if (key == 'a') move[2] = true;
        if (key == 'd') move[3] = true;
        if (key == 'r') move[4] = true;
        if (key == 'v') move[5] = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        if (key == 'w') forward = true;
        if (key == 's') backward = true;
        if (key == 'a') left = true;
        if (key == 'd') right = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        if (key == 'w') {
            move[0] = false;
        }
        if (key == 's') {
            move[1] = false;
        }
        if (key == 'a') {
            move[2] = false;
        }
        if (key == 'd') {
            move[3] = false;
        }
        if (key == 'r') {
            move[4] = false;
        }
        if (key == 'v') {
            move[5] = false;
        }
    }


}
