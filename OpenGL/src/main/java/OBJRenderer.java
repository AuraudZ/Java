import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.*;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.math.Matrix4;
import com.jogamp.opengl.util.GLArrayDataServer;
import com.jogamp.opengl.util.PMVMatrix;
import com.jogamp.opengl.util.TileRendererBase;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;
import com.jogamp.opengl.util.glsl.ShaderState;
import com.jogamp.opengl.util.texture.TextureState;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjData;
import de.javagl.obj.ObjReader;
import de.javagl.obj.ObjUtils;
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
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

public class OBJRenderer implements GLEventListener, MouseMotionListener, KeyListener {
    private ShaderState st;
    private ShaderState cubeMapSt;
    private PMVMatrix pmvMatrix;
    private GLUniformData pmvMatrixUniform;
    private GLUniformData timeUni;
    private GLUniformData cubeMapUniform;
    private GLArrayDataServer vertices;
    private GLArrayDataServer skyBoxVertices;
    private TextRenderer textRenderer;

    private long millisOffset;
    FloatBuffer OBJvertices;
    FloatBuffer texCoords;
    FloatBuffer normals;
    IntBuffer indices;


    Obj obj;
    private long t0;
    private int swapInterval = 0;
    private float aspect = 1.0f;
    private boolean doRotate = true;
    private boolean verbose = true;
    private boolean clearBuffers = false;
    private float deltaTime = 0.0f;
    private float time = 0.0f;
    private Hud hud;
    private String[] cubeMapFiles = {
            "textures/skybox/right.jpg",
            "textures/skybox/left.jpg",
            "textures/skybox/top.jpg",
            "textures/skybox/bottom.jpg",
            "textures/skybox/front.jpg",
            "textures/skybox/back.jpg"
    };
    private TileRendererBase tileRendererInUse = null;
    private boolean doRotateBeforePrinting;
    boolean[] move = new boolean[6];

    public OBJRenderer() {
        this.swapInterval = 60;
    }

    FloatBuffer timeBuffer;

    boolean forward, backward, left, right, reset;

    public void setAspect(final float aspect) {
        this.aspect = aspect;
    }

    float mouseX = 0.0f;
    float mouseY = 0.0f;
    Camera camera;

    @Override
    public void init(final GLAutoDrawable glad) {
        final GL4bc gl = glad.getGL().getGL4bc();

        final ShaderCode cubeMapVP = ShaderCode.create(gl, GL2ES2.GL_VERTEX_SHADER, this.getClass(),
                "shader", "shader/bin", "cubemap", true);
        final ShaderCode cubeMapFP = ShaderCode.create(gl, GL2ES2.GL_FRAGMENT_SHADER, this.getClass(),
                "shader", "shader/bin", "cubemap", true);
        hud = new Hud(gl);


        pmvMatrix = new PMVMatrix();
        pmvMatrix.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        pmvMatrix.glLoadIdentity();
        pmvMatrix.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        pmvMatrix.glLoadIdentity();

        skyBoxVertices = GLArrayDataServer.createGLSL("aPos", 3, GL.GL_FLOAT, false, 4, GL.GL_STATIC_DRAW);
        FloatBuffer skyBoxVerticesBuffer = Buffers.newDirectFloatBuffer(Cube.skyboxVertices);
        skyBoxVertices.put(skyBoxVerticesBuffer);
        skyBoxVertices.seal(gl, true);
        skyBoxVertices.enableBuffer(gl, false);

        cubeMapVP.defaultShaderCustomization(gl, true, true);
        cubeMapFP.defaultShaderCustomization(gl, true, true);



        cubeMapSt = new ShaderState();
        final ShaderProgram cubeMapProgram = new ShaderProgram();
        cubeMapProgram.add(cubeMapVP);
        cubeMapProgram.add(cubeMapFP);
        cubeMapSt.attachShaderProgram(gl, cubeMapProgram, true);


        gl.glEnable(GL.GL_DEPTH_TEST);
        cubeMapSt.useProgram(gl, false);
        t0 = System.currentTimeMillis();
        camera = new Camera(gl);
        if (verbose) {
            System.err.println(Thread.currentThread() + " RedSquareES2.init FIN");
        }

    }

    @Override
    public void display(final GLAutoDrawable glad) {
        final GL4bc gl = glad.getGL().getGL4bc();

        if (!gl.hasGLSL()) {
            return;
        }

        int mat4Location;
        cubeMapSt.useProgram(gl, true);
        mat4Location = cubeMapSt.getUniformLocation(gl, "u_MVPMatrix");


        FloatBuffer fb = Buffers.newDirectFloatBuffer(16);
        new Matrix4f().perspective((float) Math.toRadians(45.0f), 1.0f, 0.01f, 100.0f)
                .lookAt(0.0f, 0.0f, 10.0f,
                        0.0f, 0.0f, 0.0f,
                        0.0f, 1.0f, 0.0f).get(fb);
        gl.glUniformMatrix4fv(mat4Location, 1, false, fb);



    }

    @Override
    public void reshape(final GLAutoDrawable glad, final int x, final int y, final int width,
                        final int height) {
        final GL4bc gl = glad.getGL().getGL4bc();
        final float aspect = (float) width / (float) height;
        pmvMatrix.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        pmvMatrix.glLoadIdentity();
        pmvMatrix.gluPerspective(45.0f, aspect, 0.1f, 100.0f);
        pmvMatrix.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        pmvMatrix.glLoadIdentity();

        pmvMatrix.glTranslatef(0.0f, 0.0f, -5.0f);
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
        cubeMapSt.destroy(gl);
        cubeMapSt = null;
        pmvMatrix = null;
        if (verbose) {
            System.err.println(Thread.currentThread() + " RedSquareES2.dispose FIN");
        }
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

