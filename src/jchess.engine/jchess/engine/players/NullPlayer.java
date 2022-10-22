package jchess.engine.players;

// Null player, for a null piece
public class NullPlayer extends Player {
    public NullPlayer() {
        super("Null", Color.Null);
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
