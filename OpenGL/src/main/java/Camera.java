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
    this.position = new Vector3f(0, 0, 3);
    this.cameraFront = new Vector3f(0, 0, -1);
    this.up = new Vector3f(0, 1, 0);
    this.right = new Vector3f(0, 0, 0);
    this.positionPlusFront = new Vector3f(position);
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

  public void processKeyboard(Movement direction, boolean isPressed) {
    if (direction == Movement.FORWARD) {
      Vector3f tmp = new Vector3f();
      cameraFront.mul(speed, tmp);
      position = position.add(tmp);
    }
    if (direction == Movement.BACKWARD) {
      //        cameraPos -= glm::normalize(glm::cross(cameraFront, cameraUp)) * cameraSpeed;
      Vector3f tmp = new Vector3f(cameraFront);
      tmp.cross(up).mul(speed, tmp).normalize();
      position = position.add(tmp);
    }
    if (direction == Movement.LEFT) {
      Vector3f tmp = new Vector3f(cameraFront);
      tmp.cross(up).mul(speed, tmp).normalize();
      position = position.sub(tmp);
    }
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
      this.positionPlusFront = positionPlusFront.add(cameraFront).add(position);
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
    Vector3f tmp = new Vector3f(position);
    tmp.add(cameraFront);
    return view.lookAt(position, cameraFront, up);
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
    Vector3f front = new Vector3f();

    front.x = (float) Math.cos(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));
    front.y = (float) Math.sin(Math.toRadians(pitch));
    front.z = (float) Math.sin(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));

    front.normalize();
    //    lookAtVector = Normalize3dVector(viewDir * cos(angle) + UpVector * sin(angle));

    this.cameraFront = front;
    this.right = front.cross(this.up, right).normalize();
    up = right.cross(front, up);
    this.up.normalize();
    Vector3f tmp = new Vector3f();
    positionPlusFront.add(cameraFront);
    positionPlusFront.add(position);
  }

  public Vector3fc getLookAt() {
    return position.add(cameraFront);
  }

  public enum Movement {
    FORWARD,
    BACKWARD,
    LEFT,
    RIGHT,
    RESET
  }
}
