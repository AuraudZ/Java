import java.awt.*;
import java.io.*;
import javax.swing.*;

public class Leaderboard extends JPanel {
    // JFrame Leaderboard that reads in the leaderboard.txt file and displays it
    // in a JTextArea

    public Leaderboard() {
        // JFrame
        JFrame frame = new JFrame("Leaderboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setLocation(100, 50);
        frame.setResizable(true);
        frame.setVisible(true);

        // JTextArea
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Courier", Font.PLAIN, 12));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.WHITE);
        textArea.setForeground(Color.BLACK);
        textArea.setText("Leaderboard:\n");

        // Read in the leaderboard.txt file
        try {
            File file = new File("leaderboard.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                textArea.append(line + "\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add the textArea to the frame
        frame.add(textArea);
    }

    public static void main(String[] args) {
        new Leaderboard();

    }

}
