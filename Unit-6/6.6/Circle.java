// *** Your name:
import java.awt.Color;
import java.awt.Graphics;

public class Circle {

	private int x;
	private int y;
	private int radius;
	private Color color;

	public Circle(int x, int y, int radius, Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, 2 * radius, 2 * radius);
		g.setColor(Color.black);
		g.drawOval(x, y, 2 * radius, 2 * radius);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return 2 * radius;
	}

	public int getHeight() {
		return 2 * radius;
	}

	public Color getColor() {
		return color;
	}

	public int getRadius() {
		return radius;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public boolean containsPoint(int x, int y) {
		int circleX = getX();
		int circleY = getY();
		System.out.println("Cx: " + circleX + " Cy: " + circleY);
		System.out.println("x: " + x + " y: " + y);

		double dX = x - circleX;
		double dY = y - circleY;
		double distanceX = Math.pow(dX, 2);
		double distanceY = Math.pow(dY, 2);
		double distance = Math.sqrt(distanceX + distanceY);

		System.out.println("distance: " + distance);
		System.out.println();

		if (distance <= radius) {
			return true;
		}
		return false;
	}
}
