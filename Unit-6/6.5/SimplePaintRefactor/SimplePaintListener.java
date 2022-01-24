package SimplePaintRefactor;

import java.awt.event.*;

public class SimplePaintListener
        implements MouseListener, MouseMotionListener, ActionListener, KeyListener {

    private SimplePaintPanel paintPanel;
    private boolean dragging;
    private int prevX, prevY;
    private ColorPanel colorPanel;

    SimplePaintListener(SimplePaintPanel paintPanel, ColorPanel colorPanel) {
        this.paintPanel = paintPanel;
        this.colorPanel = colorPanel;
        paintPanel.addMouseListener(this);
        paintPanel.addMouseMotionListener(this);
        paintPanel.addKeyListener(this);
    }

    /**
     * This is called when the user presses the mouse anywhere in the panel. There are three
     * possible responses, depending on where the user clicked: Change the current color, clear the
     * drawing, or start drawing a curve. (Or do nothing if user clicks on the border.)
     */
    public void mousePressed(MouseEvent evt) {
        int x = evt.getX(); // x-coordinate where the user clicked.
        int y = evt.getY(); // y-coordinate where the user clicked.



        if (dragging == true) // Ignore mouse presses that occur
            return; // when user is already drawing a curve.
                    // (This can happen if the user presses
                    // two mouse buttons at the same time.)
                    // ***like left button is down+dragging but you click the right button

        System.out.println("mousePressed: " + x + ", " + y);
        paintPanel.repaint(); // Clicked on "CLEAR button".

        // changeColor(y); // Clicked on the color palette.
        paintPanel.repaint(); // ***added this to update the highlighted square of color


        // The user has clicked on the white drawing area.
        // Start drawing a curve from the point (x,y).
        prevX = x;
        prevY = y;
        dragging = true;


    } // end mousePressed()


    /**
     * Called whenever the user releases the mouse button. If the user was drawing a curve, the
     * curve is done, so we should set drawing to false and get rid of the graphics context that we
     * created to use during the drawing.
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
        // System.out.println("mouseDragged!");
        if (dragging == false)
            return; // Nothing to do because the user isn't drawing.

        int x = evt.getX(); // x-coordinate of mouse.
        int y = evt.getY(); // y-coordinate of mouse.


        paintPanel.lines.add(new Line(prevX, prevY, x, y, colorPanel.getColorCode()));
        // ArrayList, will be drawn later
        paintPanel.repaint(); // ***Have System call paintComponent(), otherwise lines won't show up
                              // until you
        // clicked the
        // the next color
        prevX = x; // Get ready for the next line segment in the curve.
        prevY = y;

    } // end mouseDragged()


    public void mouseEntered(MouseEvent evt) {} // Some empty routines.

    public void mouseExited(MouseEvent evt) {} // (Required by the MouseListener

    public void mouseClicked(MouseEvent evt) {} // and MouseMotionListener

    public void mouseMoved(MouseEvent evt) {} // interfaces).

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s == null) {
            return;
        } else if (s.equals("Clear")) {
            paintPanel.lines.clear();
        } else if (s.equals("Exit")) {
            System.exit(0);
        } else if (s.equals("Undo")) {
            if (paintPanel.lines.size() > 0) {
                paintPanel.removeLastLine();
                paintPanel.repaint();
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        int key = e.getKeyCode();
        if (e.isControlDown() && key == KeyEvent.VK_Z) {
            if (paintPanel.lines.size() > 0) {
                paintPanel.removeLastLine();
                paintPanel.repaint();
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}


}
