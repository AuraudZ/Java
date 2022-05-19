import com.jogamp.opengl.*;
import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.joml.Vector3f;

public class Camera {

    private Vector3f position;
    private Vector3f front;
    private Vector3f up;

    private Vector3f positionPlusFront;
    private Vector3f right;

    private float yaw;
    private float pitch;

    private float speed;
    private float sensitivity;
    private float fov;
    private float zNear;
    private float zFar;

    private Matrix4f projection;
    private Matrix4f view;

    public Camera(GL4bc gl) {
        this.position = new Vector3f(0, 0, 0);
        this.front = new Vector3f(0, 0, -1);
        this.up = new Vector3f(0, 1, 0);
        this.right = new Vector3f(0, 0, 0);
        this.yaw = 0;
        this.pitch = 0;
        this.speed = 0.1f;
        this.sensitivity = 0.1f;
        this.fov = 45;
        this.zNear = 0.1f;
        this.zFar = 1000;
        this.projection = new Matrix4f();
        this.view = new Matrix4f();
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void processKeyboard(Movement direction, boolean isPressed) {
        float velocity = speed * 0.1f;
        if (isPressed) {
            if (direction == Movement.FORWARD) {
                position.x += velocity * front.x;
                position.z += velocity * front.z;
            } else if (direction == Movement.BACKWARD) {
                position.x -= front.x * velocity;
                position.z -= front.z * velocity;
            } else if (direction == Movement.LEFT) {
                Vector3f tmp = new Vector3f(front);
                tmp.x = front.y * up.z - front.y * up.y;
                tmp.z = front.x * up.y - front.y * up.x;
                position.x -= tmp.x * velocity;
                position.z -= tmp.z * velocity;
            } else if (direction == Movement.RIGHT) {
                Vector3f tmp = new Vector3f(front);
                tmp.x = front.y * up.z - front.y * up.y;
                tmp.z = front.x * up.y - front.y * up.x;

                position.x += tmp.x * velocity;
                position.z += tmp.z * velocity;
            }
        } else {
            if (direction == Movement.FORWARD) {
                position.x -= front.x * velocity;
                position.y -= front.y * velocity;
                position.z -= front.z;
            } else if (direction == Movement.BACKWARD) {
                position.x += front.x * velocity;
                position.y += front.y * velocity;
                position.z += front.z * velocity;
            } else if (direction == Movement.LEFT) {
                position.x += right.x * velocity;
                position.y += right.y * velocity;
                position.z += right.z * velocity;
            } else if (direction == Movement.RIGHT) {
                position.x -= right.x * velocity;
                position.y -= right.y * velocity;
                position.z -= right.z * velocity;
            }
        }

    }

    public Vector3f getFront() {
        return front;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public void setFront(Vector3f front) {
        this.front = front;
    }

    public void reset(Movement direction) {
        if (direction == Movement.RESET) {
            this.position = new Vector3f(0, 0, 0);
            this.front = new Vector3f(0, 0, -1);
            this.up = new Vector3f(0, 1, 0);
            this.positionPlusFront = new Vector3f(0, 0, 0);
            this.yaw = 0;
            this.pitch = 0;
            this.updateCameraVectors();
            this.getViewMatrix().identity();
        }
    }

    public void setUp(Vector3f up) {
        this.up = up;
    }

    public void processMouseMovement(float xoffset, float yoffset) {
        yaw += xoffset * sensitivity;
        pitch += yoffset * sensitivity;

        if (pitch > 89) {
            pitch = 89;
        } else if (pitch < -89) {
            pitch = -89;
        }

        // Yaw = glm::mod( Yaw + xoffset, 360.0f );
        yaw = (yaw + xoffset) % 360;

        updateCameraVectors();
    }

    public Matrix4f getViewMatrix() {
        //   view.identity();
        Vector3f tmp = new Vector3f(front);

        tmp.x = front.y * up.z - front.y * up.y;
        tmp.z = front.x * up.y - front.y * up.x;

        return view.lookAt(position,tmp , up);
    }

    public Vector3f getPosition() {
        return position;
    }

    private void updateCameraVectors() {
        Vector3f front = new Vector3f(0, 0, -90);
        front.x = (float) Math.cos(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));
        front.y = (float) Math.sin(Math.toRadians(pitch));
        front.z = (float) Math.sin(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));
        this.front = front.normalize();
        this.right = this.front.cross(this.up,right).normalize();
        this.up = this.right.cross(this.front,up).normalize();



    }

    public enum Movement {
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT,
        RESET
    }
}
