package jchess.engine.pieces;

import jchess.engine.moves.Move;
import jchess.engine.players.*;

// Rook
public class Rook extends Piece {
    public Rook(Player player) {
        super('r', player);
    }

    public Rook() {
        this(new NullPlayer());
    }

    @Override
    public boolean canMove(Move move) {
        return move.isBlankLine();
    }
}
