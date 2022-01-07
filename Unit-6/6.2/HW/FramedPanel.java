import javax.swing.JPanel;

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
        // g.fillRect(thickness, thickness, getWidth() - 2 * thickness, getHeight() - 2 *
        // thickness);
        g.drawRect(thickness, thickness, getWidth() - 2 * thickness, getHeight() - 2 * thickness);
    }
}
