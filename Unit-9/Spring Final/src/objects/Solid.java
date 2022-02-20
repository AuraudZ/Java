package objects;

import fx.Color;
import math.Ray;
import math.Vec3;

public abstract class Solid  {
    protected Vec3 position;
   protected Color color;
    protected float reflectivity;
    protected float emission;

    public Solid(Vec3 position, Color color) {
        this.position = position;
        this.color = color;

    }

    public abstract Vec3 calculateIntersection(Ray ray);
    public abstract Vec3 getNormalAt(Vec3 point);

    public Vec3 getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public Color getTextureColor(Vec3 point) {
        return getColor();
    }

    public float getReflectivity() {
        return reflectivity;
    }

    public float getEmission() {
        return emission;
    }
}