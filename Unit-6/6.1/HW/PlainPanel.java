import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;

class PlainPanel extends JPanel {

    public PlainPanel() {
        JPanel buttonPanel = new JPanel();
        JPanel upperButtonPanel = new JPanel();
        JPanel lowerButtonPanel = new JPanel();
        JButton previousButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        JButton submitButton = new JButton("Submit");

        setBackground(java.awt.Color.white);
        setLayout(new BorderLayout());
        buttonPanel.setLayout(new GridLayout(2, 1));
        upperButtonPanel.setLayout(new FlowLayout());
        lowerButtonPanel.setLayout(new FlowLayout());
        upperButtonPanel.add(previousButton);
        upperButtonPanel.add(nextButton);
        lowerButtonPanel.add(submitButton);
        buttonPanel.add(upperButtonPanel);
        buttonPanel.add(lowerButtonPanel);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
