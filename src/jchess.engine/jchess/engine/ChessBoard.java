package jchess.engine;

import jchess.engine.board.*;
import jchess.engine.parser.MoveParser;

/**
 * This is a representation of a chess board.
 * It contains advanced board management methods, getters and others.
 */
public class ChessBoard {
    private Board board;

    /** The default constructor.<br/>
     * Initialize the board with the default board (rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR).
     */
    public ChessBoard() {
        board = new Board();
        board.initialize();
    }

    /** Get a piece.
     * @param position the postion where the piece is ("e4", "h2"...)
     * @return The piece situated here.
     */
    public ChessPiece getPiece(String position) {
        return new ChessPiece(board.getPiece(new Position(position)));
    }

    /** Get the player who plays.
     * @return 'w' for white or 'b' for black.
     */
    public char getPlayer() {
        return board.getPlayer() == Player.WHITE ? 'w' : 'b';
    }

    /** Move (a) piece(s) if possible.
     * @param moves the move(s) to do.
     */
    public void move(String... moves) {
        for(String move : moves) {
            board.move(new MoveParser(board, getPlayer() == 'w').parse(move));
        }
    }

    /** Undo the last move. */
    public void undo() {
        board.undo();
    }

    /** Return the history.
     * @return The history.
     */
    public String[] getHistory() {
        String[] moves = new String[board.getHistory().length];
        for(int i = 0; i < moves.length - 1; i++) {
            moves[i] = board.getHistory()[i].toString();
        }
        return moves;
    }

    /** Return a representation of the board.
     * @return A printable string.
     */
    @Override
    public String toString() {
        return board.toString();
    }
}
