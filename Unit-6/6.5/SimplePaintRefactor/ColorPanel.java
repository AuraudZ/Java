package SimplePaintRefactor;

import javax.swing.*;
import java.awt.*;

public class ColorPanel extends JPanel {
    private int colorCode;

    public int getColorCode() {
        return this.colorCode;
    }

    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }

    public ColorPanel() {
        this.setLayout(new GridLayout(9, 1));
        JButton redButton = new JButton("Red");
        JButton blueButton = new JButton("Blue");
        JButton greenButton = new JButton("Green");
        JButton yellowButton = new JButton("Yellow");
        JButton whiteButton = new JButton("White");
        JButton cyanButton = new JButton("Cyan");
        JButton magentaButton = new JButton("Magenta");
        JButton custom = new JButton("Custom");
        JButton clear = new JButton("Clear");
        add(whiteButton);
        add(redButton);
        add(greenButton);
        add(blueButton);
        add(cyanButton);
        add(magentaButton);
        add(yellowButton);
        add(custom);
        add(clear);
    }
}
