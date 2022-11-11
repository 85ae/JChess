/// This package defines the pieces.
package jchess.engine.pieces;

import jchess.engine.board.Position;
import jchess.engine.board.Player;
import jchess.engine.moves.Move;

/** This class is a generic piece.
 * You can't create a instance of this class, but all the methods defined here can be used in all the piece classes.
 */
public abstract class Piece {
	protected char symbol;
	protected Player player;
	protected Position position;

	/** Default Constructor.
	 * Creates a null piece (blank case) at a1.
	 */
	Piece() {
		this(new Position());
	}

	/** Creates a null piece (blank case).
	 * @param position The case position.
	 */
	Piece(Position position) {
		this('.', Player.NONE, position);
	}

	/** Creates a piece.
	 * @param symbol The piece symbol (k, n, p...).
	 * @param player The owner (white, black or none).
	 * @param position The position of the new piece.
	 */
	Piece(char symbol, Player player, Position position) {
		this.symbol = symbol;
		this.position = position;
		setPlayer(player);
	}

	/** Verify the validity of a move.
	 * @param move The move to verify.
	 * @return True if this move is correct, false else.
	 */
	public abstract boolean canMove(Move move);

	/** Verify if is null.
	 * Override if it is.
	 * @return True if it's a null piece (blank case), false else.
	 */
	public boolean isNull() {
		return false;
	}

	/** Get the piece symbol.
	 * @return The symbol of the piece.
	 */
	public char getSymbol() {
		return symbol;
	}

	/** Get the player.
	 * @return The player who has got this piece.
	 */
	public Player getPlayer() {
		return player;
	}

	/** Set the player.
	 * @param player The new owner of this piece.
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/** Get the position
	 * @return The position of the piece.
	 */
	public Position getPosition() {
		return position;
	}

	/** Set the position of the piece
	 * @param pos The new position.
	 */
	public void setPos(Position pos) {
		position = pos;
	}

	/** Return a piece.
	 * @param symbol The piece symbol.
	 * @return A piece class.
	 */
	public static Class<?> piece(char symbol) {
		switch(symbol) {
			case 'K':
			case 'k':
				return King.class;

			case 'Q':
			case 'q':
				return Queen.class;

			case 'B':
			case 'b':
				return Bishop.class;

			case 'N':
			case 'n':
				return Knight.class;

			case 'R':
			case 'r':
				return Rook.class;

			case 'P':
			case 'p':
				return Pawn.class;

			default:
				return NullPiece.class;
		}
	}

	/** Return a printable string
	 * @return A string that can be printed.
	 */
	@Override
	public String toString() {
		return String.valueOf(getSymbol());
	}
}
