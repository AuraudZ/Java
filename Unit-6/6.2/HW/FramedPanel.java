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
        int thickness = Math.min(getWidth() / 20, getHeight() / 20);
        g.fillRect(0, 0, getWidth() * thickness, getHeight() * thickness);
        g.setColor(Color.WHITE);
        g.fillRect(thickness, thickness, getWidth() - 2 * thickness, getHeight() - 2 * thickness);
        g.setColor(Color.BLACK);
        g.drawRect(thickness, thickness, getWidth() - 2 * thickness, getHeight() - 2 * thickness);
    }
}
