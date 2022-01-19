import java.awt.event.*;
import java.awt.*;

public class KeyboardAndFocusDemoListener implements KeyListener, FocusListener, MouseListener {
    KeyboardAndFocusDemo panel;

    public KeyboardAndFocusDemoListener(KeyboardAndFocusDemo panel) {
        this.panel = panel;
        panel.addMouseListener(this);
        panel.addKeyListener(this);
        panel.addFocusListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        panel.requestFocusInWindow();
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void focusGained(FocusEvent e) {}

    @Override
    public void focusLost(FocusEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {
        char keyPressed = e.getKeyChar();
        if (keyPressed == 'r' || keyPressed == 'R') {
            panel.setSquareColor(Color.RED);
        }
        if (keyPressed == 'g' || keyPressed == 'G') {
            panel.setSquareColor(Color.GREEN);
        }
        if (keyPressed == 'b' || keyPressed == 'B') {
            panel.setSquareColor(Color.BLUE);
        }
        if (keyPressed == 'k' || keyPressed == 'K') {
            panel.setSquareColor(Color.BLACK);
        }
        panel.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyPressed = e.getKeyCode();
        if (keyPressed == KeyEvent.VK_UP) {
            panel.setSquareTop(panel.getSquareTop() - 8);
        }
        if (keyPressed == KeyEvent.VK_DOWN) {
            panel.setSquareTop(panel.getSquareTop() + 8);
        }
        if (keyPressed == KeyEvent.VK_LEFT) {
            panel.setSquareLeft(panel.getSquareLeft() - 8);
        }
        if (keyPressed == KeyEvent.VK_RIGHT) {
            panel.setSquareLeft(panel.getSquareLeft() + 8);

        }
        panel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}


}
