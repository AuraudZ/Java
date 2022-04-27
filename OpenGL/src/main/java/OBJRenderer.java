import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.math.VectorUtil;
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

public class OBJRenderer implements GLEventListener, MouseMotionListener, KeyListener {
    private static final float TO_RADIANS = (float) (Math.PI / 180.0f);
    private final GLU glu = new GLU();

    ShaderProgram program = new ShaderProgram();
    ShaderUtil shaderUtil;


    float[] cameraPos = {0.0f, 0.0f, 3.0f};
    float[] cameraFront = {0.0f, 0.0f, -1.0f};
    float[] cameraUp = {0.0f, 1.0f, 0.0f};


    float yaw, pitch;
    Obj obj;
    FloatBuffer vertices;
    FloatBuffer texCoords;
    FloatBuffer normals;
    String cwd = System.getProperty("user.dir");
    boolean forward, backward, left, right, reset;
    private float camX, camZ;

    float dealtaTime = 0.0f;
    TextRenderer textRenderer;
    float lastFrame = 0.0f;

    @Override
    public void init(GLAutoDrawable drawable) {

        textRenderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 24));

        GL2 gl = drawable.getGL().getGL2();
        try {
            InputStream objInputStream = new FileInputStream(cwd + "/src/main/resources/objs/cube.obj");
            obj = ObjUtils.convertToRenderable(ObjReader.read(objInputStream));
            vertices = ObjData.getVertices(obj);
            texCoords = ObjData.getTexCoords(obj, 2);
            normals = ObjData.getNormals(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }



        String vexterShaderPath = cwd + "/vertex.vert";
        String fragmentShaderPath = cwd + "/fragment.frag";

        String shaderBasePath = cwd + "/src/main/resources/shaders/";
        ShaderData shaderData = new ShaderData(gl, shaderBasePath,vexterShaderPath, fragmentShaderPath);



        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);
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

    private CharSequence[][] readShaderSource(String[] s) {
        CharSequence[][] shaderSource = new CharSequence[s.length][];
        for (int i = 0; i < s.length; i++) {
            shaderSource[i] = readShaderSource(s[i]);
        }
        return shaderSource;
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {

        lastFrame = drawable.getAnimator().getLastFPS();

        dealtaTime = drawable.getAnimator().getLastFPS();
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glDisable(GL2.GL_DEPTH_TEST);
        gl.glDisable(GL2.GL_LIGHTING);
        textRenderer.beginRendering(drawable.getSurfaceWidth(), drawable.getSurfaceHeight());
        gl.glColor3f(255, 255, 255);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        textRenderer.draw("FPS: " + nf.format(lastFrame), 10, 10);
        textRenderer.draw("Camera Position: " + nf.format(cameraPos[0]) + " " + nf.format(cameraPos[1]) + " " + nf.format(cameraPos[2]), 10, 30);
        textRenderer.draw("Camera Rotation: " + nf.format(cameraFront[0]) + " " + nf.format(cameraFront[1]) + " " + nf.format(cameraFront[2]), 10, 50);
        textRenderer.endRendering();
        textRenderer.flush();

        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glEnableClientState(GL2ES1.GL_TEXTURE_COORD_ARRAY);
        gl.glTexCoordPointer(2, GL2.GL_FLOAT, 0, texCoords);

        Texture texture = null;
        if (texture == null) {
            try {
                texture = loadTexture(cwd + "/src/main/resources/textures/cube.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        camera();


        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_NORMALIZE);


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
        float xoffset = e.getX() - lastX;
        float yoffset = lastY - e.getY();

        lastX = (float) e.getComponent().getWidth() / 2;
        lastY = (float) e.getComponent().getHeight() / 2;

        float sensitivity = 0.05f;
        xoffset *= sensitivity;
        yoffset *= sensitivity;


        yaw += xoffset;
        pitch += yoffset;

        if (pitch > 89.0f)
            pitch = 89.0f;
        if (pitch < -89.0f)
            pitch = -89.0f;


        // Now set the direction of the camera
        float[] dir = new float[3];

        dir[0] = (float) Math.cos(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));
        dir[1] = (float) Math.sin(Math.toRadians(pitch));
        dir[2] = (float) Math.sin(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));


        // Normalize the result
        float len = (float) Math.sqrt(dir[0] * dir[0] + dir[1] * dir[1] + dir[2] * dir[2]);
        dir[0] /= len;
        dir[1] /= len;
        dir[2] /= len;

        // Set the camera's direction
        cameraFront = dir;

        camX = cameraPos[0];
        camZ = cameraPos[2];
    }

    float cameraSpeed = 5.1f;

    private void camera() {
        float x = cameraPos[0];
        float y = cameraPos[1];
        float z = cameraPos[2];

        if (forward) {
            x += cameraSpeed * cameraFront[0];

            z += cameraSpeed * cameraFront[2];
        }
        if (backward) {
            x -= cameraSpeed * cameraFront[0];

            z -= cameraSpeed * cameraFront[2];
        }
        if (left) {
            // Cross the front vector with the world up vector to get the right vector
            float[] right = new float[3];
            right[0] = cameraFront[1] * cameraUp[2] - cameraFront[2] * cameraUp[1];
            right[1] = cameraFront[2] * cameraUp[0] - cameraFront[0] * cameraUp[2];
            right[2] = cameraFront[0] * cameraUp[1] - cameraFront[1] * cameraUp[0];

            x -= cameraSpeed * right[0];
            y -= cameraSpeed * right[1];
            z -= cameraSpeed * right[2];
        }

        if (right) {
            // Cross the front vector with the world up vector to get the right vector
            float[] right = new float[3];
            right[0] = cameraFront[1] * cameraUp[2] - cameraFront[2] * cameraUp[1];
            right[1] = cameraFront[2] * cameraUp[0] - cameraFront[0] * cameraUp[2];
            right[2] = cameraFront[0] * cameraUp[1] - cameraFront[1] * cameraUp[0];

            x += cameraSpeed * right[0];
            y += cameraSpeed * right[1];
            z += cameraSpeed * right[2];
        }


        cameraPos[0] = x;
        cameraPos[1] = y;
        cameraPos[2] = z;
        float[] posPlusFront = new float[3];

        VectorUtil.addVec3(posPlusFront, cameraFront, cameraPos);

        glu.gluLookAt(x, y, z, posPlusFront[0], posPlusFront[1], posPlusFront[2], 0, 1, 0);


    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        if (key == 'w') forward = true;
        if (key == 's') backward = true;
        if (key == 'a') left = true;
        if (key == 'd') right = true;
        if (key == 'r') reset = true;
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
            reset = false;
        }
    }
}
