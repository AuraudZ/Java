import java.awt.*;

public class OlympicCircle {
    // Olymic circles is a colection of 5 circles
    private Circle[] circles;

    // Constructor
    public OlympicCircle(int x, int y, int r) {
        // Initialize the 5 circles
        circles = new Circle[5];
        circles[0] = new Circle(x, y, r, Color.BLUE);
        circles[1] = new Circle(x + 2 * r, y, r, Color.BLACK);
        circles[2] = new Circle(x + 4 * r, y, r, Color.RED);
        circles[3] = new Circle(x + r, y + r, r, Color.YELLOW);
        circles[4] = new Circle(x + 3 * r, y + r, r, Color.GREEN);

    }

    public void draw(Graphics g) {
        // Draw all the circles
        for (int i = 0; i < circles.length; i++) {
            circles[i].draw(g);
        }
    }
}
