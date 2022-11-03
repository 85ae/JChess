package jchess.engine.pieces;

import jchess.engine.board.Position;
import jchess.engine.moves.Move;
import jchess.engine.board.Player;

// Knight
public class Knight extends Piece {
    public Knight(Player player, Position pos) {
        super('n', player, pos);
    }

    public Knight() {
        this(Player.none, new Position());
    }

    @Override
    public boolean canMove(Move move) {
        return move.isL();
    }
}
