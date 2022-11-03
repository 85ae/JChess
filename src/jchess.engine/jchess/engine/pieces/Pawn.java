package jchess.engine.pieces;

import jchess.engine.board.Position;
import jchess.engine.moves.Move;
import jchess.engine.board.Player;

// Pawn
public class Pawn extends Piece {
    public Pawn(Player player, Position pos) {
        super('p', player, pos);
    }

    public Pawn() {
        this(Player.none, new Position());
    }

    @Override
    public boolean canMove(Move move) {
        if(move.isVerticalLine()) {
            if(getPlayer() == Player.white && move.verticalDistance() == 1 || getPlayer() == Player.black && move.verticalDistance() == - 1) {
                return true;
            } else if(player == Player.white && move.isOnRow(2) && move.verticalDistance() == 2) { // if move by 2 at the first move
                return true;
            } else if(player == Player.black && move.isOnRow(7) && move.verticalDistance() == -2) { // if move by 2 at the first move
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
