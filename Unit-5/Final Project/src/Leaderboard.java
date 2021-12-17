import java.awt.*;
import java.io.*;
import javax.swing.*;

public class Leaderboard extends JPanel {
    // JFrame Leaderboard that reads in the leaderboard.txt file and displays it
    // in a JTextArea
    public String[] leaderBoardStrings() {
        String[] leaderBoardString;
        try {
            File file = new File("leaderboard.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileReader fr = new FileReader(file.getAbsoluteFile());
            BufferedReader br = new BufferedReader(fr);
            leaderBoardString = br.lines().toArray(String[]::new);
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                leaderBoardString[i] = line;
                i++;
            }
            br.close();
            return leaderBoardString;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Paint the leaderboard
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        String[] strings = leaderBoardStrings();
        int i = 0;
        if (strings != null) {
            for (String s : strings) {
                String[] split = s.split(" ");
                // Null check
                if (split[0] != null || split[1] != null || split[2] != null || split[3] != null
                        || split[4] != null || split[5] != null) {
                    g.drawString(split[0] + " " + (Integer.parseInt(split[1])) + " " + split[2]
                            + " " + split[3] + " " + split[4] + " " + split[5], 10, 10 + 20 * i);
                    i++;
                }
            }
        }
    }

    public Leaderboard() {
        // JFrame
        JFrame frame = new JFrame("Leaderboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setLocation(100, 50);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new Leaderboard();

    }

}
