import java.awt.*;
import javax.swing.*;

public class DrawingPanel extends JPanel {
	int numOfRects = 0;

	private class Rectangle {
		private int x;
		private int y;
		private int width;
		private int height;
		private Color color;

		public Rectangle(int x, int y, int width, int height, Color color) {
			this.x = x;
			this.y = y;
			this.color = color;
			this.width = width;
			this.height = height;

		}

		private void draw(Graphics g) {
			g.setColor(color);
			g.fillRect(x, y, width, height);
			g.setColor(Color.black);
			g.drawRect(x, y, width, height);
		}
	}

	Rectangle[] rectangles = new Rectangle[20];
	// instance variables represent state.
	int counter = 0;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		counter++;
		int x = (int) (Math.random() * getWidth());
		int y = (int) (Math.random() * getHeight());
		int width = (int) (Math.random() * 200) + 20;
		int height = (int) (Math.random() * 200) + 20;
		g.setColor(Color.PINK);
		g.drawRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.setFont(new Font("Serif", Font.BOLD, 14));
		g.drawString("System called paintComponent " + counter + " times", 100, 100);

		// Create a new point and add it to the array
		rectangles[numOfRects] = new Rectangle(x, y, width, height, getRandColor());
		numOfRects++;
		for (int i = 0; i < numOfRects; i++) {
			rectangles[i].draw(g);
		}
	}

	public static Color getRandColor() {
		int red = (int) (Math.random() * 256);
		int green = (int) (Math.random() * 256);
		int blue = (int) (Math.random() * 256);
		return new Color(red, green, blue);
	}

}
