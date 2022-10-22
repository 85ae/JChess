package jchess.engine.players;

// Represents a player
public abstract class Player {
    protected String name;
    // The color (white, black or null)
    protected Color color;

    /* Default constructor
     * name: his name
     * color: his color
     */
    Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    // Get his name
    public String getName() {
        return name;
    }

    // Change his name
    public void changeName(String newName) {
        name = newName;
    }

    // Get his color
    public Color getColor() {
        return color;
    }

    // Return if he's null - override to say yes
    public boolean isNull() {
        return false;
    }
}
