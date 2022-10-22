package jchess.engine.board;

// Represents a position
public class Position {
    private int row;
    private int column;

    // A constructor (y = row, x = column)
    public Position(char y, int x) {
        column = y - 'a';
        row = x - 1;

        if(column < 0 || column > 7 || row < 0 || row > 7) {
            System.err.println("ERROR: " + y + x + " isn't a valid position.");
            column = 0;
            row = 0;
        }
    }

    // A constructor (y = row, x = column)
    public Position(char y, short x) {
        this(y, (int) x);
    }

    // A constructor (y = row, x = column)
    public Position(int y, int x) {
        column = y;
        row = x;

        if(column < 0 || column > 7 || row < 0 || row > 7) {
            System.err.println("ERROR: " + y + x + " isn't a valid position.");
            column = 0;
            row = 0;
        }
    }

    // A constructor (y = row, x = column)
    public Position(short y, short x) {
        this((int) y, (int) x);
    }

    // Default constructor
    public Position() {
        this('a', 1);
    }

    // Get the row (first number)
    public int getRow() {
        return row;
    }

    // Get the column (second number)
    public int getColumn() {
        return column;
    }

    // Increment the row by i
    public void incrementRow(int i) {
        if(row + i < 8 && row + i >= 0) {
            row += i;
        }
    }

    // Increment the column by i
    public void incrementColumn(int i) {
        if(column + i < 8 && column + i >= 0) {
            column += i;
        }
    }

    // Increment the row
    public void incrementRow() {
        incrementRow(1);
    }

    // Increment the column
    public void incrementColumn() {
        incrementColumn(1);
    }
}
