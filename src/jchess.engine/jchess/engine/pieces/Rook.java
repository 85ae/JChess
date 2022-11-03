package jchess.engine.pieces;

import jchess.engine.board.Position;
import jchess.engine.moves.Move;
import jchess.engine.board.Player;

// Rook
public class Rook extends Piece {
    public Rook(Player player, Position pos) {
        super('r', player, pos);
    }

    public Rook() {
        this(Player.none, new Position());
    }

    @Override
    public boolean canMove(Move move) {
        return move.isBlankLine();
    }
}
