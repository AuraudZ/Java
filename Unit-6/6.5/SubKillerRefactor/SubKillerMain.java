package SubKillerRefactor;

import java.awt.*;
import javax.swing.*;

public class SubKillerMain {
    public static void main(String[] args) {
        JFrame window = new JFrame("Sub Killer Game");
        ScorePanel score = new ScorePanel();
        SubKillerPanel content = new SubKillerPanel(score);
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(content, BorderLayout.CENTER);
        container.add(score, BorderLayout.SOUTH);
        SubKillerListener listener = new SubKillerListener(content, score);
        JMenuBar menuBar = new JMenuBar();
        JMenu subKillerMenu = new JMenu("Sub Killer");
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem about = new JMenuItem("About");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem restart = new JMenuItem("Restart");

        about.addActionListener(listener);
        exit.addActionListener(listener);
        restart.addActionListener(listener);

        subKillerMenu.add(about);
        subKillerMenu.addSeparator();
        subKillerMenu.add(exit);
        optionsMenu.add(restart);
        menuBar.add(subKillerMenu);
        menuBar.add(optionsMenu);
        window.setJMenuBar(menuBar);
        window.setContentPane(container);
        window.setSize(600, 480);
        window.setLocation(100, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); // User can't change the window's size.
        window.setVisible(true);
    }
}
