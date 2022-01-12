import java.awt.*;

public class Rectangle implements Drawable {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    Rectangle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    @Override
    public void clear(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(x, y, width, height);
    }

}
