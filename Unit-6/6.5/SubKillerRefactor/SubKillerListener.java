package SubKillerRefactor;

import java.awt.event.*;
import javax.swing.Timer;

public class SubKillerListener
        implements KeyListener, FocusListener, MouseListener, ActionListener {

    SubKillerPanel gamePanel;
    Boat boat;
    Bomb bomb;
    Timer timer;
    Submarine sub;

    public SubKillerListener(SubKillerPanel gamePanel) {
        this.gamePanel = gamePanel;
        gamePanel.addMouseListener(this);
        gamePanel.addKeyListener(this);
        gamePanel.addFocusListener(this);
        timer = new Timer(30, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        gamePanel.requestFocusInWindow();
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

    @Override
    public void focusGained(FocusEvent e) {
        timer.start();
        gamePanel.repaint();
    }

    @Override
    public void focusLost(FocusEvent e) {
        timer.stop();
        gamePanel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent evt) {
        int code = evt.getKeyCode(); // Which key was pressed?
        if (code == KeyEvent.VK_LEFT) {
            // Move the boat left. (If this moves the boat out of the frame, its
            // position will be adjusted in the boat.updateForNewFrame() method.)
            boat.centerX -= 15;
        } else if (code == KeyEvent.VK_RIGHT) {
            // Move the boat right. (If this moves boat out of the frame, its
            // position will be adjusted in the boat.updateForNewFrame() method.)
            boat.centerX += 15;
        } else if (code == KeyEvent.VK_DOWN) {
            // Start the bomb falling, if it is not already falling.
            if (bomb.isFalling == false)
                bomb.isFalling = true;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}
