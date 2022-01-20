package SubKillerRefactor;

import javax.swing.JFrame;

public class SubKillerMain {
    public static void main(String[] args) {
        JFrame window = new JFrame("Sub Killer Game");
        SubKillerPanel content = new SubKillerPanel();
        new SubKillerListener(content);
        window.setContentPane(content);
        window.setSize(600, 480);
        window.setLocation(100, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); // User can't change the window's size.
        window.setVisible(true);
    }
}
