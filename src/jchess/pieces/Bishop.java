package pieces;

import moves.Move;
import players.*;

// Bishop
public class Bishop extends Piece {
    public Bishop(Player player) {
        super("bishop", player);
    }

    public Bishop() {
        super("bishop", new NullPlayer());
    }

    @Override
    public boolean canMove(Move move) {
        return move.isBlankDiagonal();
    }
}
