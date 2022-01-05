import java.awt.*;
import javax.swing.*;

public class TestPanel extends JPanel {

	// #1: see it works, even if blank.
	// But what do we want JPanel object to do?

	// 1 - Can draw on JPanel object (won't do today)
	// 2 - (Today:) JPanel object can be used as a CONTAINER for other JComponents (Buttons, etc)
	JButton open = new JButton("Open");
	JButton close = new JButton("Close");
	// As a container, TestPanel (which is-a JPanel object) can HAVE other JComponents


	public TestPanel() {


		// Nothing happened!
		// Because we need to add the JComponents to the container, our JPanel object
		// Syntax: container.add(component);

		this.add(open);
		this.add(close);

		// What if we want to fix the layout? Using a LayoutManager
		// means you don't worry about coordinates -- it resizes it for you!



		// the interesting thing is, we can add containers within containers
		// So what if I want to add 3 buttons in the "South" position?
		// I have to add a JPanel object (a container), and place the 3 objects
		// inside the container!
		// Otherwise, you could only put 1 thing in the South position.

		// default is FlowLayout
		// you can set the Layout style of this smaller container as well


		// Aha! haven't added optionPanel to the TestPanel object


	}

}
