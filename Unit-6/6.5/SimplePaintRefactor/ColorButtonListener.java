package SimplePaintRefactor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JColorChooser;


public class ColorButtonListener implements ActionListener {

    private ColorPanel colorPanel;
    SimplePaintPanel simplePaintPanel;

    public ColorButtonListener(ColorPanel colorPanel, SimplePaintPanel simplePaintPanel) {
        this.colorPanel = colorPanel;
        this.simplePaintPanel = simplePaintPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (colorPanel.getRedButton() != null) {
            if (e.getSource() == colorPanel.getRedButton()) {
                colorPanel.setColorCode(0xFF0000);
            }
        }
        if (colorPanel.getBlueButton() != null) {
            if (e.getSource() == colorPanel.getBlueButton()) {
                colorPanel.setColorCode(0x0000FF);
            }
        }
        if (colorPanel.getGreenButton() != null) {
            if (e.getSource() == colorPanel.getGreenButton()) {
                colorPanel.setColorCode(0x00FF00);
            }
        }
        if (colorPanel.getYellowButton() != null) {
            if (e.getSource() == colorPanel.getYellowButton()) {
                colorPanel.setColorCode(0xFFFF00);
            }
        }
        if (colorPanel.getWhiteButton() != null) {
            if (e.getSource() == colorPanel.getWhiteButton()) {
                colorPanel.setColorCode(0xFFFFFF);
            }
        }
        if (colorPanel.getCyanButton() != null) {
            if (e.getSource() == colorPanel.getCyanButton()) {
                colorPanel.setColorCode(0x00FFFF);
            }
        }
        if (colorPanel.getMagentaButton() != null) {
            if (e.getSource() == colorPanel.getMagentaButton()) {
                colorPanel.setColorCode(0xFF00FF);
            }
        }
        if (colorPanel.getCustom() != null) {
            if (e.getSource() == colorPanel.getCustom()) {
                Color color = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
                colorPanel.setColorCode(colorPanel.colorToHex(color));
            }
        }
        if (colorPanel.getClear() != null) {
            if (e.getSource() == colorPanel.getClear()) {
                simplePaintPanel.lines.clear();
            }
        }

        simplePaintPanel.repaint();
    }



}
