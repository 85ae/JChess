package jchess.engine.pieces;

import jchess.engine.board.Position;
import jchess.engine.moves.Move;
import jchess.engine.moves.Take;
import jchess.engine.board.Player;

/** The pawn. */
public class Pawn extends Piece {
    /** Creates a new pawn.
     * @param player The owner.
     * @param pos The position of the pawn.
     */
    public Pawn(Player player, Position pos) {
        super('p', player, pos);
    }

    /** Creates a new pawn.
     * It hasn't got any owner and it's in a1.
     */
    public Pawn() {
        this(Player.NONE, new Position());
    }

    @Override
    public boolean canMove(Move move) {
        if(move.isVerticalLine()) {
            if(getPlayer() == Player.WHITE && move.verticalDistance() == 1 || getPlayer() == Player.BLACK && move.verticalDistance() == -1) {
                return true;
            } else if(player == Player.WHITE && move.isOnRow(2) && move.verticalDistance() == 2) { // if move by 2 at the first move
                return true;
            } else if(player == Player.BLACK && move.isOnRow(7) && move.verticalDistance() == -2) { // if move by 2 at the first move
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canTake(Take move) {
        if(move.horizontalDistance() == 1 || move.horizontalDistance() == -1) {
            if(getPlayer() == Player.WHITE && move.verticalDistance() == 1 || getPlayer() == Player.BLACK && move.verticalDistance() == -1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
