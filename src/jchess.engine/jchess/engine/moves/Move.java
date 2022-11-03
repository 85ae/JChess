package jchess.engine.moves;

import jchess.engine.board.*;
import jchess.engine.pieces.Piece;

// Represents a move
public class Move {
    protected Position oldPos, newPos;
    protected Board board;
    protected Verifier verifier;

    /** Constructor
     * @param oldPos The old position.
     * @param newPos The new position.
     * @param board The board.
     */
    public Move(Position oldPos, Position newPos, Board board) {
        this.oldPos = oldPos;
        this.newPos = newPos;
        this.board = board;
        this.verifier = new Verifier(board);
    }

    // Verify if it's a correct move - override to take a piece
    public boolean isCorrect() {
        if(!oldPos.equals(newPos) && getPiecePlayer(newPos) == Player.none) {
            return  getPiece().canMove(this);
        } else {
            return false;
        }
    }


    // Get the old position
    public Position getOldPosition() {
        return oldPos;
    }

    // Get the new position
    public Position getNewPosition() {
        return newPos;
    }

    // Get the board
    public Board getBoard() {
        return board;
    }

    /** Get the Verifier object
     * @return The jchess.engine.moves.Verifier object.
     */
    public Verifier verify() {
        return verifier;
    }

    /** Get the piece */
    public Piece getPiece() {
        return verify().getPiece(oldPos);
    }

    // Get the owner of the piece situated at the position pos
    public Player getPiecePlayer(Position pos) {
        return verify().getPiecePlayer(pos);
    }

    /** Get the owner of the piece */
    public Player getPiecePlayer() {
        return getPiece().getPlayer();
    }

    // Return the vertical distance of move
    public int verticalDistance() {
        return newPos.getRow() - oldPos.getRow();
    }

    // Return the horizontal distance of move
    public int horizontalDistance() {
        return newPos.getColumn() - oldPos.getColumn();
    }

    // Verify if it's an horizontal line
    public boolean isHorizontalLine() {
        return verify().isHorizontalLine(oldPos, newPos);
    }

    // Verify if it's a vertical line
    public boolean isVerticalLine() {
        return verify().isVerticalLine(oldPos, newPos);
    }

    // Verify if it's a line
    public boolean isLine() {
        return verify().isLine(oldPos, newPos);
    }

    // Verify that it's a diagonal from bottom - left to top - right (or opposite)
    public boolean isBottomLeft2TopRightDiagonal() {
        return verify().isBottomLeft2TopRightDiagonal(oldPos, newPos);
    }

    // Verify that it's a diagonal from top - left to bottom - right (or opposite)
    public boolean isTopLeft2BottomRightDiagonal() {
        return verify().isTopLeft2BottomRightDiagonal(oldPos, newPos);
    }

    // Verify that it's a diagonal
    public boolean isDiagonal() {
        return verify().isDiagonal(oldPos, newPos);
    }

    // Verify that there's no piece between oldPos and newPos
    public boolean isBlankLine() {
        return verify().isBlankLine(oldPos, newPos);
    }

    // Verify that there's no piece in the diagonal between oldPos and newPos
    public boolean isBlankDiagonal() {
        return verify().isBlankDiagonal(oldPos, newPos);
    }

    // Verify that it makes a 'L'
    public boolean isL() {
        return verify().isL(oldPos, newPos);
    }

    // Verify if the piece is on the x row
    public boolean isOnRow(int x) {
        return oldPos.getRow() == x - 1;
    }

    // Verify if the piece is on the x column
    public boolean isOnColumn(int x) {
        return oldPos.getColumn() == x + 1;
    }

    /** Return the representation of a move
     * @return A string to display.
     */
    @Override
    public String toString() {
        String output;

        switch(getPiece().getSymbol()) {
            case 'p':
                output = "";
                break;

            case '.':
                return "ERROR: A blank case can't move.";

            default:
                output = "" + Character.toUpperCase(getPiece().getSymbol());
            }

        output += oldPos;
        output += '-';
        output += newPos;

        return output;
    }
}
