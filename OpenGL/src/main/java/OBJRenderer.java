import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.*;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.math.Ray;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.TileRendererBase;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;
import com.jogamp.opengl.util.glsl.ShaderState;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import org.joml.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Math;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.text.NumberFormat;

import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.util.GLBuffers.sizeof;
import static java.lang.Thread.sleep;

public class OBJRenderer implements GLEventListener, MouseMotionListener, KeyListener {

    private TextRenderer textRenderer;

    int score = 0;
    FPSAnimator animator;
    private int width;
    private int height;
    private boolean verbose = true;
    private ShaderState st;
    private float deltaTime = 0.0f;
    private String[] cubeMapFiles = {
            "textures/skybox/right.jpg",
            "textures/skybox/left.jpg",
            "textures/skybox/top.jpg",
            "textures/skybox/bottom.jpg",
            "textures/skybox/front.jpg",
            "textures/skybox/back.jpg"
    };
    private TileRendererBase tileRendererInUse = null;

    public OBJRenderer(FPSAnimator animator) {
        this.animator = animator;
    }

    int vertShaderID;
    int fragShaderID;
    int cubeVertShaderID;
    int cubeFragShaderID;

    private int shaderProgramID;

    Camera camera;

    private int[] vbo_handle = new int[1];

    private int[] vao_handle = new int[1];
    Texture cube;
    Texture face;

    int cubeShaderProgramID;
    Matrix4f projection;
    int cubeMap;
    float FPS = 0;

    @Override
    public void init(final GLAutoDrawable glad) {
        final GL4bc gl = glad.getGL().getGL4bc();
        textRenderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 24));
        camera = new Camera(gl);
        width = glad.getSurfaceWidth();
        height = glad.getSurfaceHeight();
        gl.glEnable(GL_TEXTURE_2D);
        gl.glEnable(GL_DEPTH_TEST);
        gl.glDepthFunc(GL_LESS);
        gl.glEnable(GL_FRAMEBUFFER_SRGB);
//    Shader shader = new Shader("triangle","triangle",gl);
        initShaders(gl);
        gl.glEnable(GL_MULTISAMPLE);

        cubeMap = loadCubeMap(gl, cubeMapFiles);
        FPS = animator.getLastFPS();
        if (animator.isAnimating())
            ;
        {
            System.out.println("FPS: " + FPS);
        }
        try {
            cube = loadTexture(gl, "textures/container.jpg");
            face = loadTexture(gl, "textures/face.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        initBuffers(gl);
        lastX = width / 2;
        lastY = height / 2;
        // Convert to MBs
        double texMem = cube.getEstimatedMemorySize() / (1024.0 * 1024);
        System.out.println("Tex Mem Size: " + texMem + " MB");
        gl.glUseProgram(shaderProgramID);

        gl.glUniform1i(gl.glGetUniformLocation(shaderProgramID, "tex"), 0);
        projection = camera.getProjection();
        projection.perspective(
                (float) Math.toRadians(camera.getFov()), (float) width / (float) height, 0.1f, 100.0f);
        FloatBuffer matrixBuffer = Buffers.newDirectFloatBuffer(16);
        projection.get(matrixBuffer);
        gl.glUniformMatrix4fv(
                gl.glGetUniformLocation(shaderProgramID, "projection"), 1, false, matrixBuffer);
        gl.glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
        System.out.println("OpenGL Version: " + gl.glGetString(GL_VERSION));
        System.out.println("OpenGL Renderer: " + gl.glGetString(GL_RENDERER));
        System.out.println("OpenGL Vendor: " + gl.glGetString(GL_VENDOR));
        System.out.println("View Matrix: " + camera.getViewMatrix());
    }

    private Texture loadTexture(GL4bc gl, String fileName) throws IOException {
        Texture texture = TextureIO.newTexture(new File(fileName), true);
        gl.glActiveTexture(gl.GL_TEXTURE0);
        texture.bind(gl);
        gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_S, gl.GL_REPEAT);
        gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_T, gl.GL_REPEAT);
        // set texture filtering parameters
        gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
        gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
        return texture;
    }

    private Vector3f[] cubePositions = {
            new Vector3f(1.0f, 0.0f, 0.0f),
            new Vector3f(-1.0f, 0.0f, 0.0f),
            new Vector3f(0.0f, 1.0f, 0.0f),
            new Vector3f(0.0f, -1.0f, 0.0f),
            new Vector3f(0.0f, 0.0f, 1.0f),
            new Vector3f(0.0f, 0.0f, -1.0f)
    };

    private void initShaders(final GL4bc gl) {
        final ShaderCode vp =
                ShaderCode.create(
                        gl, GL2ES2.GL_VERTEX_SHADER, this.getClass(), "shader", "shader/bin", "triangle", true);
        final ShaderCode fp =
                ShaderCode.create(
                        gl,
                        GL2ES2.GL_FRAGMENT_SHADER,
                        this.getClass(),
                        "shader",
                        "shader/bin",
                        "triangle",
                        true);
        final ShaderCode cubeVp =
                ShaderCode.create(
                        gl, GL2ES2.GL_VERTEX_SHADER, this.getClass(), "shader", "shader/bin", "cubemap", true);
        final ShaderCode cubeFp =
                ShaderCode.create(
                        gl,
                        GL2ES2.GL_FRAGMENT_SHADER,
                        this.getClass(),
                        "shader",
                        "shader/bin",
                        "cubemap",
                        true);

        vp.defaultShaderCustomization(gl, true, true);
        fp.defaultShaderCustomization(gl, true, true);
        cubeFp.defaultShaderCustomization(gl, true, true);
        cubeVp.defaultShaderCustomization(gl, true, true);
        final ShaderProgram program = new ShaderProgram();
        program.init(gl);
        program.add(vp);
        program.add(fp);

        System.out.println("GLSL version: " + gl.glGetString(GL4bc.GL_SHADING_LANGUAGE_VERSION));
        if (!program.link(gl, System.out)) {
            System.err.println("Could not link program: ");
        }
        if (gl.glGetError() != GL4bc.GL_NO_ERROR) {
            System.err.println("Error: " + gl.glGetError());
        }
        shaderProgramID = program.program();
        vertShaderID = vp.id();
        fragShaderID = fp.id();
        cubeVertShaderID = cubeVp.id();
        cubeFragShaderID = cubeFp.id();
    }

    Vector3f[] cubeSizes = {
            new Vector3f(0.5f, 0.5f, 0.5f),
            new Vector3f(0.5f, 0.5f, 0.5f),
            new Vector3f(0.5f, 0.5f, 0.5f),
            new Vector3f(0.5f, 0.5f, 0.5f),
            new Vector3f(0.5f, 0.5f, 0.5f),
            new Vector3f(0.5f, 0.5f, 0.5f),
            new Vector3f(0.5f, 0.5f, 0.5f),
            new Vector3f(0.5f, 0.5f, 0.5f),
            new Vector3f(0.5f, 0.5f, 0.5f),
            new Vector3f(0.5f, 0.5f, 0.5f)
    };

    float[] texArr = {
            // positions      // colors          // texture coords
            0.5f, 0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, // top right
            0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, // bottom right
            -0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, // bottom left
            -0.5f, 0.5f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f // top left
    };

    int[] indices = { // note that we start from 0!
            0, 1, 3, // first triangle
            1, 2, 3 // second triangle
    };

    int[] skyboxVBO;
    int[] skyboxVAO;

    private void initBuffers(final GL4bc gl) {
        gl.glGenVertexArrays(1, vao_handle, 0);
        gl.glBindVertexArray(vao_handle[0]);

        // Create a new Vertex Buffer Object in memory and select it (bind) this is for the square of
        // triangles
        gl.glGenBuffers(1, IntBuffer.wrap(vbo_handle));
        gl.glBindBuffer(GL_ARRAY_BUFFER, vbo_handle[0]);
        gl.glBufferData(GL_ARRAY_BUFFER, texArr.length * 4, FloatBuffer.wrap(texArr), GL_STATIC_DRAW);

        gl.glGenBuffers(1, IntBuffer.wrap(vbo_handle));
        gl.glBindBuffer(GL_ARRAY_BUFFER, vbo_handle[0]);
        gl.glBufferData(
                GL_ARRAY_BUFFER,
                Cube.verticesCube.length * 4,
                FloatBuffer.wrap(Cube.verticesCube),
                GL_STATIC_DRAW);

        gl.glVertexAttribPointer(0, 3, GL_FLOAT, false, 5 * Buffers.SIZEOF_FLOAT, 0);
        gl.glEnableVertexAttribArray(0);
        gl.glVertexAttribPointer(
                1, 2, GL_FLOAT, false, 5 * Buffers.SIZEOF_FLOAT, 3 * Buffers.SIZEOF_FLOAT);
        gl.glEnableVertexAttribArray(1);

        // Skybox VAO, VBO
        // skybox VAO
        skyboxVAO = new int[1];
        gl.glGenVertexArrays(1, skyboxVAO, 0);
        gl.glBindVertexArray(skyboxVAO[0]);

        // skybox VBO
        skyboxVBO = new int[1];
        gl.glGenBuffers(1, skyboxVBO, 0);
        gl.glBindBuffer(GL_ARRAY_BUFFER, skyboxVBO[0]);
        gl.glBufferData(
                GL_ARRAY_BUFFER,
                Cube.skyBoxVerts.length * 4,
                FloatBuffer.wrap(Cube.skyBoxVerts),
                GL_STATIC_DRAW);
        gl.glEnableVertexAttribArray(0);
        gl.glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * Buffers.SIZEOF_FLOAT, 0);
    }

    /*
    Inits the VAO and VBO buffers we need to draw with "core" mode OpenGL
     */
    @Override
    public void display(final GLAutoDrawable glad) {
        final GL4bc gl = glad.getGL().getGL4bc();
        gl.glUseProgram(shaderProgramID);
        gl.glClear(GL4.GL_COLOR_BUFFER_BIT | GL4.GL_DEPTH_BUFFER_BIT);

        FloatBuffer matrixBuffer2 = Buffers.newDirectFloatBuffer(16);

        FloatBuffer matrixBuffer = Buffers.newDirectFloatBuffer(16);
        Matrix4f model = new Matrix4f();
        model.identity();
        model.translate(0, 0, -10);
        model.get(matrixBuffer);
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.identity();
        camera.setPosition(new Vector3f(0, 0, -10));
        viewMatrix.set(camera.getViewMatrix());
        viewMatrix.get(matrixBuffer2);
        gl.glUniformMatrix4fv(
                gl.glGetUniformLocation(shaderProgramID, "model"), 1, false, matrixBuffer);
        gl.glUniformMatrix4fv(
                gl.glGetUniformLocation(shaderProgramID, "view"), 1, false, matrixBuffer2);

        gl.glActiveTexture(GL_TEXTURE0);
        cube.bind(gl);
        gl.glActiveTexture(GL_TEXTURE1);
        face.bind(gl);
        int faceLoc = gl.glGetUniformLocation(shaderProgramID, "face");
        int uniform_texture = gl.glGetUniformLocation(shaderProgramID, "tex");
        int cubeMapLoc = gl.glGetUniformLocation(cubeShaderProgramID, "skybox");
        gl.glUniform1i(uniform_texture, 0);
        gl.glUniform1i(faceLoc, 1);
        gl.glBindVertexArray(vao_handle[0]);

        for (int i = 0; i < cubePositions.length; i++) {
            float angle = 20.0f * i;
            model.identity();
            Vector3f t = new Vector3f(cubePositions[i]);
            model.translate(cubePositions[i]);
            model.scale(0.5f);
            model.rotate((float) Math.toRadians(angle), 1.0f, 0.3f, 0.5f);
            model.get(matrixBuffer);
            gl.glUniformMatrix4fv(
                    gl.glGetUniformLocation(shaderProgramID, "model"), 1, false, matrixBuffer);
            gl.glDrawArrays(GL_TRIANGLES, 0, 36);
        }

        selectCube(camera, cubePositions, cubeSizes);

        // gl.glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);
        gl.glBindVertexArray(0);
        gl.glUseProgram(0);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        gl.glFlush();
        // Ortho matrix for hud
        Matrix4f ortho = new Matrix4f();
        ortho.identity();
        ortho.ortho(0, width, height, 0, -1, 1);
        ortho.get(matrixBuffer);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadMatrixf(matrixBuffer);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glDisable(GL2.GL_DEPTH_TEST);
        gl.glBegin(GL_LINES);
        gl.glColor3f(1, 0, 0);
        gl.glVertex2f(0, 0);
        gl.glVertex2f(width, height);
        gl.glColor3f(0, 1, 0);
        gl.glVertex2f(0, 0);
        gl.glVertex2f(width, 0);
        gl.glColor3f(0, 0, 1);
        gl.glVertex2f(0, 0);
        gl.glVertex2f(0, height);

        gl.glEnd();

    }

    @Override
    public void reshape(
            final GLAutoDrawable glad, final int x, final int y, final int width, final int height) {
        GL4 gl = glad.getGL().getGL4();
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void dispose(final GLAutoDrawable glad) {
        if (verbose) {
            System.err.println(
                    Thread.currentThread() + " RedSquareES2.dispose: tileRendererInUse " + tileRendererInUse);
        }
        final GL2ES2 gl = glad.getGL().getGL2ES2();
        if (!gl.hasGLSL()) {
            return;
        }

        gl.glDetachShader(shaderProgramID, vertShaderID);
        gl.glDetachShader(shaderProgramID, fragShaderID);
        gl.glDeleteProgram(shaderProgramID);
        System.out.println("Deleted shader program " + shaderProgramID);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            camera.processKeyboard(Camera.Movement.FORWARD, true);
        }
        if (e.getKeyChar() == 's') {
            camera.processKeyboard(Camera.Movement.BACKWARD, true);
        }
        if (e.getKeyChar() == 'a') {
            camera.processKeyboard(Camera.Movement.LEFT, true);
        }
        if (e.getKeyChar() == 'd') {
            camera.processKeyboard(Camera.Movement.RIGHT, true);
        }
        if (e.getKeyChar() == 'r') {
            camera.reset(Camera.Movement.RESET);
        }
        if (e.getKeyChar() == 'l') {
            lock = !lock;
        }
        if (e.getKeyChar() == 'e') {
            System.out.println("E");
            camera.setCameraFront(cubePositions[3]);
            camera.setPosition(cubePositions[3]);
        }
        if(e.getKeyChar() == 'z') {
            camera.processKeyboard(Camera.Movement.UP, true);
        }
        if(e.getKeyChar() == 'x') {
            camera.processKeyboard(Camera.Movement.DOWN, true);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    float lastX;
    boolean lock;
    float lastY;
    float xoffset;
    float yoffset;

    @Override
    public void mouseDragged(MouseEvent e) {
        float xpos = e.getX();
        float ypos = e.getY();

        float xoffset = xpos - lastX;
        float yoffset = lastY - ypos; // reversed since y-coordinates range from bottom to top
        lastX = xpos;
        lastY = ypos;
        camera.processMouseMovement(xoffset, yoffset);
    }

    public void selectCube(Camera camera, Vector3f cubePositions[], Vector3f cubeSizes[]) {
        Vector3f dir = new Vector3f();
        camera.getViewMatrix().positiveZ(dir);
        dir.negate();

        for (int i = 0; i < cubePositions.length; i++) {
            Vector3f cubeCenter = new Vector3f(cubePositions[i]);
            Vector2f result = new Vector2f();
            Intersectionf.intersectRayAab(camera.getPosition(), dir, cubeCenter, cubeSizes[i], result);
            if (result.x > 5 && result.y > 5) {
                System.out.println("Selected cube " + i);
                return;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    int loadCubeMap(GL2ES2 gl, String[] filenames) {
        int[] textureId = new int[1];
        gl.glGenTextures(1, textureId, 0);
        gl.glBindTexture(GL2ES2.GL_TEXTURE_CUBE_MAP, textureId[0]);
        int width, height, nrChannels;
        for (int i = 0; i < filenames.length; i++) {
            String filename = filenames[i];
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File(filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (image == null) {
                System.err.println("Failed to load image: " + filename);
                return 0;
            }
            width = image.getWidth();
            height = image.getHeight();
            int[] pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
        }
        gl.glTexParameteri(GL2ES2.GL_TEXTURE_CUBE_MAP, GL2ES2.GL_TEXTURE_MAG_FILTER, GL2ES2.GL_LINEAR);
        gl.glTexParameteri(GL2ES2.GL_TEXTURE_CUBE_MAP, GL2ES2.GL_TEXTURE_MIN_FILTER, GL2ES2.GL_LINEAR);
        gl.glTexParameteri(
                GL2ES2.GL_TEXTURE_CUBE_MAP, GL2ES2.GL_TEXTURE_WRAP_S, GL2ES2.GL_CLAMP_TO_EDGE);
        gl.glTexParameteri(
                GL2ES2.GL_TEXTURE_CUBE_MAP, GL2ES2.GL_TEXTURE_WRAP_T, GL2ES2.GL_CLAMP_TO_EDGE);
        gl.glTexParameteri(
                GL2ES2.GL_TEXTURE_CUBE_MAP, GL2ES2.GL_TEXTURE_WRAP_R, GL2ES2.GL_CLAMP_TO_EDGE);
        return textureId[0];
    }
}
