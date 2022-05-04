import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.*;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.util.GLArrayDataServer;
import com.jogamp.opengl.util.PMVMatrix;
import com.jogamp.opengl.util.TileRendererBase;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;
import com.jogamp.opengl.util.glsl.ShaderState;
import de.javagl.obj.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

public class OBJRenderer implements GLEventListener, MouseMotionListener, KeyListener {
    private ShaderState st;
    private PMVMatrix pmvMatrix;
    private GLUniformData pmvMatrixUniform;
    private GLUniformData timeUni;
    private GLArrayDataServer vertices;
    private TextRenderer textRenderer;

    private long millisOffset;
    FloatBuffer OBJvertices;
    FloatBuffer texCoords;
    FloatBuffer normals;
    IntBuffer indices;

    private GLArrayDataServer colors;
    Obj obj;
    private long t0;
    private int swapInterval = 0;
    private float aspect = 1.0f;
    private boolean doRotate = true;
    private boolean verbose = true;
    private boolean clearBuffers = true;
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
        textRenderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 24));
        try {
            InputStream is = new FileInputStream("./models/cube.obj");
            obj = ObjUtils.convertToRenderable(
                    ObjReader.read(is));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        indices = ObjData.getFaceVertexIndices(obj);
        texCoords = ObjData.getTexCoords(obj, 2);
        OBJvertices = ObjData.getVertices(obj);


        if (verbose) {
            System.err.println(Thread.currentThread() + " RedSquareES2.init: tileRendererInUse "
                    + tileRendererInUse);
        }
        final GL2ES2 gl = glad.getGL().getGL2ES2();

        if (verbose) {
            System.err.println("RedSquareES2 init on " + Thread.currentThread());
            System.err.println("Chosen GLCapabilities: " + glad.getChosenGLCapabilities());
            System.err.println("INIT GL IS: " + gl.getClass().getName());
            System.err.println(JoglVersion.getGLStrings(gl, null, false).toString());
        }
        if (!gl.hasGLSL()) {
            System.err.println("No GLSL available, no rendering.");
            return;
        }
        st = new ShaderState();
        st.setVerbose(true);
        final ShaderCode vp0 = ShaderCode.create(gl, GL2ES2.GL_VERTEX_SHADER, this.getClass(),
                "shader", "shader/bin", "RedSquareShader", true);
        final ShaderCode fp0 = ShaderCode.create(gl, GL2ES2.GL_FRAGMENT_SHADER, this.getClass(),
                "shader", "shader/bin", "RedSquareShader", true);
        vp0.defaultShaderCustomization(gl, true, true);
        fp0.defaultShaderCustomization(gl, true, true);
        final ShaderProgram sp0 = new ShaderProgram();
        sp0.add(gl, vp0, System.err);
        sp0.add(gl, fp0, System.err);
        st.attachShaderProgram(gl, sp0, true);

        // setup mgl_PMVMatrix
        pmvMatrix = new PMVMatrix();
        pmvMatrix.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        pmvMatrix.glLoadIdentity();
        pmvMatrix.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        pmvMatrix.glLoadIdentity();

        timeBuffer = Buffers.newDirectFloatBuffer(1);
        pmvMatrixUniform = new GLUniformData("mgl_PMVMatrix", 4, 4, pmvMatrix.glGetPMvMatrixf()); // P,
        timeUni = new GLUniformData("iGlobalTime", 0.0f);

        st.ownUniform(pmvMatrixUniform);
        st.uniform(gl, pmvMatrixUniform);

     //   st.ownUniform(timeUni);

        // Allocate Vertex Array
        vertices = GLArrayDataServer.createGLSL("mgl_Vertex", 3, GL.GL_FLOAT, false, 4,
                GL.GL_STATIC_DRAW);
        vertices.put(OBJvertices);
        vertices.seal(gl, true);
        st.ownAttribute(vertices, true);
        vertices.enableBuffer(gl, false);

        // OpenGL Render Settings
        gl.glEnable(GL.GL_DEPTH_TEST);
        st.useProgram(gl, false);

        t0 = System.currentTimeMillis();
        if (verbose) {
            System.err.println(Thread.currentThread() + " RedSquareES2.init FIN");
        }

        camera = new Camera(gl, pmvMatrix);
        camera.setMove(move);
        millisOffset = System.currentTimeMillis();
    }

    @Override
    public void display(final GLAutoDrawable glad) {
        final long t1 = System.currentTimeMillis();
        final GL2ES2 gl = glad.getGL().getGL2ES2();
        int width = glad.getSurfaceWidth();
        int height = glad.getSurfaceHeight();

        timeUni.setData((System.currentTimeMillis() - millisOffset) / 1000.0f);
       // st.uniform(gl, timeUni);
        //System.out.println("Render Matrix: "+ pmvMatrix.toString());
        if (clearBuffers) {
            if (null != tileRendererInUse) {
                gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
            } else {
                gl.glClearColor(0, 0, 0, 0);
            }
            gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        }
        if (!gl.hasGLSL()) {
            return;
        }
        camera.move(1,mouseX,mouseY,width,height);
        camera.update();
        st.useProgram(gl, true);
        // One rotation every four seconds
        textRenderer.setColor(Color.white);
        textRenderer.beginRendering(width,height,true);
        textRenderer.draw("Camera Position: " + Arrays.toString(camera.getPosition()), 10, height - 20);
        textRenderer.draw("Camera Direction: " + Arrays.toString(camera.getDirection()), 10, height - 40);
        textRenderer.endRendering();
        pmvMatrix.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        pmvMatrix.glLoadIdentity();
        pmvMatrix.glTranslatef(0, 0, -10);
        // pmvMatrix.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        // camera.move(1, mouseX, mouseY, width, height, move);

        if (doRotate) {
            final float ang = ((t1 - t0) * 360.0F) / 4000.0F;
            pmvMatrix.glRotatef(ang, 0, 0, 1);
            pmvMatrix.glRotatef(ang, 0, 1, 0);
        }
        st.uniform(gl, pmvMatrixUniform);

        // Draw a square
        vertices.enableBuffer(gl, true);

        gl.glDrawArrays(GL.GL_TRIANGLES, 0, indices.capacity());
        vertices.enableBuffer(gl, false);
        st.useProgram(gl, false);
    }

    @Override
    public void reshape(final GLAutoDrawable glad, final int x, final int y, final int width,
            final int height) {
        final GL2ES2 gl = glad.getGL().getGL2ES2();
        gl.setSwapInterval(swapInterval);
        reshapeImpl(gl, x, y, width, height, width, height);
    }



    void reshapeImpl(final GL2ES2 gl, final int tileX, final int tileY, final int tileWidth,
            final int tileHeight, final int imageWidth, final int imageHeight) {
        if (verbose) {
            System.err.println(Thread.currentThread() + " RedSquareES2.reshape " + tileX + "/"
                    + tileY + " " + tileWidth + "x" + tileHeight + " of " + imageWidth + "x"
                    + imageHeight + ", swapInterval " + swapInterval + ", drawable 0x"
                    + Long.toHexString(gl.getContext().getGLDrawable().getHandle())
                    + ", tileRendererInUse " + tileRendererInUse);
        }
        // Thread.dumpStack();
        if (!gl.hasGLSL()) {
            return;
        }

        st.useProgram(gl, true);
        // Set location in front of camera
        pmvMatrix.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        pmvMatrix.glLoadIdentity();

        // compute projection parameters 'normal' perspective
        final float fovy = 45f;
        final float aspect2 = ((float) imageWidth / (float) imageHeight) / aspect;
        final float zNear = 1f;
        final float zFar = 100f;

        // compute projection parameters 'normal' frustum
        final float top = (float) Math.tan(fovy * ((float) Math.PI) / 360.0f) * zNear;
        final float bottom = -1.0f * top;
        final float left = aspect2 * bottom;
        final float right = aspect2 * top;
        final float w = right - left;
        final float h = top - bottom;

        // compute projection parameters 'tiled'
        final float l = left + tileX * w / imageWidth;
        final float r = l + tileWidth * w / imageWidth;
        final float b = bottom + tileY * h / imageHeight;
        final float t = b + tileHeight * h / imageHeight;

        pmvMatrix.glFrustumf(l, r, b, t, zNear, zFar);
        // pmvMatrix.glOrthof(-4.0f, 4.0f, -4.0f, 4.0f, 1.0f, 100.0f);
        st.uniform(gl, pmvMatrixUniform);
        st.useProgram(gl, false);

        System.err.println(Thread.currentThread() + " RedSquareES2.reshape FIN");
        System.out.println(doRotate);
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
        st.destroy(gl);
        st = null;
        pmvMatrix = null;
        if (verbose) {
            System.err.println(Thread.currentThread() + " RedSquareES2.dispose FIN");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        if (key == 'w')
            move[0] = true;
        if (key == 's')
            move[1] = true;
        if (key == 'a')
            move[2] = true;
        if (key == 'd')
            move[3] = true;
        if (key == 'r')
            move[4] = true;
        if (key == 'v')
            move[5] = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        if (key == 'w')
            forward = true;
        if (key == 's')
            backward = true;
        if (key == 'a')
            left = true;
        if (key == 'd')
            right = true;
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
        if(key == 'x') {
            doRotate = !doRotate;
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        float lastX = e.getX();
        float lastY = e.getY();


        System.out.println("mouseDragged: " + lastX + " " + lastY);
        mouseX = lastX;
        mouseY = lastY;

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
