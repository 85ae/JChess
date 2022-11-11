package jchess.engine.pieces;

import jchess.engine.board.Position;
import jchess.engine.moves.Move;
import jchess.engine.board.Player;

/** The king.
 * The most important piece.
 */
public class King extends Piece {
    /** Creates a new king.
     * @param player The owner.
     * @param pos The position of the king.
     */
    public King(Player player, Position pos) {
        super('k', player, pos);
    }

    /** Creates a new king.
     * It hasn't got any owner and it's in a1.
     */
    public King() {
        this(Player.NONE, new Position());
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
