package jchess.engine.pieces;

import jchess.engine.moves.Move;
import jchess.engine.players.*;

// Queen
public class Queen extends Piece {
    public Queen(Player player) {
        super('q', player);
    }

    public Queen() {
        this(new NullPlayer());
    }

    @Override
    public boolean canMove(Move move) {
        return move.isBlankLine() || move.isBlankDiagonal();
    }
}
