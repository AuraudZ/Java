import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Test {

    public void drawString(String text, String artChar, Options options) {
        BufferedImage image = getImageIntegerMode(600, 800);
        Graphics2D graphics2d = image.getGraphics2D(image.getGraphics(), options);
        graphics2d.drawString(text, 6, ((int) (800 * 0.67)));
        for (int y = 0; y < 800; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < 600; x++) {
                sb.append(image.getRGB(x, y) == -16777216 ? " " : artChar);
            }
            if (sb.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(sb.toString());
        }
    }

    private BufferedImage getImageIntegerMode(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    private Graphics2D getGraphics2D(Graphics graphics, Options settings) {
        graphics.setFont(settings.font);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        return graphics2D;
    }

    public class Options {
        public Font font;
        public int width;
        public int height;

        public Options(Font font, int width, int height) {
            this.font = font;
            this.width = width;
            this.height = height;
        }
    }
}
