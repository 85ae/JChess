package jchess.engine.pieces;

import jchess.engine.board.Position;
import jchess.engine.moves.Move;
import jchess.engine.board.Player;

/** The rook. */
public class Rook extends Piece {
    /** Creates a new rook.
     * @param player The owner.
     * @param pos The position of the rook.
     */
    public Rook(Player player, Position pos) {
        super('r', player, pos);
    }

    /** Creates a new rook.
     * It hasn't got any owner and it's in a1.
     */
    public Rook() {
        this(Player.NONE, new Position());
    }

    @Override
    public boolean canMove(Move move) {
        return move.isBlankLine();
    }
}
