import java.awt.Color;
import java.awt.event.*;

public class RectanglePanelListener implements MouseListener, MouseMotionListener {

	private RectanglePanel rectPanel;

	// ***Reference to the Rectangle we are dragging. This is like Method 2
	// that we did in class. Remember, if we save this reference, we can change
	// the state of the Rectangle being dragged, without having to delete and redraw
	// Rectangles constantly
	private Rectangle currentlyDraggingRectangle = null;
	private int offsetX, offsetY;
	private boolean dragging;

	public RectanglePanelListener(RectanglePanel panel) {
		rectPanel = panel; // save reference to the panel we are listening on
		rectPanel.addMouseListener(this); // register listener with panel
		rectPanel.addMouseMotionListener(this);
	}

	// mouseClicked (press/release in same spot) means I want to create a new rectangle
	// mousePressed (press and hold) means I'm going to possibly start dragging a rectangle
	@Override
	public void mouseClicked(MouseEvent ev) {
		int width = 30;
		int height = 20;
		Color color;
		if (ev.isMetaDown()) {
			color = Color.BLUE;
		} else {
			color = Color.RED;
		}
		// modify state to include a new rectangle
		rectPanel.addRectangle(new Rectangle(ev.getX(), ev.getY(), width, height, color));
		// repaint so user can see this new state
		rectPanel.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent ev) {}

	@Override
	public void mouseExited(MouseEvent ev) {
		currentlyDraggingRectangle = null;

	}

	@Override
	public void mousePressed(MouseEvent ev) {
		Rectangle rectangle = rectPanel.containsPoint(ev.getX(), ev.getY());
		if (rectangle != null) {
			currentlyDraggingRectangle = rectangle;
			offsetX = ev.getX() - rectangle.getX();
			offsetY = ev.getY() - rectangle.getY();
			dragging = true;
			rectPanel.repaint();
		}
	}

	public void mouseReleased(MouseEvent ev) {
		dragging = false;
		currentlyDraggingRectangle = null;
	}

	public void mouseDragged(MouseEvent ev) {
		if (currentlyDraggingRectangle != null) {
			currentlyDraggingRectangle.setX(ev.getX() - offsetX);
			currentlyDraggingRectangle.setY(ev.getY() - offsetY);
			rectPanel.repaint();
		}
		return;
	}

	@Override
	public void mouseMoved(MouseEvent e) {}
}
