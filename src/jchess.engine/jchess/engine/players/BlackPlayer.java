package jchess.engine.players;

// Black player
public class BlackPlayer extends Player {
    public BlackPlayer(String name) {
        super(name, Color.Black);
    }

    public BlackPlayer() {
        this("Black");
    }
}
