package pieces;

import moves.Move;
import players.*;

// Queen
public class Queen extends Piece {
    public Queen(Player player) {
        super("queen", player);
    }

    public Queen() {
        super("queen", new NullPlayer());
    }

    @Override
    public boolean canMove(Move move) {
        return move.isBlankLine() || move.isBlankDiagonal();
    }
}
