package jchess.engine.pieces;

import jchess.engine.board.Position;
import jchess.engine.moves.Move;
import jchess.engine.board.Player;

// King
public class King extends Piece {
    public King(Player player, Position pos) {
        super('k', player, pos);
    }

    public King() {
        this(Player.none, new Position());
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
