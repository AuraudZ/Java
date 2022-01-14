
import javax.swing.JFrame;

public class RectanglePanelMain {

	public static void main(String[] args) {
		JFrame window = new JFrame("Rectangle Panel");
		window.setSize(600, 400);
		window.setLocation(100, 200);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RectanglePanel rectPanel = new RectanglePanel();
		new RectanglePanelListener(rectPanel);
		window.setContentPane(rectPanel);
		window.setVisible(true);
		rectPanel.requestFocusInWindow();
	}
}
