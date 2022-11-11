/** A chess engine.
 * This package is the API.
 */
package jchess.engine.chess;


/** The main API class.
 * It represents a chess game.
 */
public class Chess {
    private ChessBoard board;

    /** Default constructor. */
    public Chess() {
        board = new ChessBoard();
    }

    /** Get the chess board.
     * @return The board.
     */
    public ChessBoard getBoard() {
        return board;
    }

    /** Get player who plays.
     * @return This player. 'w' for white or 'b' for black.
     */
    public char getPlayer() {
        return board.getPlayer();
    }

    /** Undo the last move. */
    public void undo() {
        board.undo();
    }

    /** Makes a move.
     * @param move The move to do.
     */
    public void move(String move) {
        board.move(move);
    }

    /** Return a string that you can use to display.
     * It may be, for example : <br>
     * 8 r n b q k b n r <br>
     * 7 p p p p . p p p <br>
     * 6 . . . . . . . . <br>
     * 5 . . . . p . . . <br>
     * 4 . . . . P . . . <br>
     * 3 . . . . . N . . <br>
     * 2 P P P P . P P P <br>
     * 1 R N B Q K B . R <br>
     *   a b c d e f g h <br>
     * <br>
     * Last move was Ng1-f3.<br>
     * Black to play.<br>
     * @return A printable string.
     */
    @Override
    public String toString() {
        return String.format("%sLast move was %s.\n%s to play.\n",
                             board,
                             board.getHistory().length >= 1 ? board.getHistory()[board.getHistory().length - 1] : "",
                             getPlayer() == 'w' ? "White" : "Black");
    }
}
