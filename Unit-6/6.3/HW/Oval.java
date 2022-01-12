import java.awt.*;

public class Oval implements Drawable {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    Oval(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }

    @Override
    public void clear(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(x, y, width, height);
    }
}
