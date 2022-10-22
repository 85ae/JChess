package jchess.engine.pieces;

import jchess.engine.moves.Move;
import jchess.engine.players.*;

// Pawn
public class Pawn extends Piece {
    public Pawn(Player player) {
        super('p', player);
    }

    public Pawn() {
        this(new NullPlayer());
    }

    @Override
    public boolean canMove(Move move) {
        if(move.isVerticalLine()) {
            if(move.verticalDistance() == 1 || move.verticalDistance() == - 1) {
                return true;
            } else if(player.getColor() == Color.White && move.isOnRow(2) && move.verticalDistance() == 2) { // if move by 2 at the first move
                return true;
            } else if(player.getColor() == Color.Black && move.isOnRow(7) && move.verticalDistance() == -2) { // if move by 2 at the first move
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
