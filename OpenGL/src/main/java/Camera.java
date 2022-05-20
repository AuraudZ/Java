import com.jogamp.opengl.GL4bc;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;

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
    this.position = new Vector3f(0, 0, -10);
    this.front = new Vector3f(0, 0, -1);
    this.up = new Vector3f(0, 1, 0);
    this.right = new Vector3f(0, 0, 0);
    this.positionPlusFront = new Vector3f(position);
    this.yaw = -90;
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
    float velocity = speed;
    if (isPressed) {
      switch (direction) {
        case FORWARD:
          position.x += front.x * velocity;
          position.y += front.y * velocity;
          position.z += front.z * velocity;
          break;
        case BACKWARD:
            position.x -= front.x * velocity;
           position.y -= front.y * velocity;
           position.z -= front.z * velocity;
          break;
             case LEFT:
               right.x = front.y * up.z - front.z * up.y;
               right.y = front.z * up.x - front.x * up.z;
               right.z = front.x * up.y - front.y * up.x;
               position.x -= right.x * velocity;
               position.y -= right.y * velocity;
               position.z -= right.z * velocity;
               break;
             case RIGHT:
               right.x = front.y * up.z - front.z * up.y;
               right.y = front.z * up.x - front.x * up.z;
               right.z = front.x * up.y - front.y * up.x;
               position.x += right.x * velocity;
               position.y += right.y * velocity;
               position.z += right.z * velocity;
               break;
      }
    }
  }

  public Vector3f getFront() {
    return front;
  }

  private Matrix4f createLookAtMatrix(Vector3f position, Vector3f target, Vector3f up) {
    return new Matrix4f().lookAt(position, target, up);
  }

  public void setPosition(Vector3f position) {
    this.position = position;
  }

  public void setFront(Vector3f front) {
    this.front = front;
  }

  public void reset(Movement direction) {
    if (direction == Movement.RESET) {
      this.position = new Vector3f(0, 0, -5);
      this.front = new Vector3f(0, 0, -1);
      this.up = new Vector3f(0, 1, 0);
      this.positionPlusFront = positionPlusFront.add(front).add(position);
      this.yaw = -90;
      this.pitch = 0;
      this.updateCameraVectors();
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
    return view.lookAt(position, positionPlusFront, up);
  }

  public Vector3f getPosition() {
    return position;
  }

  public Vector3f getPositionPlusFront() {
    return positionPlusFront;
  }

  public Vector3f getUp() {
    return up;
  }

  private void updateCameraVectors() {
    Vector3f front = new Vector3f(0, 0, -90);
    front.x = (float) Math.cos(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));
    front.y = (float) Math.sin(Math.toRadians(pitch));
    front.z = (float) Math.sin(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));

    this.front = front;
    this.right = front.cross(this.up, right);
    this.right.normalize();
    this.up = this.right.cross(front, up);
    this.up.normalize();
  }

  public Vector3fc getLookAt() {
    return position.add(front);
  }

  public enum Movement {
    FORWARD,
    BACKWARD,
    LEFT,
    RIGHT,
    RESET
  }
}
