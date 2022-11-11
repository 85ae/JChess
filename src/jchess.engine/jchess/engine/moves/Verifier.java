package jchess.engine.moves;

import jchess.engine.board.*;
import jchess.engine.pieces.Piece;

/** This class contains some verification methods to verify if a move is correct
 * It also works with taking.
 */
public class Verifier {
    private Board board;

    /** The constructor
     * @param board The board.
     */
    public Verifier(Board board) {
        this.board = board;
    }

    /** Get a piece
     * @param pos The position of the piece.
     * @return The piece.
     */
    public Piece getPiece(Position pos) {
        return board.getPiece(pos);
    }

    /** Get the owner of a piece.
     * @param pos The piece position.
     * @return The piece owner.
     */
    public Player getPiecePlayer(Position pos) {
        return getPiece(pos).getPlayer();
    }

    /** Verify if it's an horizontal line between two positions.
     * @param firstPos The first position.
     * @param lastPos The last position.
     * @return The result.
     */
    public boolean isHorizontalLine(Position firstPos, Position lastPos) {
        return firstPos.getRow() == lastPos.getRow();
    }

    /** Verify if it's a vertical line between two positions.
     * @param firstPos The first position.
     * @param lastPos The last position.
     * @return The result.
     */
    public boolean isVerticalLine(Position firstPos, Position lastPos) {
        return firstPos.getColumn() == lastPos.getColumn();
    }

    /** Verify if it's a line between two positions.
     * @param firstPos The first position.
     * @param lastPos The last position.
     * @return The result.
     */
    public boolean isLine(Position firstPos, Position lastPos) {
        return isHorizontalLine(firstPos, lastPos) || isVerticalLine(firstPos, lastPos);
    }

    /** Verify that it's a diagonal from bottom - left to top - right (or opposite) between two positions.
     * @param firstPos The first position.
     * @param lastPos The last position.
     * @return The result.
     */
    public boolean isBottomLeft2TopRightDiagonal(Position firstPos, Position lastPos) {
        return firstPos.getRow() - firstPos.getColumn() == lastPos.getRow() - lastPos.getColumn(); // Example: a2 and c4. a = 1, c = 3, 2 - 1 = 1, 4 - 3 = 1
    }

    /** Verify that it's a diagonal from top - left to bottom - right (or opposite) between two positions.
     * @param firstPos The first position.
     * @param lastPos The last position.
     * @return The result.
     */
    public boolean isTopLeft2BottomRightDiagonal(Position firstPos, Position lastPos) {
        return firstPos.getRow() + firstPos.getColumn() == lastPos.getRow() + lastPos.getColumn(); // Example: c2 and a4. c = 3, a = 1, 3 + 2 = 5, 1 + 4 = 5
    }

    /** Verify that it's a diagonal between two positions.
     * @param firstPos The first position.
     * @param lastPos The last position.
     * @return The result.
     */
    public boolean isDiagonal(Position firstPos, Position lastPos) {
        return isBottomLeft2TopRightDiagonal(firstPos, lastPos) || isTopLeft2BottomRightDiagonal(firstPos, lastPos);
    }

    /** Verify that there's no piece in the line between two positions.
     * @param firstPos The first position.
     * @param lastPos The last position.
     * @return The result.
     */
    public boolean isBlankLine(Position firstPos, Position lastPos) {
        if(isHorizontalLine(firstPos, lastPos)) { // if horizontal
            if(firstPos.getColumn() < lastPos.getColumn() - 1) { // lastPos after firstPos
                for(int i = firstPos.getColumn() + 1; i < lastPos.getColumn(); i++) { // Verify each case between those
                    if(getPiecePlayer(new Position(i, firstPos.getRow())).isPlayer()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else if(lastPos.getColumn() < firstPos.getColumn() - 1) { // lastPos before firstPos
                for(int i = lastPos.getColumn() + 1; i < firstPos.getColumn(); i++) { // Verify each case between those
                    if(getPiecePlayer(new Position(i, firstPos.getRow())).isPlayer()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else { // if there's no case between those
                return true;
            }
        } else if(isVerticalLine(firstPos, lastPos)) { // if vertical
            if(firstPos.getRow() < lastPos.getRow() - 1) { // lastPos after firstPos
                for(int i = firstPos.getRow() + 1; i < lastPos.getRow(); i++) { // Verify each case between those
                    if(getPiecePlayer(new Position(firstPos.getColumn(),i)).isPlayer()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else if(lastPos.getRow() < firstPos.getRow() - 1) { // lastPos before firstPos
                for(int i = lastPos.getRow() + 1; i < firstPos.getRow(); i++) { // Verify each case between those
                    if(getPiecePlayer(new Position(firstPos.getColumn(), i)).isPlayer()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else { // if there's no case between those
                return true;
            }
        } else {
            return false;
        }
    }

    /** Verify that there's no piece in the diagonal between two positions.
     * @param firstPos The first position.
     * @param lastPos The last position.
     * @return The result.
     */
    public boolean isBlankDiagonal(Position firstPos, Position lastPos) {
        if(isBottomLeft2TopRightDiagonal(firstPos, lastPos)) {
            if(firstPos.getColumn() < lastPos.getColumn() - 1) { // lastPos after firstPos
                for(int i = 1; firstPos.getColumn() + i < lastPos.getColumn(); i++) { // Verify each case between those
                    if(getPiecePlayer(new Position(firstPos.getColumn() + i, firstPos.getRow() - i)).isPlayer()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else if(lastPos.getColumn() < firstPos.getColumn() - 1) { // lastPos before firstPos
                for(int i = 1; lastPos.getColumn() + i < firstPos.getColumn(); i++) { // Verify each case between those
                    if(getPiecePlayer(new Position(lastPos.getColumn() + i, lastPos.getRow() - i)).isPlayer()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else { // if there's no case between those
                return true;
            }
        } else if(isTopLeft2BottomRightDiagonal(firstPos, lastPos)) {
            if(firstPos.getColumn() < lastPos.getColumn() - 1) { // lastPos after firstPos
                for(int i = 1; firstPos.getColumn() + i < lastPos.getColumn(); i++) { // Verify each case between those
                    if(getPiecePlayer(new Position(firstPos.getColumn() + i, firstPos.getRow() + i)).isPlayer()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else if(lastPos.getColumn() < firstPos.getColumn() - 1) { // lastPos before firstPos
                for(int i = 1; lastPos.getColumn() + i < firstPos.getColumn(); i++) { // Verify each case between those
                    if(getPiecePlayer(new Position(lastPos.getColumn() + i, lastPos.getRow() + i)).isPlayer()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else { // if there's no case between those
                return true;
            }
        } else { // if it isn't a diagonal
            return false;
        }
    }

    /** Verify that it makes a 'L' between two positions.
     * @param firstPos The first position.
     * @param lastPos The last position.
     * @return The result.
     */
    public boolean isL(Position firstPos, Position lastPos) {
        if(lastPos.getRow() == firstPos.getRow() + 2 || lastPos.getRow() == firstPos.getRow() - 2) { // the | of the 'L'
            return lastPos.getColumn() == firstPos.getColumn() + 1 || lastPos.getColumn() == firstPos.getColumn() - 1; // the _ of the 'L'
        } else if(lastPos.getRow() == firstPos.getRow() + 1 || lastPos.getRow() == firstPos.getRow() - 1) { // the ' of the 'L'
            return lastPos.getColumn() == firstPos.getColumn() + 2 || lastPos.getColumn() == firstPos.getColumn() - 2; // the __ of the 'L'
        } else { // if it isn't a 'L'
            return false;
        }
    }
}
