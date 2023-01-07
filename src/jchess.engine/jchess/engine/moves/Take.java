package jchess.engine.moves;

import jchess.engine.board.Position;
import jchess.engine.board.Board;

/** Represents a taking move.
 * You should use it to take a piece.
 */
public class Take extends Move {
    /** Constructor
     * @param oldPos The old position.
     * @param newPos The new position.
     * @param board The board.
     */
    public Take(Position oldPos, Position newPos, Board board) {
        super(oldPos, newPos, board);
    }

    /** Verify if it's a correct move.
     * @return True if it's a correct move, false else.
     */
    public boolean isCorrect() {
        if(!oldPos.equals(newPos) && /* take other player */ verify().getPiecePlayer(newPos).isOpposite(getPiecePlayer()) && getPiecePlayer() == board.getPlayer()) {
            return getPiece().canMove(this);
        } else {
            return false;
        }
    }
}
