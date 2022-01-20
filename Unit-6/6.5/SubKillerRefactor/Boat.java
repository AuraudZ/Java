package SubKillerRefactor;

import java.awt.*;

public class Boat {
    int centerX, centerY; // Current position of the center of the boat.
    SubKillerPanel panel; // The panel that the boat is on.
    int width = panel.getWidth();
    int height = panel.getHeight();

    Boat(SubKillerPanel panel) { // Constructor centers the boat horizontally, 80 pixels from top.
        centerX = panel.getWidth() / 2;
        centerY = 80;
    }

    void updateForNewFrame() { // Makes sure boat has not moved off screen.
        if (centerX < 0)
            centerX = 0;
        else if (centerX > width)
            centerX = width;
    }

    void draw(Graphics g) { // Draws the boat at its current location.
        g.setColor(Color.BLUE);
        g.fillRoundRect(centerX - 40, centerY - 20, 80, 40, 20, 20);
    }
} // end nested class Boat
