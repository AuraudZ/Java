package SimplePaintRefactor;

import javax.swing.*;
import java.awt.*;

public class ColorPanel extends JPanel {
    private int colorCode;
    SimplePaintPanel simplePaintPanel;

    public int getColorCode() {
        return this.colorCode;
    }

    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }

    public int colorToHex(Color color) {
        return color.getRGB() & 0xffffff;
    }

    public Color hexToColor(int colorCode) {
        return new Color(colorCode);
    }

    private JButton redButton;
    private JButton blueButton;
    private JButton greenButton;
    private JButton yellowButton;
    private JButton whiteButton;
    private JButton cyanButton;
    private JButton magentaButton;
    private JButton custom;
    private JButton clear;



    public ColorPanel(SimplePaintPanel simplePaintPanel) {
        this.setLayout(new GridLayout(9, 1));
        this.simplePaintPanel = simplePaintPanel;
        redButton = new JButton("Red");
        blueButton = new JButton("Blue");
        whiteButton = new JButton("White");
        greenButton = new JButton("Green");
        yellowButton = new JButton("Yellow");
        cyanButton = new JButton("Cyan");
        magentaButton = new JButton("Magenta");
        custom = new JButton("Custom");
        clear = new JButton("Clear");

        redButton.addActionListener(new ColorButtonListener(this, simplePaintPanel));
        blueButton.addActionListener(new ColorButtonListener(this, simplePaintPanel));
        greenButton.addActionListener(new ColorButtonListener(this, simplePaintPanel));
        yellowButton.addActionListener(new ColorButtonListener(this, simplePaintPanel));
        whiteButton.addActionListener(new ColorButtonListener(this, simplePaintPanel));
        cyanButton.addActionListener(new ColorButtonListener(this, simplePaintPanel));
        magentaButton.addActionListener(new ColorButtonListener(this, simplePaintPanel));
        custom.addActionListener(new ColorButtonListener(this, simplePaintPanel));
        clear.addActionListener(new ColorButtonListener(this, simplePaintPanel));

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

    public JButton getRedButton() {
        return this.redButton;
    }

    public void setRedButton(JButton redButton) {
        this.redButton = redButton;
    }

    public JButton getBlueButton() {
        return this.blueButton;
    }

    public void setBlueButton(JButton blueButton) {
        this.blueButton = blueButton;
    }

    public JButton getGreenButton() {
        return this.greenButton;
    }

    public void setGreenButton(JButton greenButton) {
        this.greenButton = greenButton;
    }

    public JButton getYellowButton() {
        return this.yellowButton;
    }

    public void setYellowButton(JButton yellowButton) {
        this.yellowButton = yellowButton;
    }

    public JButton getWhiteButton() {
        return this.whiteButton;
    }

    public void setWhiteButton(JButton whiteButton) {
        this.whiteButton = whiteButton;
    }

    public JButton getCyanButton() {
        return this.cyanButton;
    }

    public void setCyanButton(JButton cyanButton) {
        this.cyanButton = cyanButton;
    }

    public JButton getMagentaButton() {
        return this.magentaButton;
    }

    public void setMagentaButton(JButton magentaButton) {
        this.magentaButton = magentaButton;
    }

    public JButton getCustom() {
        return this.custom;
    }

    public void setCustom(JButton custom) {
        this.custom = custom;
    }

    public JButton getClear() {
        return this.clear;
    }

    public void setClear(JButton clear) {
        this.clear = clear;
    }

}
