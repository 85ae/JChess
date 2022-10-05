package moves;

import board.Board;
import board.Position;
import pieces.Piece;
import players.Player;

// Represents a move
public class Move {
    protected Position oldPos, newPos;
    protected Piece piece;
    protected Board board;

    /* Constructor
     * piece: the piece that moves
     * oldPos: the old position
     * newPos: the new position
     * board: the board of the piece
     */
    public Move(Piece piece, Position oldPos, Position newPos, Board board) {
        this.piece = piece;
        this.oldPos = oldPos;
        this.newPos = newPos;
        this.board = board;
    }

    // Verify if it's a correct move - override to take a piece
    public boolean isCorrect() {
        if(!oldPos.equals(newPos) && getPiecePlayer(newPos).isNull()) {
            return  piece.canMove(this);
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

    // Get the owner of the piece situated at the position pos
    public Player getPiecePlayer(Position pos) {
        return board.getCase(pos).getPiecePlayer();
    }

    // Return the vertical distance of move
    public int verticalDistance() {
        return newPos.getRow() - oldPos.getRow();
    }

    // Return the horizontal distance of move
    public int horizontalDistance() {
        return newPos.getColumn() - oldPos.getColumn();
    }


    // Verify if it's an horizontal line between firstPos and lastPos
    public boolean isHorizontalLine(Position firstPos, Position lastPos) {
        return firstPos.getRow() == lastPos.getRow();
    }

    // Verify if it's an horizontal line
    public boolean isHorizontalLine() {
        return isHorizontalLine(oldPos, newPos);
    }

    // Verify if it's a vertical line between firstPos and lastPos
    public boolean isVerticalLine(Position firstPos, Position lastPos) {
        return firstPos.getColumn() == lastPos.getColumn();
    }

    // Verify if it's a vertical line
    public boolean isVerticalLine() {
        return isVerticalLine(oldPos, newPos);
    }

    // Verify if it's a line between firstPos and lastPos
    public boolean isLine(Position firstPos, Position lastPos) {
        return isHorizontalLine(firstPos, lastPos) || isVerticalLine(firstPos, lastPos);
    }

    // Verify if it's a line
    public boolean isLine() {
        return isLine(oldPos, newPos);
    }

    // Verify that it's a diagonal from bottom - left to top - right (or opposite) between firstPos and lastPos
    public boolean isBottomLeft2TopRightDiagonal(Position firstPos, Position lastPos) {
        return firstPos.getRow() - firstPos.getColumn() == lastPos.getRow() - lastPos.getColumn(); // Example: a2 and c4. a = 1, c = 3, 2 - 1 = 1, 4 - 3 = 1
    }

    // Verify that it's a diagonal from bottom - left to top - right (or opposite)
    public boolean isBottomLeft2TopRightDiagonal() {
        return isBottomLeft2TopRightDiagonal(oldPos, newPos);
    }

    // Verify that it's a diagonal from top - left to bottom - right (or opposite) between firstPos and lastPos
    public boolean isTopLeft2BottomRightDiagonal(Position firstPos, Position lastPos) {
        return firstPos.getRow() + firstPos.getColumn() == lastPos.getRow() + lastPos.getColumn(); // Example: c2 and a4. c = 3, a = 1, 3 + 2 = 5, 1 + 4 = 5
    }

    // Verify that it's a diagonal from top - left to bottom - right (or opposite)
    public boolean isTopLeft2BottomRightDiagonal() {
        return isTopLeft2BottomRightDiagonal(oldPos, newPos);
    }

    // Verify that it's a diagonal between firstPos and lastPos
    public boolean isDiagonal(Position firstPos, Position lastPos) {
        return isBottomLeft2TopRightDiagonal(firstPos, lastPos) || isTopLeft2BottomRightDiagonal(firstPos, lastPos);
    }

    // Verify that it's a diagonal
    public boolean isDiagonal() {
        return isDiagonal(oldPos, newPos);
    }

    // Verify that there's no piece in the line between firstPos and lastPos
    public boolean isBlankLine(Position firstPos, Position lastPos) {
        if(isHorizontalLine(firstPos, lastPos)) { // if horizontal
            if(firstPos.getColumn() < lastPos.getColumn() - 1) { // lastPos after firstPos
                for(int i = firstPos.getColumn() + 1; i < lastPos.getColumn(); i++) { // Verify each case between those
                    if(!getPiecePlayer(new Position(i, firstPos.getRow())).isNull()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else if(lastPos.getColumn() < firstPos.getColumn() - 1) { // lastPos before firstPos
                for(int i = lastPos.getColumn() + 1; i < firstPos.getColumn(); i++) { // Verify each case between those
                    if(!getPiecePlayer(new Position(i, firstPos.getRow())).isNull()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else { // if there's no case between those
                return true;
            }
        } else if(isVerticalLine()) { // if vertical
            if(firstPos.getRow() < lastPos.getRow() - 1) { // lastPos after firstPos
                for(int i = firstPos.getRow() + 1; i < lastPos.getRow(); i++) { // Verify each case between those
                    if(!getPiecePlayer(new Position(firstPos.getColumn(),i)).isNull()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else if(lastPos.getRow() < firstPos.getRow() - 1) { // lastPos before firstPos
                for(int i = lastPos.getRow() + 1; i < firstPos.getRow(); i++) { // Verify each case between those
                    if(!getPiecePlayer(new Position(firstPos.getColumn(), i)).isNull()) { // if there's a piece
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

    // Verify that there's no piece between oldPos and newPos
    public boolean isBlankLine() {
        return isBlankLine(oldPos, newPos);
    }

    // Verify that there's no piece in the diagonal between firstPos and lastPos
    public boolean isBlankDiagonal(Position firstPos, Position lastPos) {
        if(isBottomLeft2TopRightDiagonal(firstPos, lastPos)) {
            if(firstPos.getColumn() < lastPos.getColumn() - 1) { // lastPos after firstPos
                for(int i = 1; firstPos.getColumn() + i < lastPos.getColumn(); i++) { // Verify each case between those
                    if(!getPiecePlayer(new Position(firstPos.getColumn() + i, firstPos.getRow() - i)).isNull()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else if(lastPos.getColumn() < firstPos.getColumn() - 1) { // lastPos before firstPos
                for(int i = 1; lastPos.getColumn() + i < firstPos.getColumn(); i++) { // Verify each case between those
                    if(!getPiecePlayer(new Position(lastPos.getColumn() + i, lastPos.getRow() - i)).isNull()) { // if there's a piece
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
                    if(!getPiecePlayer(new Position(firstPos.getColumn() + i, firstPos.getRow() + i)).isNull()) { // if there's a piece
                        return false;
                    }
                }
                return true;
            } else if(lastPos.getColumn() < firstPos.getColumn() - 1) { // lastPos before firstPos
                for(int i = 1; lastPos.getColumn() + i < firstPos.getColumn(); i++) { // Verify each case between those
                    if(!getPiecePlayer(new Position(lastPos.getColumn() + i, lastPos.getRow() + i)).isNull()) { // if there's a piece
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

    // Verify that there's no piece in the diagonal between ldPos and newPos
    public boolean isBlankDiagonal() {
        return isBlankDiagonal(oldPos, newPos);
    }

    // Verify that it makes a 'L' between firstPos and lastPos
    public boolean isL(Position firstPos, Position lastPos) {
        if(lastPos.getRow() == firstPos.getRow() + 2 || lastPos.getRow() == firstPos.getRow() - 2) { // the | of the 'L'
            return lastPos.getColumn() == firstPos.getColumn() + 1 || lastPos.getColumn() == firstPos.getColumn() - 1; // the _ of the 'L'
        } else if(lastPos.getRow() == firstPos.getRow() + 1 || lastPos.getRow() == firstPos.getRow() - 1) { // the ' of the 'L'
            return lastPos.getColumn() == firstPos.getColumn() + 2 || lastPos.getColumn() == firstPos.getColumn() - 2; // the __ of the 'L'
        } else { // if it isn't a 'L'
            return false;
        }
    }

    // Verify that it makes a 'L'
    public boolean isL() {
        return isL(oldPos, newPos);
    }

    // Verify if the piece is on the x row
    public boolean isOnRow(int x) {
        return oldPos.getRow() == x - 1;
    }

    // Verify if the piece is on the x column
    public boolean isOnColumn(int x) {
        return oldPos.getColumn() == x + 1;
    }
}
