import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleAnimationStarter extends JPanel implements ActionListener {
	
	public SimpleAnimationStarter() {

	}
	
    public void drawFrame(Graphics g, int frameNumber, int width, int height) {

	    switch(frameNumber % 3) {
		    case 0:

		    		break;
		    case 1:

	    			break;
		    default:
	
	    			break;
	    }
    	  
    }
    
    //------ Implementation details: DO NOT EXPECT TO UNDERSTAND THIS ------
    
    
    public static void main(String[] args) {
        
        JFrame window = new JFrame("Simple Animation");
        
        SimpleAnimationStarter drawingArea = new SimpleAnimationStarter();
        
        drawingArea.setBackground(Color.WHITE);
        window.setContentPane(drawingArea);

        drawingArea.setPreferredSize(new Dimension(600,450));

        window.pack();
        window.setLocation(100,50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        
        Timer frameTimer = new Timer(1000,drawingArea);

        window.setVisible(true);
        frameTimer.start();
       
    } // end main

    private int frameNum;
    
    public void actionPerformed(ActionEvent evt) {
        frameNum++;
        repaint();
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFrame(g, frameNum, getWidth(), getHeight());
    }
    
}



