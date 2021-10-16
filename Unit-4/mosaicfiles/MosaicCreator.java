import java.awt.*;

public class MosaicCreator {
    public static void main(String[] args) {
        System.out.println("How many rows?");
        int rows = TextIO.getlnInt();
        System.out.println("How many columns?");
        int columns = TextIO.getlnInt();
        int width = 120;
        int height = 100;
        Mosaic.open(rows, columns, width, height);
        int currRow = 0;
        int currCol = 0;
        while (Mosaic.isOpen()) {
            // Mosaic.setColor(rows, columns, Color.BLACK);
            int printRow = currRow + 1;
            System.out.println("Row " + printRow + " Colors: ");
            String colorString = TextIO.getlnString();
            char[] colorArray = colorString.toCharArray();
            Color[] colors = parseColorArray(colorArray);
            colors = parseColorArray(colorArray);
            setMosaicColor(colors, currRow);
            currRow++;
            /*
             * At this point, the mosaic begins changing colors every 1 second (1000
             * milliseconds) according to the following color rotation: red -> cyan ->
             * yellow -> white -> green -> blue -> magenta -> black Note how the color
             * change does not have any delay, all tiles should change colors at the same
             * time. The mosaic should continue changing colors as long as the window is
             * open
             */

            // cycle the colors every 1 second
            Mosaic.delay(1000);
            Mosaic.setColor(rows, columns, Color.BLACK);
            if (currCol == columns) {
                currCol = 0;
                currCol++;
            }
            if (currRow == rows) {
                currRow = 0;
            }
        }
    }

    /**
     * 
     * Prompts the user for the colors of the columns using the following code: r
     * for red, g for green, b for blue, y for yellow, c for cyan, m for magenta, w
     * for white, and any other character (including spaces) for black.
     * 
     * @param input Char input from user
     * @return Color of the input
     */

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

    /**
     * Returns the color of the input of String parsed into char array
     * 
     * @param colorArray
     * @return Color of the inputs
     */
    private static Color[] parseColorArray(char[] colorArray) {
        Color[] colors = new Color[colorArray.length];
        for (int i = 0; i < colorArray.length; i++) {
            colors[i] = getColor(colorArray[i]);
        }
        return colors;
    }

    private static Color[] rotateColors() {
        Color[] colors = { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.WHITE };
        Color temp = colors[0];
        for (int i = 0; i < colors.length - 1; i++) {
            colors[i] = colors[i + 1];
        }
        colors[colors.length - 1] = temp;
        return colors;
    }

    private static void setMosaicColor(Color[] colors, int currRow) {
        for (int i = 0; i < colors.length; i++) {
            Mosaic.setColor(currRow, i, colors[i]);
        }
    }
}