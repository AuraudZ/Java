import java.awt.Graphics;

public class RoundRectangle  {

	private int width;
	private int height;
	private int x;
	private int y;

	public RoundRectangle() {
		width = 	(int)(Math.random()*200);
		height =  (int)(Math.random()*200);
		x =  (int)(Math.random()*200);
		y = (int)(Math.random()*200); 

		
	}
	
	public void draw(Graphics g) {
		g.drawRoundRect(x, y, width, height, 20, 20);
	}
	
	
}