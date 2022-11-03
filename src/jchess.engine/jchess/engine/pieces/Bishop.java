package jchess.engine.pieces;

import jchess.engine.board.Position;
import jchess.engine.moves.Move;
import jchess.engine.board.Player;

// Bishop
public class Bishop extends Piece {
    public Bishop(Player player, Position pos) {
        super('b', player, pos);
    }

    public Bishop() {
        this(Player.none, new Position());
    }

    @Override
    public boolean canMove(Move move) {
        return move.isBlankDiagonal();
    }
}
