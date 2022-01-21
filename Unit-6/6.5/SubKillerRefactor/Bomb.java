package SubKillerRefactor;

import java.awt.*;


public class Bomb {
    private int centerX, centerY; // Current position of the center of the bomb.
    private boolean isFalling; // If true, the bomb is falling; if false, it
    // is attached to the boat.
    private SubKillerPanel panel;
    private Boat boat;
    private Submarine sub; // The sub that is being bombed.

    Bomb(SubKillerPanel panel, Boat boat, Submarine sub) { // Constructor creates a bomb that is
        this.boat = boat;
        this.sub = sub;
        this.panel = panel;
        isFalling = false;
    }

    void updateForNewFrame() { // If bomb is falling, take appropriate action.
        if (isFalling) {
            if (centerY > panel.getHeight()) {
                // Bomb has missed the submarine. It is returned to its
                // initial state, with isFalling equal to false.
                isFalling = false;
            } else if (Math.abs(centerX - sub.getCenterX()) <= 36
                    && Math.abs(centerY - sub.getCenterY()) <= 21) {
                // Bomb has hit the submarine. The submarine
                // enters the "isExploding" state.
                sub.setIsExploding(true);
                sub.setExplosionFrameNumber(1);
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
            centerX = boat.getCenterX();
            centerY = boat.getCenterY() + 23;
        }
        g.setColor(Color.RED);
        g.fillOval(centerX - 8, centerY - 8, 16, 16);
    }


    public Submarine getSub() {
        return this.sub;
    }

    public void setSub(Submarine sub) {
        this.sub = sub;
    }

    public Boat getBoat() {
        return this.boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
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

    public boolean isIsFalling() {
        return this.isFalling;
    }

    public boolean getIsFalling() {
        return this.isFalling;
    }

    public void setIsFalling(boolean isFalling) {
        this.isFalling = isFalling;
    }

    public SubKillerPanel getPanel() {
        return this.panel;
    }

    public void setPanel(SubKillerPanel panel) {
        this.panel = panel;
    }

} // end class Bomb
