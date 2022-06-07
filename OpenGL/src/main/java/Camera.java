import com.jogamp.opengl.GL4bc;
import org.joml.Intersectionf;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.util.Vector;

public class Camera {

  // This class is a c++ port of this, I based it off this
  // https://learnopengl.com/Getting-started/Camera
  // I hope this is not an academic integrity violation

  private Vector3f position;
  private Vector3f cameraFront;
  private Vector3f up;

  private float[] positionPlusFront;
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
    this.position = new Vector3f(0, 0, 3);
    this.cameraFront = new Vector3f(0, 0, -1);
    this.up = new Vector3f(0, 1, 0);
    this.right = new Vector3f(0, 0, 0);
    this.positionPlusFront = new float[3];
    this.yaw = -90;
    this.pitch = 0;
    this.speed = 2.5f;
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

  public void setPitch(float pitch) {
    this.pitch = pitch;
  }

  public void setYaw(float yaw) {
    this.yaw = yaw;
  }

  public float getFov() {
    return fov;
  }

  public void processKeyboard(Movement direction, boolean isPressed) {
    Vector3f tmp = new Vector3f();
    if (direction == Movement.FORWARD) {
      position.add(cameraFront.mul(speed));
    }
    if (direction == Movement.BACKWARD) {
      position.sub(cameraFront.mul(speed));
    }
    if (direction == Movement.LEFT) {
      position.sub(cameraFront.cross(up, tmp).mul(speed).normalize());
    }
    if (direction == Movement.RIGHT) {
      position.add(cameraFront.cross(up, tmp).mul(speed).normalize());
    }
    if(direction == Movement.UP) {
      // the fov
      fov += 15;
    }
    if(direction == Movement.DOWN) {
      // the fov
      fov -= 15;
    }
    updateCameraVectors();
  }

  public Vector3f getCameraFront() {
    return cameraFront;
  }

  private Matrix4f createLookAtMatrix(Vector3f position, Vector3f target, Vector3f up) {
    return new Matrix4f().lookAt(position, target, up);
  }

  public void setPosition(Vector3f position) {
    this.position = position;
  }

  public void setCameraFront(Vector3f cameraFront) {
    this.cameraFront = cameraFront;
  }

  public void reset(Movement direction) {
    if (direction == Movement.RESET) {
      this.position = new Vector3f(0, 0, -5);
      this.cameraFront = new Vector3f(0, 0, -1);
      this.up = new Vector3f(0, 1, 0);

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

    updateCameraVectors();
  }

  public Matrix4f getViewMatrix() {
    view.identity();
    positionPlusFront[0] = position.x + cameraFront.x;
    positionPlusFront[1] = position.y + cameraFront.y;
    positionPlusFront[2] = position.z + cameraFront.z;
    view.lookAt(position, cameraFront, up);
    return view;
  }

  public Vector3f getPosition() {
    return position;
  }



  public Vector3f getUp() {
    return up;
  }

  private void updateCameraVectors() {
    Vector3f front = new Vector3f();

    front.x = (float) Math.cos(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));
    front.y = (float) Math.sin(Math.toRadians(pitch));
    front.z = (float) Math.sin(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));

    front.normalize(cameraFront);
    right = (front.cross(this.up, right)).normalize();
    up = (right.cross(front, up)).normalize();
  }

  public Vector3fc getLookAt() {
    return position.add(cameraFront);
  }

  public Matrix4f getProjection() {
    return projection;
  }

  public enum Movement {
    FORWARD,
    BACKWARD,
    LEFT,
    RIGHT,
    RESET,
    UP,
    DOWN
  }
}
