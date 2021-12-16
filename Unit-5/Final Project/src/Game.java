import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;

// Simple JPanel
public class Game extends JPanel implements ActionListener {

    private int timerAmount;
    private boolean playAgain = false;

    private boolean getPlayAgain() {
        return playAgain;
    }

    public Timer frameTimer = new Timer(10, this);

    // Constructor
    public Game() {
        frameTimer.start();
        button.addMouseListener(mouseListener);
    }

    public static void main(String[] args) {
        // Print Welcome and instructions
        System.out.println("Welcome to the game!");
        System.out.println("You have to click the button to start the game.");

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

        if (drawingArea.getIntro() == false) {
            drawingArea.frameTimer.restart();
        }
        if (drawingArea.getPlayAgain() == true) {
            drawingArea.frameTimer.restart();
        }


        window.setVisible(true);

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

    public boolean getIntro() {
        return intro;
    }



    private int speed = 100;

    // Difficulty level
    public void getDifficulty(int difficulty) {
        // I know I can do a switch but I don't want to refactor
        if (difficulty == 1) {
            timerAmount = 60;
            speed = 100;
        } else if (difficulty == 2) {
            timerAmount = 45;
            speed = 90;
        } else if (difficulty == 3) {
            timerAmount = 30;
            speed = 80;
        } else if (difficulty == 4) {
            timerAmount = 15;
            speed = 70;
        } else if (difficulty == 5) {
            timerAmount = 15;
            speed = 60;
        } else {
            timerAmount = 5;
            speed = 50;
        }
    }

    public void difficultyChoice() {
        System.out.println("Please enter a difficulty level (1-5): ");
        int choice = TextIO.getlnInt();
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

                System.out.println("Game over!");
                System.out.println("Your score was: " + score);
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
            introButton.setBounds(width / 2 - 50, height / 2, 200, 100);
            add(introButton);
            introButton.addMouseListener(introListener);
        }
        if (frameNumber == timerAmount * 100) {
            gameOver = true;
        }

        if (!gameOver && intro == false) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawString("Score: " + score, 10, 10);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int randX = (int) (Math.random() * width);
            int randY = (int) (Math.random() * height);
            Color randColor = new Color((int) (Math.random() * 256), (int) (Math.random() * 256),
                    (int) (Math.random() * 256));
            int randSize = (int) (Math.random() * width);
            if (randSize < 10) {
                randSize = 10;
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
                remove(introButton);
                add(button);
                intro = false;
                frameTimer.restart();
            } else {
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
            // g.setColor(Color.BLACK);
            // g.fillRect(0, 0, width, height);
            // g.setColor(Color.WHITE);
            // g.drawString("Game Over!", width / 2 - 50, height / 2);
            // g.drawString("Your score was: " + score, width / 2 - 50, height / 2 + 50);
            // remove(button);
            break;
        }


    }

    private int frameNum;

    public void actionPerformed(ActionEvent evt) {
        frameNum++;
        repaint();
    }
}
