import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.math.VectorUtil;
import com.jogamp.opengl.util.PMVMatrix;

public class Camera {
    private static final float TO_RADIANS = (float) (Math.PI / 180.0f);
    float[] position = {0.0f, 0.0f, 5.0f};
    float[] up = {0.0f, 1.0f, 0.0f};
    float[] front = {0.0f, 0.0f, -1.0f};
    float fov = 40.0f;
    float near = 0.1f;
    float far = 1000.0f;
    float aspect = 1.0f;
    GL2ES2 gl;
    PMVMatrix pmvMatrix;
    float lastX, lastY;
    float[] posPlusFront = new float[3];



    public Camera(GL2ES2 gl, PMVMatrix pmvMatrix) {
        this.gl = gl;
        this.pmvMatrix = pmvMatrix;
    }

    public void update() {
        pmvMatrix.glMatrixMode(GL2.GL_PROJECTION);
        pmvMatrix.glLoadIdentity();
        pmvMatrix.gluPerspective(fov, aspect, near, far);
        pmvMatrix.glMatrixMode(GL2.GL_MODELVIEW);
        pmvMatrix.glLoadIdentity();
        //pmvMatrix.gluLookAt(position[0], position[1], position[2], posPlusFront[0], posPlusFront[1],
        //        posPlusFront[2], up[0], up[1], up[2]);
    }


    boolean forward = false;
    boolean backward = false;
    boolean left = false;
    boolean right = false;
    boolean jump = false;
    boolean reset = false;

    /*
     * This is horrible.
     */
    public void setMove(boolean[] move) {
        forward = move[0];
        backward = move[1];
        left = move[2];
        right = move[3];
        reset = move[4];
        jump = move[5];
    }


    public void move(float speed, float screenX, float screenY, float screenWidth,
                     float screenHeight) {
        if (forward) {
            position[0] += speed * front[0];
            position[2] -= speed * front[2];
        }
        if (backward) {
            position[0] -= speed * front[0];
            position[2] += speed * front[2];
        }
        if (left) {
            float[] right = new float[3];
            right[0] = front[1] * up[2] - front[2] * up[1];
            right[1] = front[2] * up[0] - front[0] * up[2];
            right[2] = front[0] * up[1] - front[1] * up[0];

            position[0] -= speed * right[0];
            position[2] -= speed * right[2];
        }
        if (right) {
            float[] right = new float[3];
            right[0] = front[1] * up[2] - front[2] * up[1];
            right[1] = front[2] * up[0] - front[0] * up[2];
            right[2] = front[0] * up[1] - front[1] * up[0];

            position[0] += speed * right[0];
            position[2] += speed * right[2];
        }
        float gravity = -0.1f;
        if (jump) {
            position[1] += speed;

        }
        if (reset) {
            position[0] = 0.0f;
            position[1] = 0.0f;
            position[2] = 0.0f;
            front[0] = 0.0f;
            front[1] = 0.0f;
            front[2] = 0.0f;
        }


        float xoffset = screenX - lastX;
        float yoffset = lastY - screenY;

        lastX = screenWidth / 2;
        lastY = screenHeight / 2;

        float sensitivity = 0.05f;
        xoffset *= sensitivity;
        yoffset *= sensitivity;


        float yaw = 0, pitch = 0;
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
        front = dir;
        VectorUtil.addVec3(posPlusFront, front, position);

      //  System.out.println("posPlusFront: " + posPlusFront[0] + ", " + posPlusFront[1] + ", " + posPlusFront[2]);
        update();
        pmvMatrix.gluLookAt(position[0], position[1], position[2], posPlusFront[0], posPlusFront[1], posPlusFront[2], up[0], up[1], up[2]);
      //  System.out.println("Cam Matrix: "+pmvMatrix.toString());
    }

    public float[] getPosition() {
        return position;
    }

    public float[] getDirection() {
        return front;
    }

    public PMVMatrix getPMvMatrixf() {
        return pmvMatrix;
    }
}
