import java.awt.*;

public class MosaicCreator {
    // This took 10 hours im not joking and have no idea why.
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
            Color[] temp = new Color[colors.length];

            setMosaicColor(colors, currRow, currCol, columns);
            // Save each row's colors
            for (int i = 0; i < rows; i++) {
                saveRowColor(colors, currRow, currCol, columns);
            }
            currRow++;
            if (currCol == columns) {
                currCol = 0;
            }
            if (currRow == rows) {
                Mosaic.delay(1);
                colorDance(temp, currRow, currCol, columns);
            }

        }
    }

    /*
     * At this point, the mosaic begins changing colors every 1 second (1000
     * milliseconds) according to the following color rotation: red -> cyan ->
     * yellow -> white -> green -> blue -> magenta -> black
     */
    private static void colorDance(Color[] colors, int currRow, int currCol, int columns) {
        Color[] shiftedColors = shiftColorArray(colors);
        setMosaicColor(shiftedColors, currRow, currCol, columns);
    }

    private static Color[] saveRowColor(Color[] colors, int currRow, int currCol, int columns) {
        Color[] temp = new Color[colors.length];
        for (int i = 0; i < columns; i++) {
            temp[i] = colors[i];
        }
        return temp;
    }

    private static Color shiftColor(Color color) {
        Color shiftedColor;
        if (color.equals(Color.RED)) {
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
        return shiftedColor;
    }

    private static Color[] shiftColorArray(Color[] colors) {
        Color[] shiftedColors = new Color[colors.length];
        for (int i = 0; i < colors.length; i++) {
            shiftedColors[i] = shiftColor(colors[i]);
        }
        return shiftedColors;
    }

    private static Color[] saveColors(Color[] colors, int currRow, int currCol) {
        Color[] savedColors = new Color[colors.length];
        for (int i = 0; i < colors.length; i++) {
            savedColors[i] = colors[i];
        }
        return savedColors;
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

    private static void setMosaicColor(Color[] colors, int currRow, int currCol, int columns) {
        if (colors.length != columns) {
            for (int i = 0; i < colors.length; i++) {
                Mosaic.setColor(currRow, currCol, colors[i]);
                currCol++;
            }
        } else {
            for (int i = 0; i < colors.length; i++) {
                Mosaic.setColor(currRow, currCol, colors[i]);
                currCol++;
            }
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