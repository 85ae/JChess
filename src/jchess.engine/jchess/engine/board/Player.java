package jchess.engine.board;

/** A player.
 * Can be white, black or none (a null player).
 */
public enum Player {
    /** A white player */
    WHITE,
    /** A black player */
    BLACK,
    /** A null player */
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

    /** Check if the other player is the opposite.<br/>
     * If this player or the other is null or "none" it will return false.
     * @param other the other player.
     * @return true if it's the opposite player.
     */
    public boolean isOpposite(Player other) {
        if(this.isNull() || other == null || other.isNull() || other.equals(this)) {
            return false;
        }
        return true;
    }
}
