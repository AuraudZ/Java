import java.awt.*;
import javax.swing.*;

public class FramedPanel extends JPanel {
    private Color color;

    public FramedPanel(Color color) {
        this.color = color;
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
    }

    public void clear() {
        int thickness = Math.min(getWidth() / 20, getHeight() / 20);
        getGraphics().setColor(Color.WHITE);
        getGraphics().clearRect(thickness, thickness, getWidth() - 2 * thickness,
                getHeight() - 2 * thickness);
        this.repaint();

    }
}
