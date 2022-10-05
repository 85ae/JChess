package pieces;

import moves.Move;
import players.*;

// Knight
public class Knight extends Piece {
    public Knight(Player player) {
        super("knight", player);
    }

    public Knight() {
        super("knight", new NullPlayer());
    }

    @Override
    public boolean canMove(Move move) {
        return move.isL();
    }
}
