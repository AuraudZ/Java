import java.awt.*;
import java.awt.event.*;

public class DraggingDrawingPanelListener implements MouseMotionListener, MouseListener {
    private DrawingPanel panel;
    int startX, startY;
    Color c;
    private Rectangle curRectangle;

    public DraggingDrawingPanelListener(DrawingPanel panel) {
        this.panel = panel;
        panel.addMouseMotionListener(this);
        panel.addMouseListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        curRectangle.setWidth(e.getX() - startX);
        curRectangle.setHeight(e.getY() - startY);
        panel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}



    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
        System.out.println("Mouse Pressed");
        curRectangle = new Rectangle(startX, startY, 0, 0, c);
        if (e.isMetaDown())
            c = Color.BLUE;
        else if (e.isShiftDown())
            c = Color.GREEN;
        else
            c = Color.RED;
        panel.addRectangle(curRectangle);
        panel.repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        curRectangle = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}


}
