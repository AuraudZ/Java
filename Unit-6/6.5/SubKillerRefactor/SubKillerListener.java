package SubKillerRefactor;

import java.awt.event.*;
import javax.swing.Timer;

public class SubKillerListener
        implements KeyListener, FocusListener, MouseListener, ActionListener {

    private SubKillerPanel gamePanel;

    private Timer timer;

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
        if (gamePanel.getBoat() != null) {
            gamePanel.getBoat().updateForNewFrame();
            gamePanel.getBomb().updateForNewFrame();
            gamePanel.getSub().updateForNewFrame();
        }
        gamePanel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        gamePanel.requestFocusInWindow();
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

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
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent evt) {
        int code = evt.getKeyCode(); // Which key was pressed?
        if (code == KeyEvent.VK_LEFT) {
            // Move the boat left. (If this moves the boat out of the frame, its
            // position will be adjusted in the boat.updateForNewFrame() method.)
            gamePanel.getBoat().setCenterX(gamePanel.getBoat().getCenterX() - 15);
        } else if (code == KeyEvent.VK_RIGHT) {
            // Move the boat right. (If this moves boat out of the frame, its
            // position will be adjusted in the boat.updateForNewFrame() method.)
            gamePanel.getBoat().setCenterX(gamePanel.getBoat().getCenterX() + 15);
        } else if (code == KeyEvent.VK_DOWN) {
            // Start the bomb falling, if it is not already falling.
            if (gamePanel.getBomb().getIsFalling() == false)
                gamePanel.getBomb().setIsFalling(true);
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
