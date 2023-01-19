package jchess.engine.pieces;

import jchess.engine.board.Position;
import jchess.engine.moves.Move;
import jchess.engine.moves.Take;
import jchess.engine.board.Player;

/** The knight. */
public class Knight extends Piece {
    /** Creates a new knight.
     * @param player The owner.
     * @param pos The position of the knight.
     */
    public Knight(Player player, Position pos) {
        super('n', player, pos);
    }

    /** Creates a new knight.
     * It hasn't got any owner and it's in a1.
     */
    public Knight() {
        this(Player.NONE, new Position());
    }

    @Override
    public boolean canMove(Move move) {
        return move.isL();
    }

    @Override
    public boolean canTake(Take move) {
        return canMove(move);
    }
}
