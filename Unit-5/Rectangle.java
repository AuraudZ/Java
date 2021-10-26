import java.awt.Color;
import java.awt.Graphics;

public class Rectangle {
		// instance variables
		// use encapsulation (keep variables private)
		private int x, y, width, height;
		private Color color;
		
		// Constructors
		public Rectangle() {
			this.x = 0;
			this.y = 0;
			this.width = 50;
			this.height = 50;
			this.color = Color.BLACK;
		}
		
		public Rectangle(int width, int height) {
			this.width = width;
			this.height = height;
			this.color = Color.BLACK;
		}
		
		public Rectangle(int x, int y, int width, int height, Color color) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.color = color;
		}
		
		// instance methods
		public void draw(Graphics g) {
			g.setColor(color);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
		}
		
		// if present, toString() will be called any time you System.out
		// a Rectangle object.  Should output a nice version of the object's info
//		public String toString() {
//			return "Rectangle with x: " + x + ", y: " + y + ", width: " + 
//						width + ", height: " + height + ", color: " + color;
//		}

		// getters and setters
		public int getX() {
			return x;
		}
		
		// getters/setters allow you to control access to your data
		// more than if you left your variables private
		public void setX(int x) {
			if(x > 0) {
				this.x = x;
			}
		}
		
		public int getY() {
			return y;
		}
		
		public void setY(int y) {
			if(y > 0) {
				this.y = y;
			}
		}
		
		public int getWidth() {
			return width;
		}
		
		public void setWidth(int width) {
			this.width = width;
		}
		
		public int getHeight() {
			return height;
		}
		
		public void setHeight(int height) {
			this.height = height;
		}
		
		public Color getColor() {
			return color;
		}
		
		public void setColor(Color color) {
			this.color = color;
		}
	}

