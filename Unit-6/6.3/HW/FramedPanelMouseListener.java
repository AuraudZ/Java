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
            // new Oval(e.getX(), e.getY(), 30, 30, Color.RED
            panel.addDrawable(new Oval(e.getX(), e.getY(), 30, 30, Color.RED));
            panel.setDrawableCount(panel.getDrawableCount() + 1);
        } else if (e.isAltDown()) {
            panel.addDrawable(new Rectangle(e.getX(), e.getY(), 30, 30, Color.BLUE));
            panel.setDrawableCount(panel.getDrawableCount() + 1);
        } else if (e.isControlDown()) {
            panel.clear();
        } else if (e.isShiftDown()) {
            panel.addDrawable(new Rectangle(e.getX(), e.getY(), 30, 30, Color.RED));
            panel.setDrawableCount(panel.getDrawableCount() + 1);

        } else {
            panel.addDrawable(new Rectangle(e.getX(), e.getY(), 30, 30, Color.RED));
            panel.setDrawableCount(panel.getDrawableCount() + 1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
