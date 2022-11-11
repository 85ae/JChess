package jchess.engine.pieces;

import jchess.engine.board.Position;
import jchess.engine.moves.Move;

/** Null piece.
 * It's used to represent a blank case.
 */
public class NullPiece extends Piece {
    /** Default constructor.
     * Creates a blank case in a1.
     */
    public NullPiece() {
        super();
    }

    /** Constructor.
     * @param pos The position of the case.
     */
    public NullPiece(Position pos) {
        super(pos);
    }

    @Override
    public boolean canMove(Move move) {
        return false;
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
