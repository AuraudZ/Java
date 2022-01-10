import java.awt.event.*;
import java.awt.*;

public class FramedPanelMouseListener implements MouseListener {
    private FramedPanel panel;

    FramedPanelMouseListener(FramedPanel panel) {
        this.panel = panel;
        panel.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isMetaDown()) {
            panel.addDrawable(new Oval(e.getX(), e.getY(), 30, 30, Color.RED));
        } else if (e.isAltDown()) {
            panel.addDrawable(new Rectangle(e.getX(), e.getY(), 30, 30, Color.BLUE));
        } else if (e.isControlDown()) {
            panel.clear();
        } else if (e.isShiftDown()) {
            panel.addDrawable(new Rectangle(e.getX(), e.getY(), 30, 30, Color.RED));
        } else {
            panel.addDrawable(new Rectangle(e.getX(), e.getY(), 30, 30, Color.RED));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
