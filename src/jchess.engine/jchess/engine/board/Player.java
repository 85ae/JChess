package jchess.engine.board;

/** A player.
 * Can be white, black or none (a null player).
 */
public enum Player {
    WHITE,
    BLACK,
    NONE;

    /** Returns if a player is null.
     * @return True if the player is equal to Player.NONE and false else.
     */
    public boolean isNull() {
        return this == Player.NONE;
    }

    /** Returns if a player is a correct player.
     * @return True if the player is different to Player.NONE and false else.
     */
    public boolean isPlayer() {
        return !isNull();
    }
}
