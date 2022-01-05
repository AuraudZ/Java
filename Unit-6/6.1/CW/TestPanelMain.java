import javax.swing.JFrame;

public class TestPanelMain {

	public static void main(String[] args) {
		JFrame window = new JFrame("Test Panel");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(600, 400);
		window.setLocation(100, 100);
		window.setVisible(true);
		window.setContentPane(new TestPanel());

	}

}
