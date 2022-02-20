package math;

public class Vec3 {

    public float x;
    public float y;
    public float z;

    public Vec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }
    public Vec3 add(Vec3 vec) {
        return new Vec3(this.x + vec.x, this.y + vec.y, this.z + vec.z);
    }

    public Vec3 subtract(Vec3 vec) {
        return new Vec3(this.x - vec.x, this.y - vec.y, this.z - vec.z);
    }

    public Vec3 multiply(float scalar) {
        return new Vec3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public Vec3 multiply(Vec3 vec) {
        return new Vec3(this.x * vec.x, this.y * vec.y, this.z * vec.z);
    }

    public Vec3 divide(Vec3 vec) {
        return new Vec3(this.x / vec.x, this.y / vec.y, this.z / vec.z);
    }

    public float length() {
        return (float) Math.sqrt(x*x+y*y+z*z);
    }
    public Vec3 normalize() {
        float length = length();
        return new Vec3(this.x / length, this.y / length, this.z / length);
    }

    public static float distance(Vec3 a, Vec3 b) {
        return (float) Math.sqrt(Math.pow(a.x - b.x, 2)+Math.pow(a.y - b.y, 2)+Math.pow(a.z - b.z, 2));
    }

    public static float dot(Vec3 a, Vec3 b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    public static Vec3 lerp(Vec3 a, Vec3 b, float t) {
        return a.add(b.subtract(a).multiply(t));
    }

    @Override
    public Vec3 clone() {
        return new Vec3(x, y, z);
    }

}
