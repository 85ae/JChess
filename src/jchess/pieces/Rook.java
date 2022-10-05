package pieces;

import moves.Move;
import players.*;

// Rook
public class Rook extends Piece {
    public Rook(Player player) {
        super("rook", player);
    }

    public Rook() {
        super("rook", new NullPlayer());
    }

    @Override
    public boolean canMove(Move move) {
        return move.isBlankLine();
    }
}
