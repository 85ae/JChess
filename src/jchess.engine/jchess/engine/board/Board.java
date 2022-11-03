package jchess.engine.board;

import java.util.Arrays;
import jchess.engine.moves.Move;
import jchess.engine.parser.MoveParser;
import jchess.engine.pieces.*;

// This class represent the chessboard
public class Board {
    private Piece[][] board;
    private Move[] history;
    private Player player;

    // Construct the board
    public Board() {
        board = new Piece[8][8];
        for(int i = 0; i < board.length; i++) {
            Piece[] row = board[i];
            for(int j = 0; j < row.length; j++) {
                row[j] = new NullPiece(new Position(j, i));
            }
        }
        history = new Move[0];
        player = Player.white;
    }

    // initialize the pieces
    public void initialize() {
        char[] row = { 'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r' };

        for(int i = 0; i < row.length; i++) {
            try {
                // first row
                setPiece(new Position(i, 0), (Piece)Piece.piece(row[i]).getDeclaredConstructor(Player.class, Position.class).newInstance(Player.white, new Position(i, 0))); // setCase(i0, new Piece.piece(white, i0))
                // last row
                setPiece(new Position(i, 7), (Piece)Piece.piece(row[i]).getDeclaredConstructor(Player.class, Position.class).newInstance(Player.black, new Position(i, 7))); // setCase(i7, new Piece.piece(black, i7))
            } catch(Exception exception) {
                exception.getStackTrace();
            }
            // row 2
            setPiece(new Position(i, 1), new Pawn(Player.white, new Position(i, 1)));
            // row 7
            setPiece(new Position(i, 6), new Pawn(Player.black, new Position(i, 6)));
        }
    }

    // Get the case situated at pos
    public Piece getPiece(Position pos) {
        return board[pos.getRow()][pos.getColumn()];
    }

    // Set the case situated at pos
    public void setPiece(Position pos, Piece piece) {
        board[pos.getRow()][pos.getColumn()] = piece;
        piece.setPos(pos);
    }

    /** Get the history
     * @return The move history.
     */
    public Move[] getHistory() {
        return history;
    }

    /** Get a move in the history
     * @param index The index where is situated the move.
     * @return The move situated at `index`. Return null if an error occured.
     */
    public Move getMove(int index) {
        return index < getHistory().length && index >= 0 ? getHistory()[index] : null;
    }

    /** Get the first move
     * @return The first move.
     */
    public Move getFirstMove() {
        return getMove(0);
    }

    /** Get the last move
     * @return The last move.
     */
    public Move getLastMove() {
        return getMove(getHistory().length - 1);
    }

    /** Modify a move
     * @param index The index of the move in the history.
     * @param value A move to replace the old move.
     */
    private void setMove(int index, Move value) {
        if(index < getHistory().length && index >= 0) {
            getHistory()[index] = value;
        }
    }

    /** Append a move
     * @param move The move to add
     */
    private void addMove(Move move) {
        history = Arrays.copyOf(history, history.length + 1);
        setMove(getHistory().length, move);
        Arrays.
    }

    /** Undo the last move */
    public void undo() {
        if(getHistory().length < 1) return;

        changePlayer(); // change the player to previous

        Move move = new Move(getLastMove().getNewPosition(), getLastMove().getOldPosition(), this);
        move(move);
        changePlayer(); // change the player to previous because it was changed

        Move[] tempHistory = new Move[getHistory().length - 2]; // -2 because a move was added
        for(int i = 0; i < tempHistory.length; i++) {
            tempHistory[i] = getMove(i);
        }
        history = tempHistory;
    }

    // Return the chessboard string
    @Override
    public String toString() {
        String output = "  a b c d e f g h\n\n";
        for(int i = 0; i < board.length; i++) {
            String line = "" + (i + 1) + " ";
            for(int j = 0; j < board.length; j++) {
                Position pos = new Position(j, i);

                line += (getPiece(pos).getPlayer() == Player.white ? /* to upper case */ getPiece(pos).toString().toUpperCase() : getPiece(pos));
                line += ' ';
            }
            output = line + '\n' + output;
        }

        return output;
    }

    /** Erase the entire board and the history */
    public void erase() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                setPiece(new Position(j, i), new NullPiece());
            }
        }
        history = new Move[0];
    }

    /** Move a piece
     * @param parser A move parser (jchess.engine.parser.MoveParser) already parsed.
     */
    public void move(MoveParser parser) {
        move((Move)parser.get());
    }

    /** Move a piece
     * @param oldPos The old position of the piece.
     * @param newPos The new position of the piece.
     */
    public void move(Position oldPos, Position newPos) {
        Move move = new Move(oldPos, newPos, this);
        move(move);
        
    }

    /** Move a piece
     * @param move The move to do.
     */
    public void move(Move move) {
        if(move.isCorrect() && move.getPiecePlayer() == player) {
            setPiece(move.getNewPosition(), move.getPiece());
            setPiece(move.getOldPosition(), new NullPiece());
            addMove(move);
            changePlayer();
        } else {
            System.err.println("ERROR: This piece can't move to this position.");
        }
    }

    /** Change the player to play */
    public void changePlayer() {
        if(player == Player.white) {
            player = Player.black;
        } else { // player == Player.black
            player = Player.white;
        }
    }

    /** Get the player who plays
     * @return The player who plays now.
     */
    public Player getPlayer() {
        return player;
    }

    /** Find the piece that can move on a case
     * @param type The type of the piece (for example: king, pawn...). Must be a symbol (k for king, n for knight, p for pawn...).
     * @param target The target position.
     * @param white Ask if it's a white piece or not. True if it's a white piece, false else.
     * @return The piece that can do this move. Null if there's no piece that can do it.
     */
    public Piece whoCanDoIt(char type, Position target, boolean white) {
        Position pos = new Position();

        do {
            do {
                if(getPiece(pos).getClass() == Piece.piece(type)) {
                    if(new Move(pos, target, this).isCorrect()) {
                        return getPiece(pos);
                    }
                }
                pos.incrementColumn();
            } while(pos.getColumn() < 7);
            pos.setColumn('a');
            pos.incrementRow();
        } while(pos.getRow() < 7);

        return null;
    }

    /** Find the piece that can move on a case
     * @param type The type of the piece (for example: king, pawn...). Must be a symbol (k for king, n for knight, p for pawn...).
     * @param target The target position.
     * @param white Ask if it's a white piece or not. True if it's a white piece, false else.
     * @param column The column where is situated the piece.
     * @return The piece that can do this move. Null if there's no piece that can do it.
     */
    public Piece whoCanDoIt(char type, Position target, boolean white, char column) {
        Position pos = new Position(column, 1);
        Piece piece = null;

        do {
            if(getPiece(pos).getClass() == Piece.piece(type)) {
                if(new Move(pos, target, this).isCorrect()) {
                    piece = getPiece(pos);
                    break;
                }
            }
            pos.incrementRow();
        } while(pos.getRow() < 7);

        return piece;
    }

    /** Find the piece that can move on a case
     * @param type The type of the piece (for example: king, pawn...). Must be a symbol (k for king, n for knight, p for pawn...).
     * @param target The target position.
     * @param white Ask if it's a white piece or not. True if it's a white piece, false else.
     * @param row The row where is situated the piece.
     * @return The piece that can do this move. Null if there's no piece that can do it.
     */
    public Piece whoCanDoIt(char type, Position target, boolean white, int row) {
        Position pos = new Position('a', row);
        Piece piece = null;

        do {
            if(getPiece(pos).getClass() == Piece.piece(type)) {
                if(new Move(pos, target, this).isCorrect()) {
                    piece = getPiece(pos);
                    break;
                }
            }
            pos.incrementColumn();
        } while(pos.getColumn() < 7);

        return piece;
    }
}
