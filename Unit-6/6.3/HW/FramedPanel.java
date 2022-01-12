import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class FramedPanel extends JPanel {
    private Color color;
    private ArrayList<Drawable> drawables;

    private int drawableCount;

    public FramedPanel(Color color) {
        this.color = color;
        drawables = new ArrayList<Drawable>();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        // Colored Border with thickness of 1/20 of the width or height.
        int thickness = Math.min(getWidth() / 20, getHeight() / 20);
        g.fillRect(0, 0, getWidth() * thickness, getHeight() * thickness);
        // White Center
        g.setColor(Color.WHITE);
        g.fillRect(thickness, thickness, getWidth() - 2 * thickness, getHeight() - 2 * thickness);
        // Line between white center and outer edge.
        g.setColor(Color.BLACK);
        g.drawRect(thickness, thickness, getWidth() - 2 * thickness, getHeight() - 2 * thickness);
    }

    public void addDrawable(Drawable d) {
        d.draw(getGraphics());
        drawables.add(d);
    }

    public void clear() {
        for (int i = 0; i < drawables.size(); i++) {
            drawables.remove(i);
            repaint();
        }
    }

    public void removeDrawable(Drawable d) {
        for (int i = 0; i < drawables.size(); i++) {
            if (drawables.get(i) == d) {
                drawables.remove(i);
            }
        }
    }

    public void addDrawable(int index, Drawable d) {
        d.draw(getGraphics());
        drawables.add(index, d);
    }

    public int getDrawableCount() {
        return drawableCount;
    }

    public void setDrawableCount(int drawableCount) {
        this.drawableCount = drawableCount;
    }
}
