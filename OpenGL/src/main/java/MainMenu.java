import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JPanel {
      private final JButton startButton;
      private final JButton exitButton;
      GLProfile glprofile = GLProfile.getDefault();
      GLCapabilities glcapabilities = new GLCapabilities(glprofile);
      GLJPanel gljpanel = new GLJPanel(glcapabilities);


      public MainMenu() {
            gljpanel.addGLEventListener(new Cube());
            startButton = new JButton("Start");
            exitButton = new JButton("Exit");
            setLayout(new GridLayout(2,1));
            add(startButton);
            add(exitButton);

            startButton.addActionListener(e -> {
                  JFrame frame = (JFrame) SwingUtilities.getRoot(startButton);
                  frame.getContentPane().removeAll();
                  frame.setSize(800, 600);
                  frame.getContentPane().add(gljpanel);
                  gljpanel.requestFocusInWindow();
                  FPSAnimator animator = new FPSAnimator(gljpanel, 60);
                  animator.start();
                  frame.pack();
            });

            exitButton.addActionListener(e -> {
                  JFrame frame = (JFrame) SwingUtilities.getRoot(exitButton);
                  frame.dispose();
            });
      }



      public JButton getStartButton() {
            return startButton;
      }

      public JButton getExitButton() {
            return exitButton;
      }

}
