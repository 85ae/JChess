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
        return firstPos.row() == lastPos.row();
    }

    /** Verify if it's a vertical line between two positions.
     * @param firstPos The first position.
     * @param lastPos The last position.
     * @return The result.
     */
    public boolean isVerticalLine(Position firstPos, Position lastPos) {
        return firstPos.column() == lastPos.column();
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
        return firstPos.row() - firstPos.column() == lastPos.row() - lastPos.column(); // Example: a2 and c4. a = 1, c = 3, 2 - 1 = 1, 4 - 3 = 1
    }

    /** Verify that it's a diagonal from top - left to bottom - right (or opposite) between two positions.
     * @param firstPos The first position.
     * @param lastPos The last position.
     * @return The result.
     */
    public boolean isTopLeft2BottomRightDiagonal(Position firstPos, Position lastPos) {
        return firstPos.row() + firstPos.column() == lastPos.row() + lastPos.column(); // Example: c2 and a4. c = 3, a = 1, 3 + 2 = 5, 1 + 4 = 5
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
            if(firstPos.column() < lastPos.column() - 1) { // lastPos after firstPos
                for(int i = firstPos.column() + 1; i < lastPos.column(); i++) { // Verify each case between those
                    if(getPiecePlayer(new Position(i, firstPos.row())).isPlayer()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else if(lastPos.column() < firstPos.column() - 1) { // lastPos before firstPos
                for(int i = lastPos.column() + 1; i < firstPos.column(); i++) { // Verify each case between those
                    if(getPiecePlayer(new Position(i, firstPos.row())).isPlayer()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else { // if there's no case between those
                return true;
            }
        } else if(isVerticalLine(firstPos, lastPos)) { // if vertical
            if(firstPos.row() < lastPos.row() - 1) { // lastPos after firstPos
                for(int i = firstPos.row() + 1; i < lastPos.row(); i++) { // Verify each case between those
                    if(getPiecePlayer(new Position(firstPos.column(),i)).isPlayer()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else if(lastPos.row() < firstPos.row() - 1) { // lastPos before firstPos
                for(int i = lastPos.row() + 1; i < firstPos.row(); i++) { // Verify each case between those
                    if(getPiecePlayer(new Position(firstPos.row(), i)).isPlayer()) { // if there's a piece
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
            if(firstPos.column() < lastPos.column() - 1) { // lastPos after firstPos
                for(int i = 1; firstPos.column() + i < lastPos.column(); i++) { // Verify each case between those
                    if(getPiecePlayer(new Position(firstPos.column() + i, firstPos.row() - i)).isPlayer()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else if(lastPos.column() < firstPos.column() - 1) { // lastPos before firstPos
                for(int i = 1; lastPos.column() + i < firstPos.column(); i++) { // Verify each case between those
                    if(getPiecePlayer(new Position(lastPos.column() + i, lastPos.row() - i)).isPlayer()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else { // if there's no case between those
                return true;
            }
        } else if(isTopLeft2BottomRightDiagonal(firstPos, lastPos)) {
            if(firstPos.column() < lastPos.column() - 1) { // lastPos after firstPos
                for(int i = 1; firstPos.column() + i < lastPos.column(); i++) { // Verify each case between those
                    if(getPiecePlayer(new Position(firstPos.column() + i, firstPos.row() + i)).isPlayer()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else if(lastPos.column() < firstPos.column() - 1) { // lastPos before firstPos
                for(int i = 1; lastPos.column() + i < firstPos.column(); i++) { // Verify each case between those
                    if(getPiecePlayer(new Position(lastPos.column() + i, lastPos.row() + i)).isPlayer()) { // if there's a piece
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
        if(lastPos.row() == firstPos.row() + 2 || lastPos.row() == firstPos.row() - 2) { // the | of the 'L'
            return lastPos.column() == firstPos.column() + 1 || lastPos.column() == firstPos.column() - 1; // the _ of the 'L'
        } else if(lastPos.row() == firstPos.row() + 1 || lastPos.row() == firstPos.row() - 1) { // the ' of the 'L'
            return lastPos.column() == firstPos.column() + 2 || lastPos.column() == firstPos.column() - 2; // the __ of the 'L'
        } else { // if it isn't a 'L'
            return false;
        }
    }
}
