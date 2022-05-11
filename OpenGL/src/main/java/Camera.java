import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2ES2;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GL4bc;
import com.jogamp.opengl.math.FloatUtil;
import com.jogamp.opengl.math.VectorUtil;
import com.jogamp.opengl.util.PMVMatrix;

public class Camera {

  private final float YAW = -90.0f;
  private final float PITCH = 0.0f;
  private final float SPEED = 2.5f;
  private final float SENSITIVITY = 0.1f;
  private final float ZOOM = 45.0f;
  float[] position = new float[] {0.0f, 0.0f, 0.0f};
  float[] front = new float[] {0.0f, 0.0f, -1.0f};
  float[] up = new float[] {0.0f, 1.0f, 0.0f};
  float[] right = new float[] {1.0f, 0.0f, 0.0f};
  float[] worldUp = new float[] {0.0f, 1.0f, 0.0f};
  float[] posPlusFront = new float[3];
  float yaw;
  float pitch;
  float zoom;
  float movementSpeed;
  float mouseSensitivity;
  private GL4bc gl;
  Camera(GL4bc gl) {
    this.gl = gl;
    position = new float[] {0.0f, 0.0f, 0.0f};
    front = new float[] {0.0f, 0.0f, -1.0f};
    up = new float[] {0.0f, 1.0f, 0.0f};
    yaw = YAW;
    pitch = PITCH;
    zoom = ZOOM;
    movementSpeed = SPEED;
    mouseSensitivity = SENSITIVITY;
    updateCameraVectors();
  }
  Camera() {
    this.position = new float[] {0.0f, 0.0f, 0.0f};
    this.front = new float[] {0.0f, 0.0f, -1.0f};
    this.up = new float[] {0.0f, 1.0f, 0.0f};
    this.yaw = YAW;
    this.pitch = PITCH;
    this.zoom = ZOOM;
    this.movementSpeed = SPEED;
    this.mouseSensitivity = SENSITIVITY;
    updateCameraVectors();
  }

  Camera(
      float[] position,
      float[] front,
      float[] up,
      float yaw,
      float pitch,
      float zoom,
      float movementSpeed,
      float mouseSensitivity) {
    this.position = position;
    this.front = front;
    this.up = up;
    this.yaw = yaw;
    this.pitch = pitch;
    this.zoom = zoom;
    this.movementSpeed = movementSpeed;
    this.mouseSensitivity = mouseSensitivity;
    updateCameraVectors();
  }

  public float[] GetViewMatrix() {
    float[] matrix = new float[16];
    float[] tmp = new float[16];
    return FloatUtil.makeLookAt(matrix, 0, position, 0, front, 0, up, 0, tmp);
  }

  private void updateCameraVectors() {
    float[] front = new float[3];
    front[0] = (float) Math.cos(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));
    front[1] = (float) Math.sin(Math.toRadians(pitch));
    front[2] = (float) Math.sin(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));
    VectorUtil.normalizeVec3(front);
    VectorUtil.normalizeVec3(VectorUtil.crossVec3(right, front, worldUp));
    VectorUtil.normalizeVec3(VectorUtil.crossVec3(up, right, front));
    PMVMatrix tmpMatrix = new PMVMatrix();
    tmpMatrix.glLoadIdentity();
    tmpMatrix.glMatrixMode(GL4bc.GL_PROJECTION);
    tmpMatrix.gluPerspective(90, 16 / 9, 1, 100);
    tmpMatrix.glLoadIdentity();
    tmpMatrix.glMatrixMode(GL4bc.GL_MODELVIEW);
    VectorUtil.addVec3(posPlusFront,position,front);
    tmpMatrix.gluLookAt(position[0], position[1], position[2], posPlusFront[0], posPlusFront[1], posPlusFront[2], up[0], up[1], up[2]);
  }


  public float[] getPosition() {
    return position;
  }

  public float[] getFront() {
    return front;
  }

  public void processKeyboard(Movement direction, float deltaTime) {
    float velocity = movementSpeed * deltaTime;
    if (direction == Movement.FORWARD) {
      position[0] += front[0] * velocity;
      position[1] += front[1] * velocity;
      position[2] += front[2] * velocity;
    }
    if (direction == Movement.BACKWARD) {
      position[0] -= front[0] * velocity;
      position[1] -= front[1] * velocity;
      position[2] -= front[2] * velocity;
    }
    if (direction == Movement.LEFT) {
      position[0] -= right[0] * velocity;
      position[1] -= right[1] * velocity;
      position[2] -= right[2] * velocity;
    }
    if (direction == Movement.RIGHT) {
      position[0] += right[0] * velocity;
      position[1] += right[1] * velocity;
      position[2] += right[2] * velocity;
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
