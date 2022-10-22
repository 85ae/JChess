// This package defines the pieces
package jchess.engine.pieces;

import jchess.engine.moves.Move;
import jchess.engine.players.*;

// This class is a generic piece.
public abstract class Piece {
	protected char symbol;
	protected Player player;

	// Create a null piece
	Piece() {
		this('.', new NullPlayer());
	}

	// Create a piece
	Piece(char symbol, Player player) {
		this.symbol = symbol;
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
}
