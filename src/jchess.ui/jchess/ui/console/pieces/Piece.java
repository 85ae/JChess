package jchess.ui.console.pieces;

public enum Piece {
    WHITEKING("king", "white"),
    WHITEQUEEN("queen", "white");

    private String piece;
    private String color;

    Piece(String piece, String color) {
        this.piece = piece;
        this.color = color;
    }

    /** It returns the piece name.
     * To use in properties for example.
     * @return The piece
     */
    public String getPiece() {
        return piece;
    }

    /** It returns a color string. <br>
     * For example, it can be "white" or "black".
     * 
     * @return the color
     */
    public String getColor() {
        return color;
    }
}
