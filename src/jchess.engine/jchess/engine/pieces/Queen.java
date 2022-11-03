package jchess.engine.pieces;

import jchess.engine.board.Position;
import jchess.engine.moves.Move;
import jchess.engine.board.Player;

// Queen
public class Queen extends Piece {
    public Queen(Player player, Position pos) {
        super('q', player, pos);
    }

    public Queen() {
        this(Player.none, new Position());
    }

    @Override
    public boolean canMove(Move move) {
        return move.isBlankLine() || move.isBlankDiagonal();
    }
}
