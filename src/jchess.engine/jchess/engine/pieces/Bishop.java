package jchess.engine.pieces;

import jchess.engine.moves.Move;
import jchess.engine.players.*;

// Bishop
public class Bishop extends Piece {
    public Bishop(Player player) {
        super('b', player);
    }

    public Bishop() {
        this(new NullPlayer());
    }

    @Override
    public boolean canMove(Move move) {
        return move.isBlankDiagonal();
    }
}
