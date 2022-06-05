import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    private JButton startButton;
    private JButton exitButton;
    private JLabel titleLabel;


    public MainMenu() {
    JPanel contentPane = new JPanel();
    contentPane.setLayout(null);
    contentPane.setBackground(new Color(13, 234, 42));
    contentPane.setBounds(0, 0, 800, 600);

    contentPane.add(titleLabel);
    contentPane.add(startButton);
    contentPane.add(exitButton);
    this.add(contentPane);

        startButton = new JButton("Start");
        startButton.setBounds(100, 100, 100, 50);



        exitButton = new JButton("Exit");
        exitButton.setBounds(100, 200, 100, 50);

        titleLabel = new JLabel("Main Menu");
        titleLabel.setBounds(100, 300, 100, 50);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

}
