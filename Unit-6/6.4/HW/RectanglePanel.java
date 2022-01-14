
import java.awt.Graphics;
import javax.swing.JPanel;

public class RectanglePanel extends JPanel {

	private int numRectangles = 0;
	private Rectangle[] rectangles = new Rectangle[200];

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("Press x to remove a rectangle", 10, 10);
		for (int i = 0; i < numRectangles; i++) {
			rectangles[i].draw(g);
		}
	}

	public void addRectangle(Rectangle rectangle) {
		if (numRectangles == rectangles.length || rectangle == null)
			return;
		rectangles[numRectangles] = rectangle;
		numRectangles++;
	}


	public Rectangle containsPoint(int x, int y) {
		for (int i = numRectangles - 1; i >= 0; i--) {
			if (rectangles[i].containsPoint(x, y))
				return rectangles[i];
		}
		return null;
	}

	public void shiftAllLeft() {

		for (Rectangle rectangle : rectangles) {
			if (rectangle != null)
				rectangle.setX(rectangle.getX() - 10);
		}
	}

	public void shiftAllRight() {
		for (Rectangle rectangle : rectangles) {
			if (rectangle != null)
				rectangle.setX(rectangle.getX() + 10);
		}
	}

	public void deleteAll() {
		rectangles = new Rectangle[200];
		numRectangles = 0;

	}
}
