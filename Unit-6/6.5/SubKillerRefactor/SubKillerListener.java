package SubKillerRefactor;

import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SubKillerListener
        implements KeyListener, FocusListener, MouseListener, ActionListener, ChangeListener {

    private SubKillerPanel gamePanel;

    private Timer timer;

    public SubKillerListener(SubKillerPanel gamePanel, ScorePanel scorePanel) {
        this.gamePanel = gamePanel;
        gamePanel.addMouseListener(this);
        gamePanel.addKeyListener(this);
        gamePanel.addFocusListener(this);
        scorePanel.getSlider().addChangeListener(this);
        timer = new Timer(30, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s == null) {
            if (gamePanel.getBoat() != null) {
                gamePanel.getBoat().updateForNewFrame();
                gamePanel.getBomb().updateForNewFrame();
                gamePanel.getSub().updateForNewFrame();
            }
            gamePanel.repaint();

        } else if (s.equals("About")) {
            JOptionPane.showMessageDialog(gamePanel, "This game is great");
        } else if (s.equals("Restart")) {
            gamePanel.restart();
        } else if (s.equals("Exit")) {
            System.exit(0);
        }
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
    public void keyReleased(KeyEvent e) {}

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            gamePanel.setSubSpeed(source.getValue());
            gamePanel.requestFocusInWindow();
        }
    }
}
