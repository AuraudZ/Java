import java.awt.Color;
import java.awt.event.*;

public class DrawingPanelListener implements MouseListener {
    private DrawingPanel panel;

    public DrawingPanelListener(DrawingPanel panel) {
        this.panel = panel;
        panel.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Color c;
        System.out.println("Mouse Pressed");
        if (e.isMetaDown())
            c = Color.BLUE;
        else if (e.isShiftDown())
            c = Color.GREEN;
        else
            c = Color.RED;

        panel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
