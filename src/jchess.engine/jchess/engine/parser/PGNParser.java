package jchess.engine.parser;

import java.io.File;
import java.io.FileReader;
import jchess.engine.board.Board;

/** This is a PGN parser */
public class PGNParser implements Parser {
    String content;
    Board board;

    /** The constructor
     * @param board The board to parse.
     */
    public PGNParser(Board board) {
        this.board = board;
    }

    public Parser parse(String... input) {
        for(String i : input) {
            content += i;
        }
        return this;
    }

    /** Parses from (a) file(s)
     * @param input The file(s) to parse.
     * @return A parser object, 'this'.
     */
    public PGNParser parse(File... input) {
        for(File i : input) {
            try {
                FileReader inputFile = new FileReader(i);
                String inputString = "" + (char)inputFile.read();
                content += inputString;
                inputFile.close();
            } catch(Exception exception) {
                exception.getStackTrace();
            }
        }
        return this;
    }

    /** Parses from (a) file(s) with the parser 'parser'
     * @param parser The pgn parser used to parse.
     * @param input The file(s) to parse.
     * @return A parser object, 'parser'.
     */
    public static PGNParser parse(PGNParser parser, File... input) {
        return parser.parse(input);
    }

    /** Return the parsed board
     * @return The board.
     */
    public Object get() {
        return board;
    }
}
