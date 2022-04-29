import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.math.VectorUtil;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.awt.TextRenderer;
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

public class OBJRenderer implements GLEventListener, MouseMotionListener, KeyListener {
    private final GLU glu = new GLU();

    ShaderProgram program = new ShaderProgram();
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
        String vertShaderPath = cwd + "/src/main/java/shaders/vertex.glsl";
        String fragShaderPath = cwd + "/src/main/java/shaders/fragment.glsl";

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
        //   ShaderData shaderData = new ShaderData(gl, shaderBasePath,vexterShaderPath, fragmentShaderPath);


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

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        drawable.getAnimator().getUpdateFPSFrames();
        double fps = drawable.getAnimator().getTotalFPS();
        lastFrame = drawable.getAnimator().getLastFPS();
        dealtaTime = drawable.getAnimator().getLastFPS() - lastFrame;
        double currentTime = Instant.now().getEpochSecond();
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glDisable(GL2.GL_DEPTH_TEST);
        gl.glDisable(GL2.GL_LIGHTING);
        gl.glColor3f(255, 255, 255);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);

        float screenWidth = drawable.getSurfaceWidth();
        float screenHeight = drawable.getSurfaceHeight();

        gl.glColor3f(0, 0, 0);
        float[] cameraPos = camera.getPosition();
        float[] cameraFront = camera.getDirection();
        camera.setMove(move);

        camera.move(0.5f, mouseX, mouseY, screenWidth, screenHeight,move);
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

        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture.getTextureObject());
        int vertexShader;
        int fragmentShader;
        vertexShader = gl.glCreateShader(GL2.GL_VERTEX_SHADER);
        gl.glShaderSource(vertexShader, 1, readShaderSource(cwd+"/src/main/resources/shaders/vertex.vert"), null, 0);
        gl.glCompileShader(vertexShader);
        fragmentShader = gl.glCreateShader(GL2.GL_FRAGMENT_SHADER);
        gl.glShaderSource(fragmentShader, 1, readShaderSource(cwd +"/src/main/resources/shaders/fragment.frag"), null, 0);
        int shaderProgram = gl.glCreateProgram();
        Box box = new Box(0.f,0.f,0.f,0.1f,0.1f,0.1f,gl);
        box.draw(gl);
        box.move(camera.front[0],camera.front[1],camera.front[2]);

        gl.glAttachShader(shaderProgram, vertexShader);
        gl.glAttachShader(shaderProgram, fragmentShader);
        gl.glLinkProgram(shaderProgram);
        gl.glUseProgram(shaderProgram);
        gl.glDeleteShader(vertexShader);
        gl.glDeleteShader(fragmentShader);
        gl.glBegin(GL2.GL_QUADS);
        GLUquadric quadric = glu.gluNewQuadric();
        gl.glTranslatef(cameraPos[0], 0, cameraPos[2]);
        glu.gluQuadricDrawStyle(quadric, GLU.GLU_LINE);
        glu.gluSphere(quadric, 1, 32, 32);
        gl.glEnd();
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        textRenderer.beginRendering(drawable.getSurfaceWidth(), drawable.getSurfaceHeight(), true);
        gl.glColor3f(0,0,0);
        textRenderer.draw("FPS: " + fps, 10, 10);
        textRenderer.draw("Camera Position: " + nf.format(cameraPos[0]) + " " + nf.format(cameraPos[1]) + " " + nf.format(cameraPos[2]), 10, 30);
        textRenderer.draw("Camera Rotation: " + nf.format(cameraFront[0]) + " " + nf.format(cameraFront[1]) + " " + nf.format(cameraFront[2]), 10, 50);
        textRenderer.endRendering();
        textRenderer.flush();


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
        if(key == 'v') move[5] = true;
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
        if(key == 'v') {
            move[5] = false;
        }
    }
}
