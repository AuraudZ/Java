import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

// This demos having a lister actually BE the component (JPanel that generates the events) itself
public class SmithPanelAlsoListener extends JPanel implements MouseListener {

	private String message;

	// Kind of awkward conceptually to have main here, but to keep everything in one file...
	public static void main(String[] args) {
		JFrame window = new JFrame("Test1");
		window.setVisible(true);
		window.setSize(400, 600);
		window.setLocation(300, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new SmithPanelAlsoListener());
	}


	public SmithPanelAlsoListener() {
		message = "default";
		this.addMouseListener(this);
	}

	// draws based on state set by member variables
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // otherwise, PCs have jumbled output
		g.drawString(message, 100, 100);

	}

	// note putting a System.out.println is also a good debugging strategy to make sure
	// the event-handling method is being called
	public void mouseClicked(MouseEvent e) {
		message = "mouse clicked"; // mouse was pressed, then released in same location
		repaint();
	}

	// event-handlers should change the state. Remember NO DRAWING here.
	public void mousePressed(MouseEvent e) {
		message = "mouse pressed";
		repaint();
	}

	public void mouseReleased(MouseEvent e) {
		message = "mouse released";
		repaint();
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

}
