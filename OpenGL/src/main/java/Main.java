import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities(glprofile);
        GLJPanel gljpanel = new GLJPanel(glcapabilities);

        gljpanel.addGLEventListener(new Line3D());

        final JFrame jframe = new JFrame("One Triangle Swing GLJPanel");
        jframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowevent) {
                jframe.dispose();
                System.exit(0);
            }
        });

        jframe.getContentPane().add(gljpanel, BorderLayout.CENTER);
        jframe.setSize(640, 480);
        jframe.setVisible(true);

        final FPSAnimator animator = new FPSAnimator(gljpanel, 60);
        animator.start();
    }
}
