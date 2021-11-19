import java.awt.*;

public class Circle {

	private int radius;
	private int x;
	private int y;
	
	public Circle() {
		// first
		radius = (int)(Math.random()*200);
		x =  (int)(Math.random()*200);
		y = (int)(Math.random()*200); 
	}
	
	public void draw(Graphics g) {
		g.drawOval(x, y, radius/2, radius/2);
	}
	
}
