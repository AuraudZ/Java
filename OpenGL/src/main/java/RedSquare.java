import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.GLArrayDataClient;
import com.jogamp.opengl.util.PMVMatrix;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;
import com.jogamp.opengl.util.glsl.ShaderState;
import com.jogamp.opengl.util.glsl.ShaderUtil;

import javax.swing.*;
import java.nio.FloatBuffer;

public class RedSquare implements GLEventListener {

  ShaderState st;
  PMVMatrix pmvMatrix;

  private void initShader(GL2ES2 gl) {
    // Create & Compile the shader objects
    ShaderCode rsVp =
        ShaderCode.create(
            gl, GL2ES2.GL_VERTEX_SHADER, RedSquare.class, "shader", "shader/bin", "shader", false);
    ShaderCode rsFp =
        ShaderCode.create(
            gl,
            GL2ES2.GL_FRAGMENT_SHADER,
            RedSquare.class,
            "shader",
            "shader/bin",
            "shader",
            false);

    // Create & Link the shader program
    ShaderProgram sp = new ShaderProgram();
    sp.add(rsVp);
    sp.add(rsFp);
    if (!sp.link(gl, System.err)) {
      throw new GLException("Couldn't link program: " + sp);
    }

    // Let's manage all our states using ShaderState.
    st = new ShaderState();
    st.attachShaderProgram(gl, sp, false);
  }

  public void init(GLAutoDrawable drawable) {
    GL2ES2 gl = drawable.getGL().getGL2ES2();

    System.err.println(Thread.currentThread() + " Entering initialization");
    System.err.println(Thread.currentThread() + " GL Profile: " + gl.getGLProfile());
    System.err.println(Thread.currentThread() + " GL:" + gl);
    System.err.println(Thread.currentThread() + " GL_VERSION=" + gl.glGetString(gl.GL_VERSION));
    System.err.println(Thread.currentThread() + " GL_EXTENSIONS:");
    System.err.println(Thread.currentThread() + "   " + gl.glGetString(gl.GL_EXTENSIONS));
    System.err.println(
        Thread.currentThread()
            + " isShaderCompilerAvailable: "
            + ShaderUtil.isShaderCompilerAvailable(gl));

    pmvMatrix = new PMVMatrix();

    initShader(gl);

    // Push the 1st uniform down the path
    st.useProgram(gl, true);

    pmvMatrix.glMatrixMode(pmvMatrix.GL_PROJECTION);
    pmvMatrix.glLoadIdentity();
    pmvMatrix.glMatrixMode(pmvMatrix.GL_MODELVIEW);
    pmvMatrix.glLoadIdentity();

    if (!st.uniform(gl, new GLUniformData("mgl_PMVMatrix", 4, 4, pmvMatrix.glGetPMvMatrixf()))) {
      throw new GLException("Error setting PMVMatrix in shader: " + st);
    }
    // Allocate vertex arrays
    GLArrayDataClient vertices =
        GLArrayDataClient.createGLSL("mgl_Vertex", 3, gl.GL_FLOAT, false, 4);
    {
      // Fill them up
      FloatBuffer verticeb = (FloatBuffer) vertices.getBuffer();
      verticeb.put(-2);
      verticeb.put(2);
      verticeb.put(0);
      verticeb.put(2);
      verticeb.put(2);
      verticeb.put(0);
      verticeb.put(-2);
      verticeb.put(-2);
      verticeb.put(0);
      verticeb.put(2);
      verticeb.put(-2);
      verticeb.put(0);
    }
    vertices.seal(gl, true);

    GLArrayDataClient colors = GLArrayDataClient.createGLSL("mgl_Color", 4, gl.GL_FLOAT, false, 4);
    {
      // Fill them up
      FloatBuffer colorb = (FloatBuffer) colors.getBuffer();
      colorb.put(1);
      colorb.put(0);
      colorb.put(0);
      colorb.put(1);
      colorb.put(0);
      colorb.put(0);
      colorb.put(1);
      colorb.put(1);
      colorb.put(1);
      colorb.put(0);
      colorb.put(0);
      colorb.put(1);
      colorb.put(1);
      colorb.put(0);
      colorb.put(0);
      colorb.put(1);
    }
    colors.seal(gl, true);

    // OpenGL Render Settings
    gl.glClearColor(0, 0, 0, 1);
    gl.glEnable(GL2ES2.GL_DEPTH_TEST);

    st.useProgram(gl, false);

    // Let's show the completed shader state ..
    System.out.println(Thread.currentThread() + " " + st);
  }

  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    if (null == st) return;

    GL2ES2 gl = drawable.getGL().getGL2ES2();

    st.useProgram(gl, true);

    // Set location in front of camera
    pmvMatrix.glMatrixMode(pmvMatrix.GL_PROJECTION);
    pmvMatrix.glLoadIdentity();
    pmvMatrix.gluPerspective(45.0f, (float) width / (float) height, 1.0f, 100.0f);
    // pmvMatrix.glOrthof(-4.0f, 4.0f, -4.0f, 4.0f, 1.0f, 100.0f);

    GLUniformData ud = st.getUniform("mgl_PMVMatrix");
    if (null != ud) {
      // same data object
      st.uniform(gl, ud);
    }

    st.useProgram(gl, false);
  }

  public void dispose(GLAutoDrawable drawable) {
    if (null == st) return;

    GL2ES2 gl = drawable.getGL().getGL2ES2();
    System.out.println(Thread.currentThread() + " RedSquare.dispose: " + gl.getContext());

    st.destroy(gl);
    st = null;
    pmvMatrix = null;
    System.out.println(Thread.currentThread() + " RedSquare.dispose: FIN");
  }

  public void display(GLAutoDrawable drawable) {
    if (null == st) return;

    GL2ES2 gl = drawable.getGL().getGL2ES2();

    st.useProgram(gl, true);

    gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);

    // One rotation every four seconds
    pmvMatrix.glMatrixMode(pmvMatrix.GL_MODELVIEW);
    pmvMatrix.glLoadIdentity();
    pmvMatrix.glTranslatef(0, 0, -10);
    float ang = (float) System.currentTimeMillis() / 4000.0f;
    pmvMatrix.glRotatef(ang, 0, 0, 1);
    pmvMatrix.glRotatef(ang, 0, 1, 0);

    GLUniformData ud = st.getUniform("mgl_PMVMatrix");
    if (null != ud) {
      // same data object
      st.uniform(gl, ud);
    }

    // Draw a square
    gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 0, 4);

    st.useProgram(gl, false);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("RedSquare");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(640, 480);
    frame.setLocation(100, 100);
    GLProfile glprofile = GLProfile.getDefault();
    GLCapabilities glcapabilities = new GLCapabilities(glprofile);
    GLJPanel canvas = new GLJPanel(glcapabilities);
    canvas.addGLEventListener(new RedSquare());
    frame.add(canvas);
    frame.setVisible(true);

  }
}
