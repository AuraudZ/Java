import java.awt.*;

public class Circle {
    // Constructors
    public Circle() {
        this(0, 0, 1, Color.BLACK);
    }

    public Circle(int x, int y, int radius) {
        this(x, y, radius, Color.BLACK);
    }

    public Circle(int x, int y, double radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    // Instance variables
    private int x, y;
    private double radius;
    private Color color;

    // Instance methods
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, (int) (radius * 2), (int) (radius * 2));
    }

    // Getters and setters
    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
