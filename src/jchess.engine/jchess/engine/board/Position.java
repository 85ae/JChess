package jchess.engine.board;

// Represents a position
public class Position {
    private int row;
    private int column;

    /** A constructor
     * @param y The column ('a', 'b'..., 'h')
     * @param x The row (1, 2..., 8)
     */
    public Position(char y, int x) {
        this(y - 'a', x - 1);
    }

    /** A constructor
     * @param y The column ('a', 'b'..., 'h')
     * @param x The row (1, 2..., 8)
     */
    public Position(char y, short x) {
        this(y, (int) x);
    }

    /** A constructor
     * @param y The column (0, 1..., 7)
     * @param x The row (0, 1..., 7)
     */
    public Position(int y, int x) {
        column = y;
        row = x;

        if(column < 0 || column > 7 || row < 0 || row > 7) {
            System.err.println("ERROR: " + y + x + " isn't a valid position.");
            column = 0;
            row = 0;
        }
    }

    /** A constructor
     * @param y The column (0, 1..., 7)
     * @param x The row (0, 1..., 7)
     */
    public Position(short y, short x) {
        this((int) y, (int) x);
    }

    /** This constructor parses a string like "e4"
     * @param pos The position to parse.
     */
    public Position(String pos) {
        if(pos.length() >= 2) {
            row = 0;
            column = 0;
            setColumn(pos.charAt(0));
            setRow(Character.getNumericValue(pos.charAt(1)) - 1);
        }
    }

    /// Default constructor
    public Position() {
        this('a', 1);
    }

    // Get the row
    public int getRow() {
        return row;
    }

    /** Set the row
     * @param x The new value
     */
    public void setRow(int x) {
        if(x < 8 && x >= 0) {
            row = x;
        }
    }

    // Get the column
    public int getColumn() {
        return column;
    }

    /** Set the column
     * @param x The new value
     */
    public void setColumn(int x) {
        if(x < 8 && x >= 0) {
            column = x;
        }
    }

    /** Set the column
     * @param x The new value
     */
    public void setColumn(char x) {
        if(x - 'a' < 8 && x - 'a' >= 0) {
            column = x - 'a';
        }
    }

    // Increment the row by i
    public void incrementRow(int i) {
        setRow(row + i);
    }

    // Increment the column by i
    public void incrementColumn(int i) {
        setColumn(column + i);
    }

    // Increment the row
    public void incrementRow() {
        incrementRow(1);
    }

    // Increment the column
    public void incrementColumn() {
        incrementColumn(1);
    }

    /** Return a string to represent a move
     * @return A string that can be displayed.
     */
    @Override
    public String toString() {
        return Character.toString(column + 'a') + Integer.toString(row + 1);
    }
}
