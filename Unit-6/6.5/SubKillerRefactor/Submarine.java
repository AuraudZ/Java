package SubKillerRefactor;

import java.awt.*;

public class Submarine {
    // Width and height of the submarine.
    private int centerX, centerY; // Current position of the center of the sub.
    private boolean isMovingLeft; // Tells whether the sub is moving left or right
    private boolean isExploding; // Set to true when the sub is hit by the bomb.
    private int explosionFrameNumber; // If the sub is exploding, this is the number
    // of frames since the explosion started.

    SubKillerPanel panel;

    int width;
    int height;

    Submarine(SubKillerPanel panel) { // Create the sub at a random location 40 pixels from bottom.
        this.panel = panel;
        width = panel.getWidth();
        height = panel.getHeight();
        centerX = (int) (width * Math.random());
        centerY = height - 40;

        isExploding = false;
        isMovingLeft = (Math.random() < 0.5);
    }

    void updateForNewFrame() { // Move sub or increase explosionFrameNumber.
        if (isExploding) {
            // If the sub is exploding, add 1 to explosionFrameNumber.
            // When the number reaches 15, the explosion ends and the
            // sub reappears in a random position.
            explosionFrameNumber++;
            if (explosionFrameNumber == 15) {
                centerX = (int) (width * Math.random());
                centerY = height - 40;
                isExploding = false;
                isMovingLeft = (Math.random() < 0.5);
            }
        } else { // Move the sub.
            if (Math.random() < 0.04) {
                // In one frame out of every 25, on average, the sub
                // reverses its direction of motion.
                isMovingLeft = !isMovingLeft;
            }
            if (isMovingLeft) {
                // Move the sub 5 pixels to the left. If it moves off
                // the left edge of the panel, move it back to the left
                // edge and start it moving to the right.
                centerX -= 5;
                if (centerX <= 0) {
                    centerX = 0;
                    isMovingLeft = false;
                }
            } else {
                // Move the sub 5 pixels to the right. If it moves off
                // the right edge of the panel, move it back to the right
                // edge and start it moving to the left.
                centerX += 5;
                if (centerX > width) {
                    centerX = width;
                    isMovingLeft = true;
                }
            }
        }
    }

    void draw(Graphics g) { // Draw sub and, if it is exploding, the explosion.
        g.setColor(Color.BLACK);
        System.out.println("Sub");
        g.fillOval(centerX - 30, centerY - 15, 60, 30);
        if (isExploding) {
            // Draw an "explosion" that grows in size as the number of
            // frames since the start of the explosion increases.
            g.setColor(Color.YELLOW);
            g.fillOval(centerX - 4 * explosionFrameNumber, centerY - 2 * explosionFrameNumber,
                    8 * explosionFrameNumber, 4 * explosionFrameNumber);
            g.setColor(Color.RED);
            g.fillOval(centerX - 2 * explosionFrameNumber, centerY - explosionFrameNumber / 2,
                    4 * explosionFrameNumber, explosionFrameNumber);
        }
    }


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

    public boolean isIsMovingLeft() {
        return this.isMovingLeft;
    }

    public boolean getIsMovingLeft() {
        return this.isMovingLeft;
    }

    public void setIsMovingLeft(boolean isMovingLeft) {
        this.isMovingLeft = isMovingLeft;
    }

    public boolean isIsExploding() {
        return this.isExploding;
    }

    public boolean getIsExploding() {
        return this.isExploding;
    }

    public void setIsExploding(boolean isExploding) {
        this.isExploding = isExploding;
    }

    public int getExplosionFrameNumber() {
        return this.explosionFrameNumber;
    }

    public void setExplosionFrameNumber(int explosionFrameNumber) {
        this.explosionFrameNumber = explosionFrameNumber;
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

}
