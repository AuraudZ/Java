import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;

// Simple JPanel
public class Game extends JPanel implements MouseListener, ActionListener {

    // Constructor
    public Game() {
        // Add mouse listener
        addMouseListener(this);
    }

    public static void main(String[] args) {

        /*
         * NOTE: The string in the following statement goes in the title bar of the window.
         */
        JFrame window = new JFrame("Simple Animation");

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

    // Paint method
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFrame(g, frameNum, getWidth(), getHeight());
    }

    // Get Mouse Position using MouseListener

    public void drawFrame(Graphics g, int frameNumber, int width, int height) {
        g.drawString("Frame number " + frameNumber, 40, 50);
        Point mousePosition = getMousePosition();
        int mouseX = mousePosition.x;
        int mouseY = mousePosition.y;
        g.drawString("Mouse position: " + mouseX + ", " + mouseY, 40, 70);
        g.setColor(Color.BLACK);
        g.fillRect(mouseX, mouseY, 10, 10);
        g.setColor(Color.RED);
        g.fillRect(300, 500, 50, 60);
        new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the color of the pixel at the mouse position
                try {
                    Color pxColor = getPixelColor(e.getX(), e.getY());
                    if (pxColor.equals(Color.RED)) {
                        System.out.println("RED");
                    }
                } catch (Exception ex) {
                    System.out.println("Error: " + ex);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Mouse pressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("Mouse released");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Mouse entered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Mouse exited");
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println("Mouse dragged");
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println("Mouse moved");
            }
        };
    }

    public Color getPixelColor(int x, int y) throws AWTException {
        // Get the color of the pixel at the mouse position
        Color pixelColor = new Robot().getPixelColor(x, y);
        return pixelColor;
    }

    public Point getMousePosition() {
        return MouseInfo.getPointerInfo().getLocation();
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent evt) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    private int frameNum;

    public void actionPerformed(ActionEvent evt) {
        frameNum++;
        repaint();
    }

}
