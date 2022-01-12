import java.awt.Graphics;
import javax.swing.JPanel;

public class RectanglePanel extends JPanel {
	// instance variables: 
	private int numRectangles = 0;
	private Rectangle[] rectangles = new Rectangle[20000];
	
	// system calls every time you resize
	// system will call when you call repaint() 
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// redraw, from scratch, our current state of rectangles
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
	
	
	// *** (2) note there are 2 versions of containsPoint methods
	// what do you think containsPoint from a RectanglePanel's perspective is doing?
	public Rectangle containsPoint(int x, int y) {
		for (int i = numRectangles - 1; i >= 0; i--) {
		    if (rectangles[i].containsPoint(x, y))
		    		return rectangles[i];
		}
		return null;
	}

}
