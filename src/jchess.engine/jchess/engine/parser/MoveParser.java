package jchess.engine.parser;

import jchess.engine.board.Board;
import jchess.engine.board.Position;
import jchess.engine.moves.Move;
import jchess.engine.pieces.Piece;

/** A class to parse a move.
 * You can use it to move a piece from a player input.
 */
public class MoveParser implements Parser {
    private String content;
    private Move move;
    private boolean isWhitePlayer;
    private Board board;

    /** The constructor.
     * @param board The chessboard.
     * @param white True if the owner of the piece is white, false else.
     */
    public MoveParser(Board board, boolean white) {
        move = null;
        this.board = board;
        isWhitePlayer = white;
    }

    @Override
    public Parser parse(String... input) {
        content = "";
        for(String i : input) {
            content += i;
        }

        boolean capture;
        char oldColumn = '\u0000';
        int oldRow = 0;
        char newColumn = '\u0000';
        int newRow = 0;
        char piece = 'P';
        Piece pieceToMove = null;

        for(int i = 0; i < content.length(); i++) {
            char x = content.charAt(i);
            switch(x) {
                case 'K', 'Q', 'B', 'N', 'R' -> piece = x;

                case 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' -> {
                    if(newColumn != '\u0000') {
                        oldColumn = newColumn;
                    }
                    newColumn = x;
                }

                case '1', '2', '3', '4', '5', '6', '7', '8' -> {
                    if(newRow != 0) {
                        oldRow = newRow;
                    }
                    newRow = (int)x - ('1' - 1);
                }

                case 'x' -> capture = true;

                default -> System.err.println("'" + x + "' isn't a valid character in a move definition");
            }
        }

        Position target = new Position(newColumn, newRow);

        if(oldColumn != '\u0000') {
            if(oldRow != 0) {
                pieceToMove = board.getPiece(new Position(newColumn, newRow));
            } else {
                pieceToMove = board.whoCanDoIt(piece, target, isWhitePlayer, newColumn);
            }
        } else if(oldRow != 0) {
            pieceToMove = board.whoCanDoIt(piece, target, isWhitePlayer, newRow);
        } else {
            pieceToMove = board.whoCanDoIt(piece, target, isWhitePlayer);
        }

        move = new Move(pieceToMove.getPosition(), target, board);

        return this;
    }

    /** Return the parsed move
     * @return The move.
     */
    @Override
    public Object get() {
        return move;
    }
}