// This package defines the pieces
package pieces;

import config.Lang;
import moves.Move;
import players.*;

// This class is a generic piece.
public abstract class Piece {
	char symbol;
	String name;
	Player player;

	// Create a null piece
	Piece() {
		name = "Null";
		symbol = ' ';
		player = new NullPlayer();
	}

	// Create a piece
	Piece(String key, Player player) {
		Lang lang = new Lang();
		symbol = lang.getChar("pieces." + key + ".symbol");
		name = lang.getText("pieces." + key + ".name");
		setPlayer(player);
	}

	// Rules to move a piece
	public abstract boolean canMove(Move move);

	// Verify if is null - override to say yes
	public boolean isNull() {
		return false;
	}

	// Get the name
	public String getName() {
		return name;
	}

	// Get the symbol to print
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
