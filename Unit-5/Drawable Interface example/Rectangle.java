import java.awt.Graphics;

public class Rectangle  {

	private int width;
	private int height;
	private int x;
	private int y;

	public Rectangle() {
		width = 	(int)(Math.random()*200);
		height =  (int)(Math.random()*200);
		x =  (int)(Math.random()*20);
		y = (int)(Math.random()*200); 
		
	}
	
	public void draw(Graphics g) {
		g.drawRect(x, y, width, height);
	}
	
	
}
