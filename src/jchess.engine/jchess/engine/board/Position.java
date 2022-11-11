package jchess.engine.board;

/** Represents a position.
 * It can also parse a string
 */
public class Position {
    private int row;
    private int column;

    /** A constructor.
     * @param y The column ('a', 'b'..., 'h')
     * @param x The row (1, 2..., 8)
     */
    public Position(char y, int x) {
        this(y - 'a', x - 1);
    }

    /** A constructor.
     * @param y The column ('a', 'b'..., 'h')
     * @param x The row (1, 2..., 8)
     */
    public Position(char y, short x) {
        this(y, (int) x);
    }

    /** A constructor.
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

    /** A constructor.
     * @param y The column (0, 1..., 7)
     * @param x The row (0, 1..., 7)
     */
    public Position(short y, short x) {
        this((int) y, (int) x);
    }

    /** This constructor parses a string.
     * For example, "e4".
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

    /** Default constructor.
     * The default position is a1.
     */
    public Position() {
        this('a', 1);
    }

    /** Get the row.
     * @return The row (0, 1...).
     */
    public int getRow() {
        return row;
    }

    /** Set the row.
     * @param x The new value (0 for 1...).
     */
    public void setRow(int x) {
        if(x < 8 && x >= 0) {
            row = x;
        }
    }

    /** Get the column.
     * @return The column (0 for a, 1 for b...).
     */
    public int getColumn() {
        return column;
    }

    /** Set the column.
     * @param x The new value (0 for a...).
     */
    public void setColumn(int x) {
        if(x < 8 && x >= 0) {
            column = x;
        }
    }

    /** Set the column.
     * @param x The new value.
     */
    public void setColumn(char x) {
        if(x - 'a' < 8 && x - 'a' >= 0) {
            column = x - 'a';
        }
    }

    /** Increments the row.
     * @param i The increment number.
     */
    public void incrementRow(int i) {
        setRow(row + i);
    }

    /** Increments the column by i
     * @param i The increment number.
     */
    public void incrementColumn(int i) {
        setColumn(column + i);
    }

    /** Increments the row. */
    public void incrementRow() {
        incrementRow(1);
    }

    /** Increments the column. */
    public void incrementColumn() {
        incrementColumn(1);
    }

    /** Return a string to represent a move.
     * @return A string that can be displayed. For example, "e4".
     */
    @Override
    public String toString() {
        return Character.toString(column + 'a') + Integer.toString(row + 1);
    }
}
