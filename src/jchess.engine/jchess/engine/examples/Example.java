package jchess.engine.examples;

import jchess.engine.Chess;

/**
 * A sample program.
 * Just to show what you can do and how.
 */
public class Example {
    /**
     * The main method.
     * @param args the arguments passed to the program.
     */
    public static void main(String[] args) {
        // Create a Chess object.
        Chess chess = new Chess();

        // Print the chess game.
        System.out.println(chess);

        // Make some moves.
        chess.move("e4");
        System.out.println("Now it's " + /* Get the player's name ('b' or 'w'). */ chess.getPlayer() + " to play.\n");
        chess.move("e5", "Nf3");

        System.out.println(chess);

        // Undo a move.
        chess.undo();
        System.out.println("Now it's " + /* Get the player's name ('b' or 'w'). */ chess.getPlayer() + " to play.");

        System.out.println(chess);

        chess.move("f4", "xf4");

        System.out.println(chess);
    }
}
