package jchess.engine.chess;

import jchess.engine.board.*;
import jchess.engine.parser.MoveParser;

/** This class represents a chess board. */
public class ChessBoard {
    private Board board;

    /** The default constructor.
     * Initialize the board with the default board (rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR).
     */
    public ChessBoard() {
        board = new Board();
        board.initialize();
    }

    /** Get a piece.
     * @param position The postion where the piece is.
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
     * @param moves The move(s) to do.
     */
    public void move(String... moves) {
        MoveParser[] parsers = new MoveParser[moves.length];
        for(int i = 0; i < moves.length; i++) {
            parsers[i] = (MoveParser)new MoveParser(board, getPlayer() == 'w').parse(moves[i]);
        }
        board.move(parsers);
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
