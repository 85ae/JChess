package build.buildscripts;

import java.io.PrintStream;
import java.nio.file.Files;
import java.io.File;
import java.io.FileReader;

public enum Log {
    TITLE,
    BIG,
    LITLE,
    ERROR,
    FATALERROR,
    WARNING,
    DEBUG,
    SAMPLE;

    /**
     * Prints an information.
     * @param level The log level.
     * @param info The text to display.
     */
    public static void log(Log level, String info) {
        PrintStream stream = System.out;
        String output = switch(level) {
            case TITLE -> "\u001B[01;04m"; // bold underlined
            case BIG -> "\n\u001B[01;35m==> \u001B[00;35m";
            case LITLE -> "\u001B[01;36m -> \u001B[00;36m";
            case ERROR, FATALERROR -> {
                stream = System.err;
                yield "\u001B[01;31mERROR: \u001B[00;31m";
            }
            case WARNING -> {
                stream = System.err;
                yield "\u001B[03;33mWarning: \u001B[00;33m";
            }
            case DEBUG -> "\u001B[02;32m[debug] \u001B[00;32m";
            default -> ""; // just an information
        };
        output += info;
        output += "\u001B[00m";
        stream.println(output);
        if(level == FATALERROR) {
            System.exit(1);
        }
    }

    /** Display an help message. */
    public static void help() {
        try {
            // String content = Files.readString(new File(Log.class.getResource("help.txt").toURI()).toPath());
            // FileReader file = new FileReader(new File(Log.class.getResource("help.txt").toURI()));
            System.out.println(Files.readString(new File(Log.class.getResource("help.txt").toURI()).toPath()));
            // file.close();
        } catch (Exception exception) {
            System.out.println("Jchess Build System Help.\nLook at README.md for more informations.");
        }
        System.exit(0);
    }
}
