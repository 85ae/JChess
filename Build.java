import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

import javax.tools.Tool;
import javax.tools.ToolProvider;

/** The build class
 * Type `java Build.java [OPTIONS]` to build the program.
 * To see more help about the usage, type `java Build.java --help`.
 */
class Build {
    private Build(String[] args) {
        Tool javac = ToolProvider.getSystemJavaCompiler();
        
        // list the build system source files
        File[] sourceFiles = 
        new File("build/scripts")
            .listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".java");
                }
            });
        
        // transform to String[]
        String[] sourceFilesStrings = new String[sourceFiles.length];
        for (int i = 0; i < sourceFilesStrings.length; i++) {
            sourceFilesStrings[i] = sourceFiles[i].getAbsolutePath();
        }

        // copy source files to compile arguments and add some extra arguments
        String[] compileArguments = Arrays.copyOf(sourceFilesStrings, sourceFiles.length + 2);
        compileArguments[compileArguments.length - 1] = ".";
        compileArguments[compileArguments.length - 2] = "-d";

        // compile
        if(javac.run(null, null, null, compileArguments) != 0) {
            System.err.println("ERROR: Build system can not be compiled.");
            System.exit(1);
        }
        try {
            // copy a ressource file
            Files.copy(new File("build/scripts/help.txt").toPath(), new File("build/buildscripts/help.txt").toPath(), StandardCopyOption.REPLACE_EXISTING);
            // load a class
            this.getClass().getClassLoader().loadClass("build.buildscripts.Builder").getConstructor(String[].class).newInstance((Object)args);
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        File buildSystemDir = new File("build/buildscripts");
        for (File file : buildSystemDir.listFiles()) {
            file.delete();
        }
        buildSystemDir.delete();
    }

    /** Main method */
    public static void main(String[] args) {
        new Build(args);
    }
}
