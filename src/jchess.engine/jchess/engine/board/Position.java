package jchess.engine.board;

/** Represents a position.
 * It can also parse a string
 */
public record Position(int row, int column) {

    /** A constructor.
     * @param column The column ('a', 'b'..., 'h')
     * @param row The row (1, 2..., 8)
     */
    public Position(char column, int row) {
        this(column - 'a', row - 1);
    }

    /** A constructor.
     * @param y The column (0, 1..., 7)
     * @param x The row (0, 1..., 7)
     */
    public Position(int row, int column) {
        if(column < 0 || column > 7 || row < 0 || row > 7) {
            System.err.println("ERROR: " + column + row + " isn't a valid position.");
            this.column = 0;
            this.row = 0;
        } else {
            this.column = column;
            this.row = row;
        }
    }

    /** This constructor parses a string.
     * For example, "e4".
     * @param pos The position to parse.
     */
    public Position(String pos) {
        this(
            Character.getNumericValue(pos.charAt(1)) - 1,
            pos.charAt(0)
        );
    }

    /** Default constructor.
     * The default position is a1.
     */
    public Position() {
        this('a', 1);
    }

    public static Position incrementRow(Position pos) {
        if(pos.row == 7 /* 8 */) return pos;
        return new Position(pos.row + 1, pos.column);
    }

    public static Position incrementColumn(Position pos) {
        if(pos.column == 7 /* h */) return pos;
        return new Position(pos.row, pos.column + 1);
    }
}
