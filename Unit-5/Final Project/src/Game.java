import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;

// Simple JPanel
public class Game extends JPanel implements ActionListener {

    private static int timerAmount = 10;
    private boolean playAgain = false;

    private boolean getPlayAgain() {
        return playAgain;
    }

    // Constructor
    public Game() {
        // Add mouse listener
        add(button);
        button.addMouseListener(mouseListener);
    }

    public static void main(String[] args) {

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

        Timer frameTimer = new Timer(10, drawingArea);
        frameTimer.start();
        if (drawingArea.getIntro() == false || drawingArea.getPlayAgain() == true) {
            frameTimer.restart();
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

    public void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("Game Over!", getWidth() / 2, getHeight() / 2);
    }

    private int speed = 100;

    // Difficulty level
    public void getDifficulty(int difficulty) {
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
            timerAmount = 10;
            speed = 60;
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

    Image image = new ImageIcon("./icon.png").getImage();
    boolean intro = true;

    // Draws the frame
    public void drawFrame(Graphics g, int frameNumber, int width, int height) {
        if (intro) {
            frameNumber = 0;
            g.setColor(Color.RED);
            introButton.setBounds(width / 2 - 50, height / 2, 2001, 100);
            add(introButton);
            introButton.addMouseListener(introListener);
        }
        if (frameNumber == timerAmount * 100) {
            gameOver = true;
        }

        if (!gameOver && intro == false) {

            g.drawString("Frame number " + frameNumber, 40, 50);
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
                g.drawString("Time Left: " + (timerAmount * 100 - frameNumber) / 100, 40, 110);
            }

            if (frameNumber % speed == 0) {
                add(button);
                g.setColor(randColor);
                button.setBounds(randX, randY, randSize, randSize);

            }
            g.drawString("Score: " + score, width / 2, height / 2);
            button.setText("");
            button.setBorderPainted(false);
            button.setOpaque(true);
        }
        while (gameOver) {
            remove(button);
            add(introButton);
            System.out.println("Game Over");
            System.out.println("Score: " + score);
            System.out.println("Would you like to play again? (y/n)");
            playAgain = TextIO.getlnBoolean();
            boolean changeDifficulty = false;
            if (playAgain) {
                System.out.println("Would you like to change the difficulty? (y/n)");
                changeDifficulty = TextIO.getlnBoolean();
                gameOver = false;
                score = 0;
                frameNumber = 0;
                remove(introButton);
                add(button);
                intro = false;
            } else {
                System.exit(0);
            }
            if (changeDifficulty) {
                difficultyChoice();
                gameOver = false;
                score = 0;
                frameNumber = 0;
                remove(introButton);
                add(button);
                intro = false;
            }
        }
    }



    public Color getPixelColor(int x, int y) throws AWTException {
        // Get the color of the pixel at the mouse position
        Color pixelColor = new Robot().getPixelColor(x, y);
        return pixelColor;
    }

    public Point getMousePosition() {
        return MouseInfo.getPointerInfo().getLocation();
    }


    private int frameNum;

    public void actionPerformed(ActionEvent evt) {
        frameNum++;
        repaint();
    }

}
