package objects;

import fx.Color;
import math.Ray;
import math.Vec3;

public class Sphere extends Solid {
    private float radius;

    public Sphere(Vec3 position, float radius, Color color) {
        super(position, color);
        this.radius = radius;
    }

    @Override
    public Vec3 calculateIntersection(Ray ray) {
        float t = Vec3.dot(position.subtract(ray.getOrigin()), ray.getDirection());
        Vec3 p = ray.getOrigin().add(ray.getDirection().multiply(t));

        float y = position.subtract(p).length();
        if (y < radius) {
            float x = (float) Math.sqrt(radius * radius - y * y);
            float t1 = t - x;
            if (t1 > 0) return ray.getOrigin().add(ray.getDirection().multiply(t1));
            else return null;
        } else {
            return null;
        }
    }

    @Override
    public Vec3 getNormalAt(Vec3 point) {
        return point.subtract(position).normalize();
    }
}