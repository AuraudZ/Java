import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

public class Box implements Collidable {

    GL2 gl;

    @Override
    public void colide(Collidable other) {
        // TODO Auto-generated method stub

    }

    float x,y,z;
    float xSize = 1;
    float ySize = 1;
    float zSize = 1;
    public Box(float x, float y, float z,float xSize, float ySize, float zSize,GL2 gl) {
        this.gl = gl;
        this.x = x;
        this.y = y;
        this.z = z;
        this.xSize = xSize;
        this.ySize = ySize;
        this.zSize = zSize;
    }
    public Box(float x, float y, float z, GL2 gl) {
        this.gl = gl;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void move(float x, float y, float z) {
        gl.glPushMatrix();
        gl.glTranslatef(x, y, z);
        gl.glPopMatrix();
    }

    public void moveRot(float x, float y, float z, float angle) {
        gl.glPushMatrix();
        gl.glTranslatef(x, y, z);
        gl.glRotatef(angle, 0, 1, 0);
        gl.glPopMatrix();
    }




    public void draw(GL2 gl) {
        gl.glPushMatrix();
        gl.glScalef(xSize, ySize, zSize);
        gl.glBegin(GL2.GL_QUADS);

        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glColor3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glColor3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glEnd();
        gl.glFlush();
        gl.glPopMatrix();
    }
}
