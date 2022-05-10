import com.jogamp.opengl.GL2ES2;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL4bc;
import com.jogamp.opengl.util.PMVMatrix;

public class Hud {
    GL4bc gl;
    Hud(GL4bc gl) {
        this.gl =  gl;
        gl.glPushAttrib(GL2.GL_ALL_ATTRIB_BITS);
        gl.glDisable(GL2.GL_DEPTH_TEST);
        gl.glPushMatrix();
        int[] viewport = new int[4];
        gl.glGetIntegerv(GL2.GL_VIEWPORT, viewport, 0);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, viewport[2], 0, viewport[3], -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glDisable(GL2.GL_DEPTH_TEST);
    }

    public void cleanUp() {
        // Clean up the HUD
        gl.glPopMatrix();
        gl.glPopAttrib();
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

    }


    public void drawBox(int x,int y,int width,int height,int r,int g,int b,int a) {
        gl.glColor4f(r/255f,g/255f,b/255f,a/255f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2i(x,y);
        gl.glVertex2i(x+width,y);
        gl.glVertex2i(x+width,y+height);
        gl.glVertex2i(x,y+height);
        gl.glEnd();
    }



}
