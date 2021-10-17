import java.awt.*;

public class MosaicCreator {

    /**
     * @param args
     */
    // This took 10 hours im not joking and have no idea why.
    // And am so happy that I got it to work.
    // Thank you Mr.Smith :)
    // It is 1am and I am still working on it.
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
        Mosaic.fill(Color.BLACK);
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
            setRowColor(colors, currRow, currCol, columns);
            currRow++;
            if (currCol == columns) {
                currCol = 0;
            }
            while (currRow == rows) {
                // Why is there a null pointer exception here?
                // It was because of the cleanColors method.
                Mosaic.delay(1000);
                colorDance(currRow, currCol, columns);
            }
        }
    }

    /**
     * Cycles though the colors acording to the pattern.
     * 
     * @param currRow
     * @param currCol
     * @param columns
     */
    private static void colorDance(int currRow, int currCol, int columns) {
        Color[][] mosaicColors = saveMosaicColors(currRow, columns);
        mosaicColors = shift2DColorArray(mosaicColors, currRow, columns);
        setMosaicColors(mosaicColors, currRow, columns);
    }

    /**
     * Sets the colors of enite mosaic to the colors in the array
     * 
     * @param mosaicColors
     * @param currRow
     * @param columns
     */
    private static void setMosaicColors(Color[][] mosaicColors, int currRow, int columns) {
        for (int i = 0; i < mosaicColors.length; i++) {
            for (int j = 0; j < mosaicColors[i].length; j++) {
                Mosaic.setColor(i, j, mosaicColors[i][j]);
            }
        }
    }

    /**
     * Shifts the colors in the 2D array according to the pattern
     * 
     * @param color
     * @param row
     * @param col
     * @return Color[][]
     */
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

    /**
     * Saves the current colors of the mosaic into 2d array of colors.
     * 
     * @param currRow
     * @param columns
     * @return Color[][]
     */
    private static Color[][] saveMosaicColors(int currRow, int columns) {
        Color[][] colors = new Color[currRow][columns];
        for (int i = 0; i < currRow; i++) {
            for (int j = 0; j < columns; j++) {
                colors[i][j] = Mosaic.getColor(i, j);
            }
        }
        return colors;
    }

    /**
     * @param color
     * @return Color
     */
    private static Color shiftColor(Color color) {
        Color shiftedColor = color;
        try {
            if (color.equals(null)) {
                shiftedColor = Color.BLACK;
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
            System.out.println("NullPointerException");
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

    /**
     * Sets the color of the row to the input color array from the user.
     * 
     * @param colors
     * @param currRow
     * @param currCol
     * @param columns
     */
    private static void setRowColor(Color[] colors, int currRow, int currCol, int columns) {
        for (int i = 0; i < colors.length; i++) {
            Mosaic.setColor(currRow, currCol, colors[i]);
            currCol++;
        }
    }

    /**
     * Removes colors that are longer than the columns and fills the rest with black
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
        // This is to fix the null pointer exception and took me 3 hours
        for (int j = 0; j < newColors.length; j++) {
            if (newColors[j] == null) {
                newColors[j] = Color.BLACK;
            }
        }
        return newColors;
    }

}