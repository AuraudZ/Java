import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Test extends JPanel implements MouseMotionListener {
    static int x,y;
    public static void main(String[] args) {
       JFrame frame = new JFrame("Test");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(400, 400);

       Test panel = new Test();
       panel.addMouseMotionListener(panel);
       frame.add(panel);
        frame.setVisible(true);

    }


    @Override
    public void mouseDragged(MouseEvent e) {
        y = e.getY();
        x = e.getX();
        System.out.println(x + " " + y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
