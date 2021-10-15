import java.awt.Color;

public class MosaicExperimenter {
    public static void main(String[] args) {
        int rows = 4;
        int cols = 8;
        int width = 100;
        int height = 100;
        Mosaic.open(rows, cols, width, height);
        int currentRow = 0;
        int currentCol = 0;
        while (Mosaic.isOpen()) {
            Mosaic.delay(2);
            Mosaic.setColor(currentRow, currentCol, getRandomColor());
            currentCol++;
            if (currentCol == cols) {
                currentCol = 0;
                currentRow++;
            }
            if (currentRow == rows) {
                currentRow = 0;
            }
        }
    }

    private static Color getRandomColor() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        return new Color(red, green, blue);
    }
}
