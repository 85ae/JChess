package jchess.engine.pieces;

import jchess.engine.board.Position;
import jchess.engine.moves.Move;
import jchess.engine.board.Player;

/** The bishop. */
public class Bishop extends Piece {
    /** Creates a new bishop.
     * @param player The owner.
     * @param pos The position of the bishop.
     */
    public Bishop(Player player, Position pos) {
        super('b', player, pos);
    }

    /** Creates a new bishop.
     * It hasn't got any owner and it's in a1.
     */
    public Bishop() {
        this(Player.NONE, new Position());
    }

    @Override
    public boolean canMove(Move move) {
        return move.isBlankDiagonal();
    }
}
