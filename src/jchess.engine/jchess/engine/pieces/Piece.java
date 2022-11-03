// This package defines the pieces
package jchess.engine.pieces;

import jchess.engine.board.Position;
import jchess.engine.board.Player;
import jchess.engine.moves.Move;

// This class is a generic piece.
public abstract class Piece {
	protected char symbol;
	protected Player player;
	protected Position position;

	/** Default Constructor */
	Piece() {
		this(new Position());
	}

	// Create a null piece
	Piece(Position position) {
		this('.', Player.none, position);
	}

	// Create a piece
	Piece(char symbol, Player player, Position position) {
		this.symbol = symbol;
		this.position = position;
		setPlayer(player);
	}

	// Rules to move a piece
	public abstract boolean canMove(Move move);

	// Verify if is null - override to say yes
	public boolean isNull() {
		return false;
	}

	// Get the symbol
	public char getSymbol() {
		return symbol;
	}

	// Get the player
	public Player getPlayer() {
		return player;
	}

	// Set the player
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

	/** Return a piece
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
