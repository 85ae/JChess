package jchess.engine.board;

import jchess.engine.pieces.*;
import jchess.engine.players.Color;
import jchess.engine.players.Player;

// Represent a case
public class Case {
    // The piece on a case
    private Piece piece;

    // Default constructor - create a blank case
    Case() {
        piece = new NullPiece();
    }

    // A constructor which sets the piece to 'piece'
    Case(Piece piece) {
        this.piece = piece;
    }

    // Get the piece symbol
    public char getPieceSymbol() {
        if(getPiecePlayer().getColor() == Color.White) {
            return Character.toUpperCase(piece.getSymbol());
        } else {
            return piece.getSymbol();
        }
    }

    // Get the player who has the piece
    public Player getPiecePlayer() {
        return piece.getPlayer();
    }

    // Get the piece
    public Piece getPiece() {
        return piece;
    }

    // Set the piece
    void setPiece(Piece piece) {
        this.piece = piece;
    }

    // Set the player who has the piece
    public void setPiecePlayer(Player player) {
        piece.setPlayer(player);
    }
}
