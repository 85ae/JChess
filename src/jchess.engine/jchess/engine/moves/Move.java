/** Contains some move definitions and utilities. */
package jchess.engine.moves;

import jchess.engine.board.*;
import jchess.engine.pieces.Piece;

/** Represents a move.
 * It represents a move, but not a taking move.
 * To take a piece, you must inherit this class.
 */
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

    /** Verify if it's a correct move.
     * Override to take a piece.
     * @return True if it's a correct move, false else.
     */
    public boolean isCorrect() {
        if(!oldPos.equals(newPos) && getPiecePlayer(newPos).isPlayer()) {
            return getPiece().canMove(this);
        } else {
            return false;
        }
    }


    /** Get the old position.
     * @return The old position.
     */
    public Position getOldPosition() {
        return oldPos;
    }

    /** Get the new position.
     * @return The new position.
     */
    public Position getNewPosition() {
        return newPos;
    }

    /** Get the board.
     * @return The chess board assigned to it.
     */
    public Board getBoard() {
        return board;
    }

    /** Get the Verifier object
     * @return The jchess.engine.moves.Verifier object.
     */
    public Verifier verify() {
        return verifier;
    }

    /** Get the piece.
     * @return The piece which was assigned to this move.
     */
    public Piece getPiece() {
        return verify().getPiece(oldPos);
    }

    /** Get the owner of the piece situated at the position pos.
     * @deprecated This method is not longer used here. Use verify.getPiecePlayer(Position) instead.
     */
    @Deprecated
    public Player getPiecePlayer(Position pos) {
        return verify().getPiecePlayer(pos);
    }

    /** Get the owner of the piece.
     * @return The piece owner.
     */
    public Player getPiecePlayer() {
        return getPiece().getPlayer();
    }

    /** Returns the vertical distance of move.
     * @return This distance.
     */
    public int verticalDistance() {
        return newPos.getRow() - oldPos.getRow();
    }

    /** Returns the horizontal distance of move.
     * @return This distance.
     */
    public int horizontalDistance() {
        return newPos.getColumn() - oldPos.getColumn();
    }

    /** Verify if it's an horizontal line.
     * @return The result.
     */
    public boolean isHorizontalLine() {
        return verify().isHorizontalLine(oldPos, newPos);
    }

    /** Verify if it's a vertical line.
     * @return The result.
     */
    public boolean isVerticalLine() {
        return verify().isVerticalLine(oldPos, newPos);
    }

    /** Verify if it's a line.
     * @return The result.
     */
    public boolean isLine() {
        return verify().isLine(oldPos, newPos);
    }

    /** Verify that it's a diagonal from bottom - left to top - right (or opposite).
     * @return The result.
     */
    public boolean isBottomLeft2TopRightDiagonal() {
        return verify().isBottomLeft2TopRightDiagonal(oldPos, newPos);
    }

    /** Verify that it's a diagonal from top - left to bottom - right (or opposite).
     * @return The result.
     */
    public boolean isTopLeft2BottomRightDiagonal() {
        return verify().isTopLeft2BottomRightDiagonal(oldPos, newPos);
    }

    /** Verify that it's a diagonal.
     * @return The result.
     */
    public boolean isDiagonal() {
        return verify().isDiagonal(oldPos, newPos);
    }

    /** Verify that there's no piece between oldPos and newPos.
     * @return The result.
     */
    public boolean isBlankLine() {
        return verify().isBlankLine(oldPos, newPos);
    }

    /** Verify that there's no piece in the diagonal between oldPos and newPos.
     * @return The result.
     */
    public boolean isBlankDiagonal() {
        return verify().isBlankDiagonal(oldPos, newPos);
    }

    /** Verify that it makes a 'L'.
     * @return The result.
     */
    public boolean isL() {
        return verify().isL(oldPos, newPos);
    }

    /** Verify if the piece is on the x row.
     * @param x The row.
     * @return The result.
     */
    public boolean isOnRow(int x) {
        return oldPos.getRow() == x - 1;
    }

    /** Verify if the piece is on the x column.
     * @param x The column.
     * @return The result.
     */
    public boolean isOnColumn(int x) {
        return oldPos.getColumn() == x + 1;
    }

    /** Returns the representation of a move.
     * @return A move like "Bf1-c4". Returns "ERROR: A blank case can't move." if it's an uncorrect move.
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
