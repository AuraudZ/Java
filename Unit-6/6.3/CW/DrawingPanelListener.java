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

        panel.addRectangle(e.getX(), e.getY(), 100, 50, c);
        panel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
