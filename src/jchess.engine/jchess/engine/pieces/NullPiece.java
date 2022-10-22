package jchess.engine.pieces;

import jchess.engine.moves.Move;

// Null piece, for a blank case
public class NullPiece extends Piece {
    public NullPiece() {
        super();
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
