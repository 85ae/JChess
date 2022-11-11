package jchess.engine.pieces;

import jchess.engine.board.Position;
import jchess.engine.moves.Move;
import jchess.engine.board.Player;

/** The queen. */
public class Queen extends Piece {
    /** Creates a new queen.
     * @param player The owner.
     * @param pos The position of the queen.
     */
    public Queen(Player player, Position pos) {
        super('q', player, pos);
    }

    /** Creates a new queen.
     * It hasn't got any owner and it's in a1.
     */
    public Queen() {
        this(Player.NONE, new Position());
    }

    @Override
    public boolean canMove(Move move) {
        return move.isBlankLine() || move.isBlankDiagonal();
    }
}
