package jchess.ui.pieces;

import jchess.ui.locale.Locale;

/**
 * This enum represents a player.<br/>
 * It can be a {@link #WHITE white}, {@link #BLACK black} or {@link #NONE null} player for a non-existent player.
 * It contains some methods to give a string representation.
 */
public enum Player {
    /** A white player */
    WHITE,
    /** A black player */
    BLACK,
    /**
     * A null player.<br/>
     * It's used to represent a blank case for example.
     */
    NONE;

    @Override
    public String toString() {
        return switch(this) {
            case WHITE -> "White";
            case BLACK -> "Black";
            default -> "None";
        };
    }

    /**
     * This method give a printable string.
     * @param locale the locale used to get them strings.
     * @return the string to print.
     */
    public String toString(Locale locale) {
        return locale.get(switch(this) {
            case WHITE -> "jchess.player.white";
            case BLACK -> "jchess.player.black";
            default -> "jchess.player.none";
        });
    }
}
