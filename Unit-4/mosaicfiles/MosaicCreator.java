import java.awt.*;

public class MosaicCreator {
    // This took 10 hours im not joking and have no idea why.
    // And am so happy that I got it to work.
    // Thank you Mr.Smith :)
    public static void main(String[] args) {
        System.out.println("How many rows?");
        int rows = TextIO.getlnInt();
        System.out.println("How many columns?");
        int columns = TextIO.getlnInt();
        int width = 80;
        int height = 80;
        Mosaic.open(rows, columns, width, height);
        int currRow = 0;
        int currCol = 0;
        while (Mosaic.isOpen()) {
            int printRow = currRow + 1;
            System.out.println("Row " + printRow + " Colors: ");
            String colorString = TextIO.getlnString();
            char[] colorArray = colorString.toCharArray();
            Color[] colors = parseColorArray(colorArray);
            colors = cleanColors(colors, columns);
            if (currRow >= rows || currCol >= columns) {
                System.out.println("Row " + printRow + " Colors: ");
                colorString = TextIO.getlnString();
                colorArray = colorString.toCharArray();
                colors = parseColorArray(colorArray);
                currRow = 0;
                currCol = 0;
            }
            if (colors.length != columns) {
                Mosaic.setColor(currRow, currCol, Color.BLACK);
                currCol++;
            }
            setRowColor(colors, currRow, currCol, columns);
            currRow++;
            if (currCol == columns) {
                currCol = 0;
            }
            while (currRow == rows) {
                // Why is there a null pointer exception here?
                Mosaic.delay(1000);
                colorDance(currRow, currCol, columns);
            }
        }
    }

    private static void colorDance(int currRow, int currCol, int columns) {
        Color[][] mosaicColors = saveMosaicColors(currRow, columns);
        mosaicColors = shift2DColorArray(mosaicColors, currRow, columns);
        setMosaicColors(mosaicColors, currRow, columns);
    }

    private static void setMosaicColors(Color[][] mosaicColors, int currRow, int columns) {
        for (int i = 0; i < mosaicColors.length; i++) {
            for (int j = 0; j < mosaicColors[i].length; j++) {
                Mosaic.setColor(i, j, mosaicColors[i][j]);
            }
        }
    }

    private static Color[][] shift2DColorArray(Color[][] color, int row, int col) {
        color = saveMosaicColors(row, col);
        Color[][] shiftedColors = saveMosaicColors(row, col);
        for (int i = 0; i < shiftedColors.length; i++) {
            for (int j = 0; j < shiftedColors[i].length; j++) {
                shiftedColors[i][j] = shiftColor(color[i][j]);
            }
        }
        return shiftedColors;
    }

    private static Color[][] saveMosaicColors(int currRow, int columns) {
        Color[][] colors = new Color[currRow][columns];
        for (int i = 0; i < currRow; i++) {
            for (int j = 0; j < columns; j++) {
                colors[i][j] = Mosaic.getColor(i, j);
            }
        }
        return colors;
    }

    private static Color shiftColor(Color color) {
        // This creates a null pointer exception
        // I have no idea why.
        Color shiftedColor = color;
        try {
            if (color.equals(null)) {
                shiftedColor = Color.RED;
            } else if (color.equals(Color.RED)) {
                shiftedColor = Color.CYAN;
            } else if (color.equals(Color.CYAN)) {
                shiftedColor = Color.YELLOW;
            } else if (color.equals(Color.YELLOW)) {
                shiftedColor = Color.WHITE;
            } else if (color.equals(Color.WHITE)) {
                shiftedColor = Color.GREEN;
            } else if (color.equals(Color.GREEN)) {
                shiftedColor = Color.BLUE;
            } else if (color.equals(Color.BLUE)) {
                shiftedColor = Color.MAGENTA;
            } else if (color.equals(Color.MAGENTA)) {
                shiftedColor = Color.BLACK;
            } else {
                shiftedColor = Color.RED;
            }
        } catch (NullPointerException e) {
        }
        return shiftedColor;
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
            case ' ': // Fixes null ptr exception
                color = Color.BLACK;
                break;
            default:
                color = Color.BLACK;
                break;
        }
        return color;
    }

    /**
     * Returns the color of the input of char array
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

    private static void setRowColor(Color[] colors, int currRow, int currCol, int columns) {
        for (int i = 0; i < colors.length; i++) {
            Mosaic.setColor(currRow, currCol, colors[i]);
            currCol++;
        }
    }

    /**
     * Removes colors that are longer than the columns
     * 
     * @param colors
     * @param columns
     * @return Color array with the colors removed
     */
    private static Color[] cleanColors(Color[] colors, int columns) {
        Color[] newColors = new Color[columns];
        for (int i = 0; i < colors.length; i++) {
            if (i < columns) {
                newColors[i] = colors[i];
            }
        }
        return newColors;
    }
}