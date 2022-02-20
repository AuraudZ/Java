package math;

import objects.Solid;

public class RayHit {
    private Ray ray;
    private Solid hitSolid;
    private Vec3 hitPos;
    private Vec3 normal;

    public RayHit(Ray ray, Solid hitSolid, Vec3 hitPos) {
        this.ray = ray;
        this.hitSolid = hitSolid;
        this.hitPos = hitPos;
        this.normal = hitSolid.getNormalAt(hitPos);
    }

    public Ray getRay() {
        return ray;
    }

    public Solid getSolid() {
        return hitSolid;
    }

    public Vec3 getPosition() {
        return hitPos;
    }

    public Vec3 getNormal() {
        return normal;
    }
}
