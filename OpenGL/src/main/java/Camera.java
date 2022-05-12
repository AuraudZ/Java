import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.nio.FloatBuffer;

public class Camera {

    private final float YAW = -90.0f;
    private final float PITCH = 0.0f;
    private final float SPEED = 2.5f;
    private final float SENSITIVITY = 0.1f;
    private final float ZOOM = 90.0f;
    private GLU glu = new GLU();
    Vector3f position;
    Vector3f front;
    Vector3f up;
    Vector3f right = new Vector3f();
    Vector3f worldUp = new Vector3f(0.0f, 1.0f, 0.0f);
    float yaw;
    float pitch;
    float zoom;
    float movementSpeed;
    Matrix4f view = new Matrix4f();
    Matrix4f projection = new Matrix4f();
    float mouseSensitivity;
    private GL4bc gl;
    public Matrix4f getProjectionMatrix() {
        projection.setPerspective(zoom, 1.0f, 0.01f, 100.0f);
        return projection;
    }

    public Matrix4f getViewMatrix() {
        view.lookAt(position, position.add(front), up);
        return view;
    }

    public Matrix4f getProjectPlusViewMatrix() {
        Matrix4f projection = getProjectionMatrix();
        Matrix4f view = getViewMatrix();
        Matrix4f projectionView = new Matrix4f();
        projectionView.mul(projection, view);
        return projectionView;
    }



    public void cleanUp(GL4bc gl) {
        gl.glPopMatrix();
    }

    Camera(GL4bc gl) {
        this.gl = gl;
        position = new Vector3f(0.0f, 0.0f, 10.0f);
        front = new Vector3f(0.0f, 0.0f, -1.0f);
        up = new Vector3f(0.0f, 1.0f, 0.0f);
        yaw = YAW;
        pitch = PITCH;
        zoom = ZOOM;
        movementSpeed = SPEED;
        mouseSensitivity = SENSITIVITY;
        updateCameraVectors();
    }



    private void updateCameraVectors() {
        Vector3f front = new Vector3f();
        front.x = (float) Math.cos(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));
        front.y = (float) Math.sin(Math.toRadians(pitch));
        front.z = (float) Math.sin(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));
        front.normalize();
        right = front.cross(worldUp).normalize();
        up = right.cross(front).normalize();
        view.setPerspective(zoom, 1.0f, 0.01f, 100.0f);
        view.lookAt(position, front, up);
    }

    public float getZoom() {
        return zoom;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getFront() {
        return front;
    }

    public void processKeyboard(Movement direction) {
        float velocity = movementSpeed * 0.01f;
        processKeyboard(direction, velocity);
    }

    public void processKeyboard(Movement direction, float deltaTime) {
        float velocity = movementSpeed * deltaTime;
        if (direction == Movement.FORWARD) {
          position.add(front.mul(velocity));
        }
        if (direction == Movement.BACKWARD) {
          position.sub(front.mul(velocity));
        }
        if (direction == Movement.LEFT) {
          position.sub(right.mul(velocity));
        }
        if (direction == Movement.RIGHT) {
          position.add(right.mul(velocity));
        }
    }

    public void processMouseMovement(float xoffset, float yoffset, boolean constrainPitch) {
        xoffset *= mouseSensitivity;
        yoffset *= mouseSensitivity;
        yaw += xoffset;
        pitch += yoffset;
        if (constrainPitch) {
            if (pitch > 89.0f) {
                pitch = 89.0f;
            }
            if (pitch < -89.0f) {
                pitch = -89.0f;
            }
        }
        updateCameraVectors();
    }

    public enum Movement {
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT,
    }
}
