import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.*;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.math.Matrix4;
import com.jogamp.opengl.util.GLArrayDataClient;
import com.jogamp.opengl.util.GLArrayDataServer;
import com.jogamp.opengl.util.PMVMatrix;
import com.jogamp.opengl.util.TileRendererBase;
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

import static com.jogamp.opengl.GL.GL_TRIANGLES;

public class OBJRenderer implements GLEventListener, MouseMotionListener, KeyListener {
    private PMVMatrix pmvMatrix;
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

    private  GLArrayDataClient vertexBuffer;
    private ShaderState st;
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
    boolean[] move = new boolean[6];

    public OBJRenderer() {
        this.swapInterval = 60;
    }

    FloatBuffer timeBuffer;

    boolean forward, backward, left, right, reset;
    Texture cubeMapTexture = null;

    public void setAspect(final float aspect) {
        this.aspect = aspect;
    }

    float mouseX = 0.0f;
    float mouseY = 0.0f;
    Camera camera;

    @Override
    public void init(final GLAutoDrawable glad) {
        final GL4bc gl = glad.getGL().getGL4bc();
        textRenderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 24));

        hud = new Hud(gl);
        //initShaders(gl);
/*
        st.useProgram(gl, true);
        vertexBuffer = GLArrayDataClient.createGLSL("mgl_Vertex", 3, gl.GL_FLOAT, false, 4);
        {
            // Fill them up
            FloatBuffer verticeb = (FloatBuffer)vertexBuffer.getBuffer();
            verticeb.put(-2);  verticeb.put(  2);  verticeb.put( 0);
            verticeb.put( 2);  verticeb.put(  2);  verticeb.put( 0);
            verticeb.put(-2);  verticeb.put( -2);  verticeb.put( 0);
            verticeb.put( 2);  verticeb.put( -2);  verticeb.put( 0);
        }
        vertexBuffer.seal(gl, true);
        vertexBuffer.bindBuffer(gl,true);

        try {
            cubeMapTexture = TextureIO.newTexture(new File("textures/skybox/right.jpg"), true);
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
        gl.glBindTexture(GL.GL_TEXTURE_2D, cubeMapTexture.getTextureObject(gl));
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP_TO_EDGE);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP_TO_EDGE);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);

*/


        t0 = System.currentTimeMillis();
        camera = new Camera(gl);
        if (verbose) {
            System.err.println(Thread.currentThread() + " RedSquareES2.init FIN");
        }
        gl.glClearColor(0, 0, 0, 1);
        gl.glEnable(GL2ES2.GL_DEPTH_TEST);
        //st.useProgram(gl, false);

    }

    private void initShaders(final GL4bc gl) {
        st = new ShaderState();
        final ShaderCode vp = ShaderCode.create(gl, GL2ES2.GL_VERTEX_SHADER, this.getClass(),
                "shader", "shader/bin", "shader", true);
        final ShaderCode fp = ShaderCode.create(gl, GL2ES2.GL_FRAGMENT_SHADER, this.getClass(),
                "shader", "shader/bin", "shader", true);
        vp.defaultShaderCustomization(gl, true, true);
        fp.defaultShaderCustomization(gl, true, true);

        final ShaderProgram program = new ShaderProgram();
        program.add(vp);
        program.add(fp);

    //    st.attachShaderProgram(gl, program, true);
   //     st.useProgram(gl, false);
    }

    @Override
    public void display(final GLAutoDrawable glad) {
        final GL4bc gl = glad.getGL().getGL4bc();

        textRenderer.beginRendering(glad.getSurfaceWidth(), glad.getSurfaceHeight());
        textRenderer.setColor(1.0f, 1.0f, 1.0f, 1.0f);

        textRenderer.draw("Hello World", 10, glad.getSurfaceHeight() - 20);
        textRenderer.endRendering();
        if (!gl.hasGLSL()) {
            return;
        }

        //t.useProgram(gl, true);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        gl.glEnable(GL.GL_CULL_FACE);
      //
        //  st.useProgram(gl, false);

        gl.glClearColor(1,1,1,1);
        gl.glBegin(GL4bc.GL_QUADS);
        gl.glColor3f(0, 1, 1);
        gl.glVertex3f(-2, 2, 0);
        gl.glColor3f(0, 1, 1);
        gl.glVertex3f(2, 2, 0);
        gl.glColor3f(0, 1, 1);
        gl.glVertex3f(2, -2, 0);
        gl.glColor3f(0, 1, 1);
        gl.glVertex3f(-2, -2, 0);
        gl.glEnd();






    }

    @Override
    public void reshape(final GLAutoDrawable glad, final int x, final int y, final int width,
                        final int height) {
        final GL4bc gl = glad.getGL().getGL4bc();
        final float aspect = (float) width / (float) height;
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
        //st.destroy(gl);
        st = null;
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

