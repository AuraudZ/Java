import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class SimplePaint extends JPanel implements MouseListener, MouseMotionListener {

    /**
     * This main routine allows this class to be run as a program.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Simple Paint");
        SimplePaint content = new SimplePaint();
        window.setContentPane(content);
        window.setSize(700, 380);
        window.setLocation(100, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

    }

    /**
     * Some constants to represent the color selected by the user.
     */
    private final static int BLACK = 0, RED = 1, GREEN = 2, BLUE = 3, CYAN = 4, MAGENTA = 5,
            YELLOW = 6;

    private int currentColor = BLACK; // The currently selected drawing color,
                                      // coded as one of the above constants.

    /*
     * The following variables are used when the user is sketching a curve while dragging a mouse.
     */

    private int prevX, prevY; // The previous location of the mouse.
    private boolean dragging; // This is set to true while the user is drawing.

    // *** Let's make a nested class to define a new data type that will be stored in a data
    // structure
    // e.g. (not a Rectangle[] rectangles)
    private class Line {
        private int x1, y1, x2, y2;
        private int colorCode;

        public Line(int x1, int y1, int x2, int y2, int colorCode) {
            super();
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.colorCode = colorCode;
        }
    }

    private ArrayList<Line> lines = new ArrayList<Line>();

    /**
     * Constructor for SimplePaintPanel class sets the background color to be white and sets it to
     * listen for mouse events on itself.
     */
    SimplePaint() {
        this.setBackground(Color.WHITE);
        // *** Since the SimplePaint JPanel is also a Listener, register appropriately
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Fill with background color (white).

        int width = getWidth(); // Width of the panel.
        int height = getHeight(); // Height of the panel.

        int colorSpacing = (height - 56) / 7;

        g.setColor(Color.GRAY);
        g.drawRect(0, 0, width - 1, height - 1); // one rectangle for each pixel
        g.drawRect(1, 1, width - 3, height - 3);
        g.drawRect(2, 2, width - 5, height - 5);

        /*
         * Draw a 56-pixel wide gray rectangle along the right edge of the panel. The color palette
         * and Clear button will be drawn on top of this. (This covers some of the same area as the
         * border I just drew.
         */

        g.fillRect(width - 56, 0, 56, height);

        /*
         * Draw the "Clear button" as a 50-by-50 white rectangle in the lower right corner of the
         * panel, allowing for a 3-pixel border.
         */

        g.setColor(Color.WHITE);
        g.fillRect(width - 53, height - 53, 50, 50);
        g.setColor(Color.BLACK);
        g.drawRect(width - 53, height - 53, 49, 49);
        g.drawString("CLEAR", width - 48, height - 23);

        /* Draw the seven color rectangles. */

        g.setColor(Color.BLACK);
        g.fillRect(width - 53, 3 + 0 * colorSpacing, 50, colorSpacing - 3);
        g.setColor(Color.RED);
        g.fillRect(width - 53, 3 + 1 * colorSpacing, 50, colorSpacing - 3);
        g.setColor(Color.GREEN);
        g.fillRect(width - 53, 3 + 2 * colorSpacing, 50, colorSpacing - 3);
        g.setColor(Color.BLUE);
        g.fillRect(width - 53, 3 + 3 * colorSpacing, 50, colorSpacing - 3);
        g.setColor(Color.CYAN);
        g.fillRect(width - 53, 3 + 4 * colorSpacing, 50, colorSpacing - 3);
        g.setColor(Color.MAGENTA);
        g.fillRect(width - 53, 3 + 5 * colorSpacing, 50, colorSpacing - 3);
        g.setColor(Color.YELLOW);
        g.fillRect(width - 53, 3 + 6 * colorSpacing, 50, colorSpacing - 3);

        /*
         * Draw a 2-pixel white border around the color rectangle of the current drawing color.
         */

        g.setColor(Color.WHITE);
        g.drawRect(width - 55, 1 + currentColor * colorSpacing, 53, colorSpacing);
        g.drawRect(width - 54, 2 + currentColor * colorSpacing, 51, colorSpacing - 2);


        // *** Re-draw all of the information, based on the state of our data structure
        for (int i = 0; i < lines.size(); i++) {
            switch (lines.get(i).colorCode) {
                case BLACK:
                    g.setColor(Color.BLACK);
                    break;
                case RED:
                    g.setColor(Color.RED);
                    break;
                case GREEN:
                    g.setColor(Color.GREEN);
                    break;
                case BLUE:
                    g.setColor(Color.BLUE);
                    break;
                case CYAN:
                    g.setColor(Color.CYAN);
                    break;
                case MAGENTA:
                    g.setColor(Color.MAGENTA);
                    break;
                case YELLOW:
                    g.setColor(Color.YELLOW);
                    break;
            }
            g.drawLine(lines.get(i).x1, lines.get(i).y1, lines.get(i).x2, lines.get(i).y2);
        }
    }

    // end paintComponent()


    private void changeColor(int y) {
        int width = getWidth(); // Width of panel.
        int height = getHeight(); // Height of panel.
        int colorSpacing = (height - 56) / 7; // Space for one color rectangle.
        int newColor = y / colorSpacing; // Which color number was clicked?
        System.out.println("y: " + y + " colorSpacing: " + colorSpacing + " newColor: " + newColor);

        if (newColor < 0 || newColor > 6) // Make sure the color number is valid.
            return;

        currentColor = newColor;
    }


    /**
     * This is called when the user presses the mouse anywhere in the panel. There are three
     * possible responses, depending on where the user clicked: Change the current color, clear the
     * drawing, or start drawing a curve. (Or do nothing if user clicks on the border.)
     */
    public void mousePressed(MouseEvent evt) {
        int x = evt.getX(); // x-coordinate where the user clicked.
        int y = evt.getY(); // y-coordinate where the user clicked.

        int width = getWidth(); // Width of the panel.
        int height = getHeight(); // Height of the panel.

        if (dragging == true) // Ignore mouse presses that occur
            return; // when user is already drawing a curve.
                    // (This can happen if the user presses
                    // two mouse buttons at the same time.)
                    // ***like left button is down+dragging but you click the right button
        if (x > width - 53) {
            if (y > height - 53) {
                // ***Clicked on "CLEAR button".
                lines.clear(); // Clear the list of lines.

            } else {
                changeColor(y); // Clicked on the color palette.
                                // ***update the highlighted square of color
            }
        } else if (x > 3 && x < width - 56 && y > 3 && y < height - 3) {
            // The user has clicked on the white drawing area.
            // Start drawing a curve from the point (x,y).
            prevX = x;
            prevY = y;
            dragging = true;

        }
        repaint();
    } // end mousePressed()


    /**
     * Called whenever the user releases the mouse button. If the user was drawing a curve, the
     * curve is done, so we should set dragging to false
     */
    public void mouseReleased(MouseEvent evt) {
        if (dragging == false)
            return; // Nothing to do because the user isn't drawing.
        dragging = false;
    }


    /**
     * Called whenever the user moves the mouse while a mouse button is held down. If the user is
     * drawing, draw a line segment from the previous mouse location to the current mouse location,
     * and set up prevX and prevY for the next call. Note that in case the user drags outside of the
     * drawing area, the values of x and y are "clamped" to lie within this area. This avoids
     * drawing on the color palette or clear button.
     */
    public void mouseDragged(MouseEvent evt) {
        if (dragging == false)
            return; // Nothing to do because the user isn't drawing.

        int x = evt.getX(); // x-coordinate of mouse.
        int y = evt.getY(); // y-coordinate of mouse.

        if (x < 3) // Adjust the value of x,
            x = 3; // to make sure it's in
        if (x > getWidth() - 57) // the drawing area.
            x = getWidth() - 57;

        if (y < 3) // Adjust the value of y,
            y = 3; // to make sure it's in
        if (y > getHeight() - 4) // the drawing area.
            y = getHeight() - 4;

        // *** update our data structure to reflect the new state as the user is dragging
        // Remember, NO DRAWING here!
        lines.add(new Line(prevX, prevY, x, y, currentColor));
        prevX = x;
        prevY = y;
        repaint();


    } // end mouseDragged()

    public void mouseEntered(MouseEvent evt) {} // Some empty routines.

    public void mouseExited(MouseEvent evt) {} // (Required by the MouseListener

    public void mouseClicked(MouseEvent evt) {} // and MouseMotionListener

    public void mouseMoved(MouseEvent evt) {} // interfaces).
} // end class SimplePaint
