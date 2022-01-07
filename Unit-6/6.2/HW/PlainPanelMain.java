import javax.swing.*;

public class PlainPanelMain {
    public static void main(String[] args) {
        JFrame window = new JFrame("Plain Panel");
        window.setContentPane(new PlainPanel());
        window.setSize(700, 500);
        window.setLocation(150, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

}
