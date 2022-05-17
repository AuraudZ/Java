import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.*;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.math.Matrix4;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;
import com.jogamp.opengl.util.glsl.ShaderState;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.opengl.util.texture.TextureState;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjData;
import de.javagl.obj.ObjReader;
import de.javagl.obj.ObjUtils;
import jogamp.opengl.util.glsl.GLSLArrayHandler;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

import static com.jogamp.opengl.GL.GL_TRIANGLES;

public class OBJRenderer implements GLEventListener, MouseMotionListener, KeyListener {

    private TextRenderer textRenderer;

    private boolean doRotate = true;
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
    boolean[] move = new boolean[6];

    public OBJRenderer() {
    }

    FloatBuffer timeBuffer;
    int[] vao = new int[1];
    int[] vbo = new int[1];
    int[] ebo = new int[1];
    boolean forward, backward, left, right, reset;
    Texture cubeMapTexture = null;
    Buffer vb;
    float[] triangleVertices = {
            -1.0f, -1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
            0.0f, 1.0f, 0.0f,
    };

    public int vVBO_ID;
    public IntBuffer vVBO;

    int vertShaderID;
    int fragShaderID;

    public int vVAO_ID;
    public IntBuffer vVAO;

    private int shaderProgramID;
    float mouseX = 0.0f;
    float mouseY = 0.0f;
    Camera camera;

    @Override
    public void init(final GLAutoDrawable glad) {

        final GL4bc gl = glad.getGL().getGL4bc();
        textRenderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 24));
        initShaders(gl);
        initBuffers(gl);

    }

    private void initShaders(final GL4bc gl) {
        final ShaderCode vp = ShaderCode.create(gl, GL2ES2.GL_VERTEX_SHADER, this.getClass(),
                "shader", "shader/bin", "shader", true);
        final ShaderCode fp = ShaderCode.create(gl, GL2ES2.GL_FRAGMENT_SHADER, this.getClass(),
                "shader", "shader/bin", "shader", true);
        vp.defaultShaderCustomization(gl, true, true);
        fp.defaultShaderCustomization(gl, true, true);
        final ShaderProgram program = new ShaderProgram();
        program.init(gl);

        program.add(vp);
        program.add(fp);
        if (!program.link(gl, System.out)) {
            System.err.println("Could not link program: ");
        }
        shaderProgramID = program.program();
        vertShaderID = vp.id();
        fragShaderID = fp.id();
    }


    /*
    Inits the VAO and VBO buffers we need to draw with "core" mode OpenGL
     */
    private void initBuffers(final GL4bc gl) {

        FloatBuffer vVertFloatBuffer = Buffers.newDirectFloatBuffer(triangleVertices);
        // Create a new Vertex Array Object in memory and select it (bind)
        vVAO = Buffers.newDirectIntBuffer(1);
        vVBO = Buffers.newDirectIntBuffer(1);

        gl.glGenVertexArrays(1, vVAO);
        vVAO_ID = vVAO.get();

        gl.glGenBuffers(1, vVBO);
        vVBO_ID = vVBO.get();

        gl.glBindVertexArray(vVAO_ID);
        gl.glBindBuffer(GL2ES2.GL_ARRAY_BUFFER, vVBO_ID);
        gl.glBufferData(GL.GL_ARRAY_BUFFER, vVertFloatBuffer.capacity() * Buffers.SIZEOF_FLOAT, vVertFloatBuffer, GL.GL_STATIC_DRAW);


        gl.glVertexAttribPointer(0, 3, GL.GL_FLOAT, false, 3 * 4 /*sizeof(float)*/, 0);
        gl.glEnableVertexAttribArray(0);
        gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);
        gl.glBindVertexArray(0);


    }

    @Override
    public void display(final GLAutoDrawable glad) {
        final GL4bc gl = glad.getGL().getGL4bc();

        gl.glClear(GL4.GL_COLOR_BUFFER_BIT | GL4.GL_DEPTH_BUFFER_BIT);

        gl.glUseProgram(shaderProgramID);

        gl.glBindVertexArray(vVAO_ID);

        gl.glDrawArrays(GL.GL_TRIANGLES, 0, 3);
        System.out.println(gl.glGetError());


        textRenderer.beginRendering(glad.getSurfaceWidth(), glad.getSurfaceHeight());
        textRenderer.setColor(1.0f, 1.0f, 1.0f, 1.0f);

        textRenderer.draw("Hello World", 10, glad.getSurfaceHeight() - 20);
        textRenderer.endRendering();

        System.out.println(gl.glGetError());


    }

    @Override
    public void reshape(final GLAutoDrawable glad, final int x, final int y, final int width,
                        final int height) {


    }


    @Override
    public void dispose(final GLAutoDrawable glad) {
        if (verbose) {
            System.err.println(Thread.currentThread() + " RedSquareES2.dispose: tileRendererInUse "
                    + tileRendererInUse);
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
            camera.processKeyboard(Camera.Movement.FORWARD);
        }
        if (e.getKeyChar() == 's') {
            camera.processKeyboard(Camera.Movement.BACKWARD);
        }
        if (e.getKeyChar() == 'a') {
            camera.processKeyboard(Camera.Movement.LEFT);
        }
        if (e.getKeyChar() == 'd') {
            camera.processKeyboard(Camera.Movement.RIGHT);
        }
        System.out.println(e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> camera.processKeyboard(Camera.Movement.FORWARD, deltaTime);
            case KeyEvent.VK_S -> camera.processKeyboard(Camera.Movement.BACKWARD, deltaTime);
            case KeyEvent.VK_A -> camera.processKeyboard(Camera.Movement.LEFT, deltaTime);
            case KeyEvent.VK_D -> camera.processKeyboard(Camera.Movement.RIGHT, deltaTime);
        }
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
        if (key == 'x') {
            doRotate = !doRotate;
        }
    }

    float lastX;
    float lastY;
    float xoffset;
    float yoffset;

    @Override
    public void mouseDragged(MouseEvent e) {
        xoffset = e.getX() - lastX;
        yoffset = lastY - e.getY();

        lastX = (float) e.getComponent().getWidth() / 2;
        lastY = (float) e.getComponent().getHeight() / 2;
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
        gl.glTexParameteri(GL2ES2.GL_TEXTURE_CUBE_MAP, GL2ES2.GL_TEXTURE_WRAP_S, GL2ES2.GL_CLAMP_TO_EDGE);
        gl.glTexParameteri(GL2ES2.GL_TEXTURE_CUBE_MAP, GL2ES2.GL_TEXTURE_WRAP_T, GL2ES2.GL_CLAMP_TO_EDGE);
        gl.glTexParameteri(GL2ES2.GL_TEXTURE_CUBE_MAP, GL2ES2.GL_TEXTURE_WRAP_R, GL2ES2.GL_CLAMP_TO_EDGE);
        return textureId[0];
    }
}

