public class Vertex3 {
    float x;
    float y;
    float z;

    public Vertex3() {
        this(0, 0, 0);
    }

    public Vertex3(double x, double y, double z) {
        this.x = (float) x;
        this.y = (float) y;
        this.z = (float) z;
    }

    public Vertex3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vertex3(double[] v) {
        this(v[0], v[1], v[2]);
    }

    public Vertex3(double v) {
        this(v, v, v);
    }


    @Override
    public String toString() {
        return "Vertex3{ " +
                "x = " + x +
                ", y = " + y +
                ", z = " + z +
                '}';
    }
}
