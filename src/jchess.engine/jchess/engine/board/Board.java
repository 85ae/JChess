/** Contains chess board classes.
 * Such as board, player and position.
 */
package jchess.engine.board;

import java.util.Arrays;
import jchess.engine.moves.Move;
import jchess.engine.moves.Take;
import jchess.engine.parser.MoveParser;
import jchess.engine.pieces.*;

/**
 * This class represent a chessboard.
 * It provides a lot of methods to manipulate a chess board.
 */
public class Board {
    private Piece[][] board;
    private Move[] history;
    private Player player;

    /**
     * Default constructor.
     * It creates a blank board (8/8/8/8/8/8/8/8) with white to play and a blank history.
     * There's no other constructor.
     */
    public Board() {
        board = new Piece[8][8];
        for(int i = 0; i < board.length; i++) {
            Piece[] row = board[i];
            for(int j = 0; j < row.length; j++) {
                row[j] = new NullPiece(new Position(j, i));
            }
        }
        history = new Move[0];
        player = Player.WHITE;
    }

    /**
     * Initializes the board.
     * This method initializes the board with a pawn line at the second and the seventh lines.
     * The first and the last lines are initialized with the line given as argument.
     * It can be used for random chess (Fischer's chess).
     * @param row The first and last row, generally {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'}.
     */
    public void initialize(char[] row) {
        for(int i = 0; i < row.length && i < board.length; i++) {
            try {
                // first row
                setPiece(new Position(i, 0), (Piece)Piece.piece(row[i]).getDeclaredConstructor(Player.class, Position.class).newInstance(Player.WHITE, new Position(i, 0))); // setCase(i0, new Piece.piece(white, i0))
                // last row
                setPiece(new Position(i, 7), (Piece)Piece.piece(row[i]).getDeclaredConstructor(Player.class, Position.class).newInstance(Player.BLACK, new Position(i, 7))); // setCase(i7, new Piece.piece(black, i7))
            } catch(Exception exception) {
                exception.getStackTrace();
            }
            // row 2
            setPiece(new Position(i, 1), new Pawn(Player.WHITE, new Position(i, 1)));
            // row 7
            setPiece(new Position(i, 6), new Pawn(Player.BLACK, new Position(i, 6)));
        }
    }

    /**
     * Initializes the board.
     * This method initializes the chess board with the default layout (rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR).
     */
    public void initialize() {
        initialize(new char[]{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'});
    }

    /**
     * Returns the piece on the board.
     * @param pos The piece position.
     * @return A reference to this piece.
     */
    public Piece getPiece(Position pos) {
        return board[pos.row()][pos.column()];
    }

    /**
     * Set the piece on the board.
     * @param pos The piece position.
     * @param piece A reference to the piece which will be assigned to this position.
     */
    public void setPiece(Position pos, Piece piece) {
        board[pos.row()][pos.column()] = piece;
        piece.setPos(pos);
    }

    /**
     * Get the history.
     * @return The move history.
     */
    public Move[] getHistory() {
        return history;
    }

    /**
     * Get a move in the history.
     * @param index The index where is situated the move.
     * @return The move situated at `index`. Return null if an error occured.
     */
    public Move getMove(int index) {
        return index < getHistory().length && index >= 0 ? getHistory()[index] : null;
    }

    /**
     * Get the first move.
     * @return The first move.
     */
    public Move getFirstMove() {
        return getMove(0);
    }

    /**
     * Get the last move.
     * @return The last move.
     */
    public Move getLastMove() {
        return getMove(getHistory().length - 1);
    }

    /**
     * Modify a move.
     * @param index The index of the move in the history.
     * @param value A move to replace the old move.
     */
    private void setMove(int index, Move value) {
        if(index < getHistory().length && index >= 0) {
            getHistory()[index] = value;
        }
    }

    /**
     * Append a move.
     * @param move The move to add.
     */
    private void addMove(Move move) {
        history = Arrays.copyOf(history, history.length + 1);
        setMove(getHistory().length - 1, move);
    }

    /**
     * Undo the last move.
     * If the history is empty, it doesn't do anything.
     * Else, it undos the last move and resizes the history.
     */
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

    /**
     * Returns the chess board string.<br/>
     * It returns a string like this :<br/>
     * 8 r n b q k b n r<br/>
     * 7 p p p p . p p p<br/>
     * 6 . . . . . . . .<br/>
     * 5 . . . . p . . .<br/>
     * 4 . . . . P . . .<br/>
     * 3 . . . . . N . .<br/>
     * 2 P P P P . P P P<br/>
     * 1 R N B Q K B . R<br/>
     *   a b c d e f g h
     * @return A representation of this chess board.
     */
    @Override
    public String toString() {
        String output = "  a b c d e f g h\n\n";
        for(int i = 0; i < board.length; i++) {
            String line = "" + (i + 1) + " ";
            for(int j = 0; j < board.length; j++) {
                Position pos = new Position(j, i);

                line += (getPiece(pos).getPlayer() == Player.WHITE ? /* to upper case */ getPiece(pos).toString().toUpperCase() : getPiece(pos));
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

    /** Move a piece.
     * @param parser A move parser (jchess.engine.parser.MoveParser) already parsed.
     */
    public void move(MoveParser parser) {
        move(parser.get());
    }

    /** Move a piece.
     * @param oldPos The old position of the piece.
     * @param newPos The new position of the piece.
     */
    public void move(Position oldPos, Position newPos) {
        Move move = new Move(oldPos, newPos, this);
        move(move);
        
    }

    /** Move a piece.
     * @param move The move to do.
     */
    public void move(Move move) {
        System.out.println(move);
        if(move.isCorrect()) {
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
        if(player == Player.WHITE) {
            player = Player.BLACK;
        } else { // player == Player.BLACK
            player = Player.WHITE;
        }
    }

    /** Get the player who plays
     * @return The player who plays now.
     */
    public Player getPlayer() {
        return player;
    }

    /** Finds the piece that can move on a case.
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
                pos = Position.incrementColumn(pos);
            } while(pos.column() < 7);
            pos = new Position(pos.row(), 0 /* a */);
            pos = Position.incrementRow(pos);
        } while(pos.row() < 7);

        return null;
    }

    /** Finds the piece that can take another piece.
     * @param type The type of the piece (for example: king, pawn...). Must be a symbol (k for king, n for knight, p for pawn...).
     * @param target The target position.
     * @param white Ask if it's a white piece or not. True if it's a white piece, false else.
     * @return The piece that can do this move. Null if there's no piece that can do it.
     */
    public Piece whoCanTakeIt(char type, Position target, boolean white) {
        Position pos = new Position();

        do {
            do {
                if(getPiece(pos).getClass() == Piece.piece(type)) {
                    if(new Take(pos, target, this).isCorrect()) {
                        return getPiece(pos);
                    }
                }
                pos = Position.incrementColumn(pos);
            } while(pos.column() < 7);
            pos = new Position(pos.row(), 0 /* a */);
            pos = Position.incrementRow(pos);
        } while(pos.row() < 7);

        return null;
    }

    /** Finds the piece that can move on a case.
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
            pos = Position.incrementRow(pos);
        } while(pos.row() < 7);

        return piece;
    }

    /** Finds the piece that can take another piece.
     * @param type The type of the piece (for example: king, pawn...). Must be a symbol (k for king, n for knight, p for pawn...).
     * @param target The target position.
     * @param white Ask if it's a white piece or not. True if it's a white piece, false else.
     * @param column The column where is situated the piece.
     * @return The piece that can do this move. Null if there's no piece that can do it.
     */
    public Piece whoCanTakeIt(char type, Position target, boolean white, char column) {
        Position pos = new Position(column, 1);
        Piece piece = null;

        do {
            if(getPiece(pos).getClass() == Piece.piece(type)) {
                if(new Take(pos, target, this).isCorrect()) {
                    piece = getPiece(pos);
                    break;
                }
            }
            pos = Position.incrementRow(pos);
        } while(pos.row() < 7);

        return piece;
    }

    /** Finds the piece that can move on a case.
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
            pos = Position.incrementColumn(pos);
        } while(pos.column() < 7);

        return piece;
    }

    /** Finds the piece that can take another piece.
     * @param type The type of the piece (for example: king, pawn...). Must be a symbol (k for king, n for knight, p for pawn...).
     * @param target The target position.
     * @param white Ask if it's a white piece or not. True if it's a white piece, false else.
     * @param row The row where is situated the piece.
     * @return The piece that can do this move. Null if there's no piece that can do it.
     */
    public Piece whoCanTakeIt(char type, Position target, boolean white, int row) {
        Position pos = new Position('a', row);
        Piece piece = null;

        do {
            if(getPiece(pos).getClass() == Piece.piece(type)) {
                if(new Take(pos, target, this).isCorrect()) {
                    piece = getPiece(pos);
                    break;
                }
            }
            pos = Position.incrementColumn(pos);
        } while(pos.column() < 7);

        return piece;
    }
}
