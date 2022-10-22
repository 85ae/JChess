package jchess.engine.board;

import jchess.engine.moves.Move;
import jchess.engine.pieces.*;
import jchess.engine.players.*;

// This class represent the chessboard
public class Board {
    private Case[][] board;
    private WhitePlayer white;
    private BlackPlayer black;

    // Construct the board with 
    public Board(WhitePlayer white, BlackPlayer black) {
        board = new Case[8][8];
        for(Case[] row : board) {
            for(int i = 0; i < row.length; i++) {
                row[i] = new Case();
            }
        }
        this.white = white;
        this.black = black;
    }

    // initialize the pieces
    public void initialize() {
        Piece[] firstRow = { new Rook(white), new Knight(white), new Bishop(white), new Queen(white), new King(white), new Bishop(white), new Knight(white), new Rook(white) };
        Piece[] lastRow = { new Rook(black), new Knight(black), new Bishop(black), new Queen(black), new King(black), new Bishop(black), new Knight(black), new Rook(black) };
        int length = firstRow.length;
        
        for(int i = 0; i < length; i++) {
            // first row
            setCase(new Position(i, 0), firstRow[i]);
            // last row
            setCase(new Position(i, 7), lastRow[i]);
            // row 2
            setCase(new Position(i, 1), new Pawn(white));
            // row 7
            setCase(new Position(i, 6), new Pawn(black));
        }
    }

    // Get the case situated at pos
    public Case getCase(Position pos) {
        return board[pos.getRow()][pos.getColumn()];
    }

    // Set the case situated at pos
    public void setCase(Position pos, Piece piece) {
        getCase(pos).setPiece(piece);
    }

    // Return the chessboard string
    @Override
    public String toString() {
        String output = "   ";
        for (int i = 0; i < board.length; i++) {
            String line = "" + i;
            for (int j = 0; j < board.length; j++) {
                Position pos = new Position(j, i);

                line += getCase(pos).getPieceSymbol();
                line += ' ';
            }
            output = line + '\n' + output;
        }

        return output;
    }

    // Move a piece
    public void move(Position oldPos, Position newPos) {
        Piece piece = getCase(oldPos).getPiece();
        Move move = new Move(piece, oldPos, newPos, this);
        if(move.isCorrect()) {
            setCase(oldPos, new NullPiece());
            setCase(newPos, piece);
        } else {
            System.err.println("ERROR: This piece can't move to this position.");
        }
    }
}
