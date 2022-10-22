package jchess.engine.players;

// White player
public class WhitePlayer extends Player {
    public WhitePlayer(String name) {
        super(name, Color.White);
    }

    public WhitePlayer() {
        this("White");
    }
}
