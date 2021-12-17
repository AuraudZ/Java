import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

// Simple JPanel
public class Game extends JPanel implements ActionListener {

    private static int runCount = 0;

    private int timerAmount;
    private boolean playAgain = false;
    public Timer frameTimer = new Timer(10, this);

    // Constructor
    public Game() {
        frameTimer.start();
        button.addMouseListener(mouseListener);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the this bad game!");
        System.out.println("You have to click the button to start the game.");
        System.out.println("Click the sqaures to get points, earn the most before time runs out.");
        JFrame window = new JFrame("FPS Game");
        Game drawingArea = new Game();
        drawingArea.difficultyChoice();
        drawingArea.setBackground(Color.WHITE);
        window.setContentPane(drawingArea);
        drawingArea.setPreferredSize(new Dimension(600, 450));
        window.pack();
        window.setLocation(100, 50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setVisible(true);
        drawingArea.frameTimer.start();

    } // end main

    // Paint method
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFrame(g, frameNum, getWidth(), getHeight());
    }

    JButton button = new JButton("");
    JButton introButton = new JButton("Click to start!");

    private int score = 0;
    private boolean gameOver = false;

    public boolean getGameOver() {
        return gameOver;
    }

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

    public void printLeaderBoard() {
        try {
            String[] strings = leaderBoardStrings();
            File file = new File("leaderboard.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            // Read using leaderBoardString then pull out the runCount and add 1 to the runCount
            // Write to leaderBoardString
            for (String s : strings) {
                String[] split = s.split(" ");
                // Null check
                if (split[0] != null || split[1] != null || split[2] != null || split[3] != null
                        || split[4] != null || split[5] != null) {
                    // Append to end of file
                    if (Integer.parseInt(split[1]) == runCount) {
                        bw.append(split[0] + " " + (Integer.parseInt(split[1]) + 1) + " " + split[2]
                                + " " + split[3] + " " + split[4] + " " + split[5] + "\n");
                        bw.newLine();
                    } else {
                        bw.write("Run: " + runCount + " Score: " + score + " Difficulty: "
                                + difficulty);
                        bw.newLine();
                    }
                }
            }
            bw.append("Run: " + runCount + " Score: " + score + " Difficulty: " + difficulty);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public boolean getIntro() {
        return intro;
    }

    private int speed = 100;

    // Difficulty level
    public void getDifficulty(int difficulty) {
        switch (difficulty) {
            case 1:
                timerAmount = 60;
                speed = 100;
                break;
            case 2:
                timerAmount = 45;
                speed = 90;
                break;
            case 3:
                timerAmount = 30;
                speed = 80;
                break;
            case 4:
                timerAmount = 15;
                speed = 70;
                break;
            case 5:
                timerAmount = 15;
                speed = 60;
                break;
            default:
                timerAmount = 5;
                speed = 50;
                break;
        }
    }


    int difficulty = 0;

    public void difficultyChoice() {
        System.out.println("Please enter a difficulty level (1-5): ");
        int choice = TextIO.getlnInt();
        difficulty = choice;
        getDifficulty(choice);
    }

    // Create new Mouse listener
    MouseInputAdapter mouseListener = new MouseInputAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent arg0) {
            if (gameOver == false) {
                remove(button);
                score++;
            } else {
                gameOver = true;
                remove(button);
            }

        }
    };
    MouseInputAdapter introListener = new MouseInputAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent arg0) {
            if (intro) {
                remove(introButton);
                add(button);
                intro = false;
            }
        }
    };

    boolean intro = true;

    // Draws the frame
    public void drawFrame(Graphics g, int frameNumber, int width, int height) {
        if (intro) {
            frameNumber = 0;
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, width, height);
            introButton.setBackground(Color.RED);
            introButton.setText("Click to start!");
            // Center the button
            introButton.setBounds((width / 2) - (introButton.getWidth() / 2),
                    (height / 2) - (introButton.getHeight() / 2), introButton.getWidth(),
                    introButton.getHeight());
            add(introButton);
            introButton.addMouseListener(introListener);
        }
        if (frameNumber == timerAmount * 100) {
            gameOver = true;
        }
        if (!gameOver && !intro) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawString("Score: " + score, 10, 10);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int randX = (int) (Math.random() * width);
            int randY = (int) (Math.random() * height);
            Color randColor = new Color((int) (Math.random() * 256), (int) (Math.random() * 256),
                    (int) (Math.random() * 256));
            int randSize = (int) (Math.random() * width);
            if (randSize < 50) {
                randSize = 50;
            } else if (randSize > 150) {
                randSize = 150;
            }
            if (frameNumber < timerAmount * 100) {
                g.drawString("Time Left: " + (timerAmount * 100 - frameNumber) / 100, 40, 50);
            }
            if (frameNumber % speed == 0) {
                add(button);
                button.setBounds(randX, randY, randSize, randSize);
            }
            if (frameNumber % 75 == 0) {
                button.setBackground(randColor);
            }
            button.setText("");
            button.setBorderPainted(false);
            button.setOpaque(true);
        }

        while (gameOver) {
            // Wanted to Add a game over screen but I couldn't get it to work :(
            System.out.println("Game Over");
            System.out.println("Score: " + score);
            System.out.println("Would you like to play again? (y/n)");
            playAgain = TextIO.getlnBoolean();
            boolean changeDifficulty = false;
            if (playAgain) {
                // Clear the screen
                System.out.println("Would you like to change the difficulty? (y/n)");
                changeDifficulty = TextIO.getlnBoolean();
                gameOver = false;
                score = 0;
                frameNumber = 0;
                add(button);
                intro = false;
                frameTimer.restart();
            } else {
                System.out.println("Thanks for playing!");
                printLeaderBoard();
                System.exit(0);
            }
            if (changeDifficulty) {
                difficultyChoice();
                gameOver = false;
                frameTimer.restart();
                score = 0;
                frameNumber = 0;
                remove(introButton);
                add(button);
                intro = false;
            }
            break;
        }
    }

    private int frameNum;

    public void actionPerformed(ActionEvent evt) {
        frameNum++;
        repaint();
    }
}
