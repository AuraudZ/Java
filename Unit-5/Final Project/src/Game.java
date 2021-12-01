import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This file can be used to create very simple animations. Just fill in the definition of drawFrame
 * with the code to draw one frame of the animation, and possibly change a few of the values in the
 * rest of the program as noted below. Note that if you change the name of the class, you must also
 * change the name in the main() routine!
 */
public class Game extends JPanel implements ActionListener {

    /**
     * Draws one frame of an animation. This subroutine is called re second and is responsible for
     * redrawing the entire drawing area. The parameter g is used for drawing. The frameNumber
     * starts at zero and increases by 1 each time this subroutine is called. The parameters width
     * and height give the size of the drawing area, in pixels. The sizes and positions of the
     * rectangles that are drawn depend on the frame number, giving the illusion of motion.
     */
    public void drawFrame(Graphics g, int frameNumber, int width, int height) {
        g.drawString("Frame number " + frameNumber, 40, 50);
        // Game Loop
        // Draw the background
        g.setColor(Color.BLACK);
        // Draw Crosshair
        g.setColor(Color.RED);
        g.drawLine(width / 2, 0, width / 2, height);
        g.drawLine(0, height / 2, width, height / 2);
        // Draw red square
        g.fill3DRect(500, 300, 10, 20, true);
        // remove sqaures if clicked
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                // System.out.println("Mouse moved to " + x + " " + y);
                // If over red square
                try {
                    Color color = new Robot().getPixelColor(x, y);

                } catch (AWTException ex) {
                    ex.printStackTrace();
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println("Mouse clicked at " + x + " " + y);
                // If over red square
                try {
                    // The the pixel color at location x, y
                    Color color = new Robot().getPixelColor(x, y);
                    System.out.println("Color: " + color.toString());
                } catch (AWTException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }



    void spawnSqaures(int x, int y, int width, int height, Graphics g, int frameNumber,
            int amount) {
        // Draw red square
        for (int i = 0; i < amount; i++) {
            g.fillRect(x, y, width, height);
            x += width;
            y += height;
        }
    }
    // ------ Implementation details: DO NOT EXPECT TO UNDERSTAND THIS ------


    public static void main(String[] args) {

        /*
         * NOTE: The string in the following statement goes in the title bar of the window.
         */
        JFrame window = new JFrame("Aimbot");

        /*
         * NOTE: If you change the name of this class, you must change the name of the class in the
         * next line to match!
         */
        Game drawingArea = new Game();

        drawingArea.setBackground(Color.WHITE);
        window.setContentPane(drawingArea);

        /*
         * NOTE: In the next line, the numbers 600 and 450 give the initial width and height of the
         * drawing array. You can change these numbers to get a different size.
         */
        drawingArea.setPreferredSize(new Dimension(600, 450));

        window.pack();
        window.setLocation(100, 50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        /*
         * Note: In the following line, you can change true to false. This will prevent the user
         * from resizing the window, so you can be sure that the size of the drawing area will not
         * change. It can be easier to draw the frames if you know the size.
         */
        window.setResizable(true);

        /*
         * NOTE: In the next line, the number 20 gives the time between calls to drawFrame(). The
         * time is given in milliseconds, where one second equals 1000 milliseconds. You can
         * increase this number to get a slower animation. You can decrease it somewhat to get a
         * faster animation, but the speed is limited by the time it takes for the computer to draw
         * each frame.
         */
        Timer frameTimer = new Timer(10, drawingArea);

        window.setVisible(true);
        frameTimer.start();

    } // end main

    private int frameNum;

    public void actionPerformed(ActionEvent evt) {
        frameNum++;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFrame(g, frameNum, getWidth(), getHeight());
    }

}
