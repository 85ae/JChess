package pieces;

import moves.Move;
import players.*;

// King
public class King extends Piece {
    public King(Player player) {
        super("king", player);
    }

    public King() {
        super("king", new NullPlayer());
    }

    @Override
    public boolean canMove(Move move) {
        if(move.horizontalDistance() < 2 && move.horizontalDistance() > -2 && move.verticalDistance() < 2 && move.verticalDistance() > -2) {
            //TODO: Faire un code de vérification de mise en échec
            return true;
        } else {
            return false;
        }
    }
}
