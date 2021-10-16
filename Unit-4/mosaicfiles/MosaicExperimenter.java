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
        System.out.println("What Colors");
        String colorString = TextIO.getlnString();
        colorString = colorString.toLowerCase();
        char[] colorArray = colorString.toCharArray();

        while (Mosaic.isOpen()) {
            Mosaic.delay(2);

            Mosaic.setColor(currentRow, currentCol, parseColorArray(colorArray));
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

    private static Color getColor(char input) {
        Color color = null;
        switch (input) {
            case 'r':
                color = Color.RED;
                break;
            case 'g':
                color = Color.GREEN;
                break;
            case 'b':
                color = Color.BLUE;
                break;
            case 'y':
                color = Color.YELLOW;
                break;
            case 'c':
                color = Color.CYAN;
                break;
            case 'm':
                color = Color.MAGENTA;
                break;
            case 'w':
                color = Color.WHITE;
                break;
            default:
                color = Color.BLACK;
                break;
        }
        return color;
    }

    private static Color parseColorArray(char[] colorArray) {
        Color color = null;
        for (int i = 0; i < colorArray.length; i++) {
            color = getColor(colorArray[i]);
            return color;
        }
        color = Color.BLACK;
        return color;
    }
}
