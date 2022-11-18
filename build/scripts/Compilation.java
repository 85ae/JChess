package build.buildscripts;

import javax.tools.Tool;
import javax.tools.ToolProvider;

/** This class provides some compilation tools. */
public class Compilation {
    /** Call the javac command.
     * @param args The arguments to pass.
     * @return True if the compilation passed, false if an error occured.
     */
    public static boolean compile(String... args) {
        Tool javac = ToolProvider.getSystemJavaCompiler();
        return javac.run(null, null, null, args) == 0;
    }
}
