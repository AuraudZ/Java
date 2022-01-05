import javax.swing.*;

public class DrawingPanelMain {
    public static void main(String[] args) {
        JFrame window = new JFrame("Drawing Panel");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(600, 400);
        window.setLocation(500, 300);
        window.setVisible(true);
        window.setContentPane(new DrawingPanel());
    }
}
