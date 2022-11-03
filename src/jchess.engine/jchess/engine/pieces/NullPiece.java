package jchess.engine.pieces;

import jchess.engine.board.Position;
import jchess.engine.moves.Move;

// Null piece, for a blank case
public class NullPiece extends Piece {
    /** Default constructor */
    public NullPiece() {
        super();
    }

    /** Constructor
     * @param pos The position of the piece.
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
