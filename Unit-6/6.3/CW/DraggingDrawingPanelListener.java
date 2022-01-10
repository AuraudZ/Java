import java.awt.*;
import java.awt.event.*;

public class DraggingDrawingPanelListener implements MouseMotionListener {
    private DrawingPanel panel;

    public DraggingDrawingPanelListener(DrawingPanel panel) {
        this.panel = panel;
        panel.addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
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
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
