import math.Ray;
import math.RayHit;
import math.Vec3;
import objects.Solid;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Scene {
    // List of Objects in the scene
    private CopyOnWriteArrayList<Solid> solids;

    public  Scene() {
        this.solids = new CopyOnWriteArrayList<Solid>();

    }

    public void addSolid(Solid solid) {
        this.solids.add(solid);
    }

    public void clearSolids() {
        solids.clear();
    }

    public RayHit raycast(Ray ray) {
        RayHit closestHit = null;
        for (Solid solid : solids) {
            if (solid == null)
                continue;

            Vec3 hitPos = solid.calculateIntersection(ray);
            if (hitPos != null && (closestHit == null || Vec3.distance(closestHit.getPosition(), ray.getOrigin()) > Vec3.distance(hitPos, ray.getOrigin()))) {
                closestHit = new RayHit(ray, solid, hitPos);
            }
        }
        return closestHit;
    }
}


