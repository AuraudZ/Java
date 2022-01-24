package SimplePaintRefactor;

import javax.swing.*;
import java.awt.*;

public class SimplePaintMain {
    public static void main(String[] args) {
        JFrame window = new JFrame("Simple Paint");
        SimplePaintPanel content = new SimplePaintPanel();
        ColorPanel colorPanel = new ColorPanel(content);
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());

        container.add(content, BorderLayout.CENTER);
        container.add(colorPanel, BorderLayout.EAST);
        SimplePaintListener listener = new SimplePaintListener(content, colorPanel);
        JMenuBar menuBar = new JMenuBar();
        JMenu simplePaintMenu = new JMenu("Simple Paint");
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem about = new JMenuItem("About");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem clear = new JMenuItem("Clear");
        JMenuItem undo = new JMenuItem("Undo");
        optionsMenu.add(clear);
        optionsMenu.add(undo);
        exit.addActionListener(listener);
        clear.addActionListener(listener);
        undo.addActionListener(listener);
        about.addActionListener(listener);

        simplePaintMenu.add(about);
        simplePaintMenu.addSeparator();
        simplePaintMenu.add(exit);
        menuBar.add(simplePaintMenu);
        menuBar.add(optionsMenu);
        window.setJMenuBar(menuBar);
        window.setContentPane(container);
        window.setSize(700, 380);
        window.setLocation(100, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }


}
