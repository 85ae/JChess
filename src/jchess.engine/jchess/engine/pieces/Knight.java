package jchess.engine.pieces;

import jchess.engine.moves.Move;
import jchess.engine.players.*;

// Knight
public class Knight extends Piece {
    public Knight(Player player) {
        super('n', player);
    }

    public Knight() {
        this(new NullPlayer());
    }

    @Override
    public boolean canMove(Move move) {
        return move.isL();
    }
}
