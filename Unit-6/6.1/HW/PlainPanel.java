import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;

class PlainPanel extends JPanel {

    public PlainPanel() {
        JPanel southContainer = new JPanel();
        JPanel upperButtonPanel = new JPanel();
        JPanel lowerButtonPanel = new JPanel();
        JButton previousButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        JButton submitButton = new JButton("Submit");
        setBackground(java.awt.Color.white);
        setLayout(new BorderLayout());
        // add(new FramedPanel(Color.RED), BorderLayout.CENTER);
        upperButtonPanel.add(previousButton);
        upperButtonPanel.add(nextButton);
        upperButtonPanel.setLayout(new GridLayout(1, 1));
        lowerButtonPanel.setLayout(new GridLayout(1, 1));
        lowerButtonPanel.add(submitButton);
        southContainer.setLayout(new GridLayout(2, 3));
        southContainer.add(upperButtonPanel);
        southContainer.add(lowerButtonPanel);
        add(southContainer, BorderLayout.SOUTH);
    }
}
