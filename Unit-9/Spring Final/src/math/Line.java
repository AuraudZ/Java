package math;

public class Line {
    public Vec3 pointA;
    public Vec3 pointB;

    public Line(Vec3 pointA, Vec3 pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }
    public Ray asRay() {
        return new Ray(pointA, pointB.subtract(pointA).normalize());
    }
}
