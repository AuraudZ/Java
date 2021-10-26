import javax.swing.*;
import java.awt.*;

public class RectangleCollectionDrawer extends JPanel {
	
	private Rectangle r1;
	private Rectangle r2;
	private Rectangle r3;
	private Rectangle[] rectangleCollection;
	
	public RectangleCollectionDrawer() {
		r1 = new Rectangle();
		r2 = new Rectangle(400, 70);
		r3 = new Rectangle(200, 400, 80, 80, Color.RED);
		rectangleCollection = new Rectangle[4];
//		rectangleCollection[0] = new Rectangle(200, 100, 80, 80, Color.MAGENTA);
//		rectangleCollection[1] = new Rectangle(300, 100, 90, 80, Color.GREEN);
//		rectangleCollection[2] = new Rectangle(50, 250, 80, 90, Color.PINK);
//		rectangleCollection[3] = new Rectangle(75, 0, 100, 80, Color.YELLOW);
	}

	public static void main(String[] args) {
		// toString()
		JFrame window = new JFrame("Simple Animation");
		RectangleCollectionDrawer drawingArea = new RectangleCollectionDrawer();
		drawingArea.setBackground(Color.WHITE);
		window.setContentPane(drawingArea);
		drawingArea.setPreferredSize(new Dimension(600,450));
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		Rectangle r4 = new Rectangle();
		Rectangle r5 = new Rectangle(100, 70);
		Rectangle r6 = new Rectangle(200, 100, 80, 80, Color.BLUE);
		// Print out Rectangle objects.  Normally, this will just print the pointer
		// which will look like a mess: Rectangle@4e9ba398, Rectangle@6d7b4f4c, Rectangle@527740a2
		// But, if Rectangle has defined a toString() method, it will use that instead and actually
		// print something hopefully useful and nice-looking
		System.out.println(r4);
		System.out.println(r5);
		System.out.println(r6);
		
	}
	
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        r1.draw(g);
        r2.draw(g);
        r3.draw(g);
        for(int i = 0; i < rectangleCollection.length; i++) {
        		rectangleCollection[i].draw(g);
        }
    }



}
