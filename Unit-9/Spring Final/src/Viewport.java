import fx.Color;
import math.Vec3;
import objects.Sphere;

import java.awt.*;
import javax.swing.*;


public class Viewport  extends JPanel {
    private Scene scene;
public Viewport() {
     scene = new Scene();
     scene.addSolid(new Sphere(new Vec3(0,0,1),0.4F, Color.BLUE));
}

@Override
   public void pain(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(0,0,getWidth(),getHeight());
    for (int x = 0; x < getWidth(); x++) {
        for (int y = 0; y < getHeight(); y++) {
            float u, v;
            u = (float) x / (float) getWidth();
            v = (float) y / (float) getHeight();

        }
    }
   }


}
