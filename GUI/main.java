import javax.swing.*;
import java.awt.*;
import java.io.*;

class main {
    public static void main(String args[]) {
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        // Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        JMenuItem m33 = new JMenuItem("Exit");
        Desktop desktop = Desktop.getDesktop();

        m33.addActionListener(e -> System.exit(0));
        File f = new File("C:\\Users\\User\\Desktop\\test.txt");
        m11.addActionListener(e -> desktop.Open(f));

        m1.add(m11);
        m1.add(m22);
        m1.add(m33);
        JButton button = new JButton("Press");
        JTextArea textArea = new JTextArea();
        JCheckBox checkbox = new JCheckBox("Check");
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContextPane().add(BorderLayout.CENTER, textArea);
        frame.getContentPane().add(checkbox);

        frame.getContentPane().add(button); // Adds Button to content pane of frame
        frame.setVisible(true);
    }
}
