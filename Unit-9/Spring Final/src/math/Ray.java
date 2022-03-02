package math;

public class Ray {
    private Vec3 origin;
    private Vec3 direction;

    public  Ray(Vec3 origin, Vec3 direction) {
        this.origin = origin;

        if (direction.length() != 1) {
            direction = direction.normalize();
        }
        this.direction = direction;
    }

    public Line asLine(float length) {
        return new Line(origin, origin.add(direction.multiply(length)));
    }

    public Vec3 getOrigin() {
        return origin;
    }

    public Vec3 getDirection() {
        return direction;
    }
}
