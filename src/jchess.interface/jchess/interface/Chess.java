// Main program

import config.ConfigFile;
import players.*;
import java.io.File;
import board.*;

// The main class
class Chess {
	// The main void
	public static void main(String[] args) {
		ConfigFile config = new ConfigFile(System.getProperty("user.home") + "/.chess/chess.conf");
		new ConfigFile(System.getProperty("user.home") + "/.chess/tmp_lang.conf").setProprety("lang", config.getProperty("interface.lang"));

		Board board = new Board(new WhitePlayer(), new BlackPlayer());

		board.initialize();

		board.move(new Position('e', 2), new Position('e', 4));
		board.move(new Position('e', 7), new Position('e', 5));
		board.move(new Position('b', 1), new Position('c', 3));
		board.move(new Position('a', 1), new Position('b', 1));
		board.move(new Position('f', 1), new Position('c', 4));
		board.move(new Position('d', 1), new Position('e', 2));
		board.move(new Position('e', 1), new Position('d', 1));

		board.show(System.out);

		new File(System.getProperty("user.home") + "/.chess/tmp_lang.conf").delete();
	}
}
