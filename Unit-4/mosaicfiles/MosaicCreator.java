import java.awt.*;

public class MosaicCreator {
    public static void main(String[] args) {
        System.out.println("How many rows?");
        int rows = TextIO.getlnInt();
        System.out.println("How many columns?");
        int columns = TextIO.getlnInt();
        Mosaic.open(rows, columns);
        int currRow = 0;
        int currCol = 0;
        while (Mosaic.isOpen()) {
            System.out.println("What Color? ");
            char input = TextIO.getlnChar();
            Color color = getColor(input);
            Mosaic.setColor(currRow, currCol, color);
            if (currCol == columns) {
                currCol = 0;
                currCol++;
            }
            if (currRow == rows) {
                currRow = 0;
            }

        }
    }

    /*
     * Prompt the user (row by row) for the colors of the columns using the
     * following code: r for red, g for green, b for blue, y for yellow, c for cyan,
     * m for magenta, w for white, and any other character (including spaces) for
     * black
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
}
