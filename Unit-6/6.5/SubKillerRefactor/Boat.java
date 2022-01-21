package SubKillerRefactor;

import java.awt.*;

public class Boat {
    private int centerX, centerY; // Current position of the center of the boat.

    public int getCenterX() {
        return this.centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return this.centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public SubKillerPanel getPanel() {
        return this.panel;
    }

    public void setPanel(SubKillerPanel panel) {
        this.panel = panel;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    SubKillerPanel panel; // The panel that the boat is on.
    int width;
    int height;

    Boat(SubKillerPanel panel) { // Constructor centers the boat horizontally, 80 pixels from top.
        this.panel = panel;
        centerX = panel.getWidth() / 2;
        width = panel.getWidth();
        height = panel.getHeight();
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
