package jchess.engine;

import jchess.engine.board.Player;
import jchess.engine.board.Position;
import jchess.engine.pieces.*;

/** Represents a chess piece. */
public class ChessPiece {
    private Piece piece;

    /** The default constructor.
     * Creates a blank piece at a1.
     */
    public ChessPiece() {
        this(' ', "a1", ' ');
    }

    /** Main constructor.
     * @param piece the piece symbol ('k' for king, 'p' for pawn...).
     * @param position the position of the piece.
     * @param player the player ('w' or 'b', ' ' for none).
     */
    public ChessPiece(char piece, String position, char player) {
        try {
            this.piece = (Piece)Piece.piece(piece).getConstructor(Player.class, Position.class).
                newInstance(player == 'w' ? Player.WHITE : (player == 'b' ? Player.BLACK : Player.NONE),
                            new Position(position));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /** Another constructor.
     * @param piece The piece.
     */
    ChessPiece(Piece piece) {
        this.piece = piece;
    }

    /** Get the piece.
     * Only for internal classes - objects.
     * @return The piece.
     */
    Piece getPiece() {
        return piece;
    }

    /** Return the position.
     * @return The position (like "e4").
     */
    public String getPosition() {
        Position pos = piece.getPosition();
        return Character.toString(pos.column() + 'a') + Integer.toString(pos.row() + 1);
    }

    /** Get the piece owner.
     * @return 'w' if white, 'b' if black and ' ' if null.
     */
    public char getOwner() {
        if(piece.getPlayer() == Player.WHITE) {
            return 'w';
        } else if(piece.getPlayer() == Player.BLACK) {
            return 'b';
        } else {
            return ' ';
        }
    }

    /** Return a character that represents the piece.
     * It can be 'N' for a white knight or 'p' for a black pawn.
     * @return The character. '.' for a null piece (blank case).
     */
    public char getSymbol() {
        char symbol = piece.getSymbol();
        if(getOwner() == 'w') {
            return Character.toUpperCase(symbol);
        } else {
            return symbol;
        }
    }

    /** Return a printable string.
     * For example, it can be "Be4" for a white bishop situated in e4.
     * @return A printable string.
     */
    @Override
    public String toString() {
        return Character.toString(getSymbol()) + getPosition();
    }
}
