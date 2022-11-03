package jchess.engine.parser;

/** This interface is used to define a parser
 * It's used, for example, to create a PGN parser.
 * @author 85ae
 * @version 0.0.1
 */
public interface Parser {
    /** Parses (a) string(s)
     * @param input The string(s) to parse.
     * @return A parser object, normally 'this'.
     */
    public Parser parse(String... input);

    /** Parses (a) string(s) with the parser 'parser'
     * @param parser The parser used to parse.
     * @param input The string(s) to parse.
     * @return A parser object, normally 'parser'.
     */
    public static Parser parse(Parser parser, String... input) {
        return parser.parse(input);
    }

    /** Get the parsed object
     * @return The parsed object
     */
    public Object get();
}
