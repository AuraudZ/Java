package SubKillerRefactor;

import java.awt.*;


public class Bomb {
    int width, height; // Width and height of the bomb.
    Submarine sub; // The sub that is being bombed.
    Boat boat; // The boat that is droping bomb.
    int centerX, centerY; // Current position of the center of the bomb.
    boolean isFalling; // If true, the bomb is falling; if false, it
                       // is attached to the boat.

    Bomb(Boat boat, Submarine submarine) { // Constructor creates a bomb that is initially attached
        this.boat = boat;
        this.sub = submarine;
        isFalling = false;
    }

    void updateForNewFrame() { // If bomb is falling, take appropriate action.
        if (isFalling) {
            if (centerY > height) {
                // Bomb has missed the submarine. It is returned to its
                // initial state, with isFalling equal to false.
                isFalling = false;
            } else if (Math.abs(centerX - sub.centerX) <= 36
                    && Math.abs(centerY - sub.centerY) <= 21) {
                // Bomb has hit the submarine. The submarine
                // enters the "isExploding" state.
                sub.isExploding = true;
                sub.explosionFrameNumber = 1;
                isFalling = false; // Bomb reappears on the boat.
            } else {
                // If the bomb has not fallen off the panel or hit the
                // sub, then it is moved down 10 pixels.
                centerY += 10;
            }
        }
    }

    void draw(Graphics g) { // Draw the bomb.
        if (!isFalling) { // If not falling, set centerX and centerY
                          // to show the bomb on the bottom of the boat.
            centerX = boat.centerX;
            centerY = boat.centerY + 23;
        }
        g.setColor(Color.RED);
        g.fillOval(centerX - 8, centerY - 8, 16, 16);
    }
} // end class Bomb
