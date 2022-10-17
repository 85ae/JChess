import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.tools.Tool;
import javax.tools.ToolProvider;

/* The build class
 * Type `java Build.java [OPTIONS]` to build the program.
 */
class Build {
    private String prefix, os, build_dir, src_dir, slash;
    private boolean jar, test, debug;

    // Create the build / install / test / clean files
    Build(String[] args) {
        // defines the file separator
        slash = System.getProperty("file.separator");

        // default properties
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("nix") || os.contains("nux") || os.contains("aix") || os.contains("sunos")) {
            this.os = "unix";
        } else if(os.contains("mac")) {
            this.os = "macos";
        } else if(os.contains("win")) {
            this.os = "windows";
        } else {
            this.os = os;
        }
        this.build_dir = "." + slash + "build";
        this.src_dir = "." + slash + "src";
        this.debug = false;
        this.test = false;
        this.jar = true;

        // Modify the properties
        testArguments(args);

        // Call the right method
        switch(this.os) {
            case "unix":
                unix();
                break;

            case "macos":
                macos();
                break;

            case "windows":
                windows();
                break;

            default:
                log(3, "Bad os " + this.os + ".");
        }
    }

    // Test arguments
    private void testArguments(String[] args) {
        for(int i = 0; i < args.length; i++) {
            switch(args[i]) {
                case "--no-jar":
                    jar = false;
                    break;

                case "--test":
                    test = true;
                    break;

                case "--debug":
                    debug = true;
                    break;

                case "--prefix":
                    if(i < args.length - 1) {
                        i++;
                    } else {
                        log(3, "No prefix gave.");
                    }
                    prefix = args[i];
                    break;

                case "--os":
                    if(i < args.length - 1) {
                        i++;
                    } else {
                        log(3, "No os name gave.");
                    }
                    switch(args[i]) {
                        case "unix":
                        case "macos":
                        case "windows":
                            os = args[i];
                            break;

                        default:
                            log(3, args[i] + " isn't a right os.\nYou can use only these three systems\n\tunix: For *nix systems, Linux and Solaris\n\tmacos: For MacOS or OSX systems.\n\twindows: For windows.");
                    }
                    break;

                case "--build-dir":
                    if(i < args.length - 1) {
                        i++;
                    } else {
                        log(3, "No build directory gave.");
                    }
                    build_dir = args[i];
                    break;

                case "--src-dir":
                    if(i < args.length - 1) {
                        i++;
                    } else {
                        log(3, "No source directory gave.");
                    }
                    src_dir = args[i];
                    break;

                default:
                    log(3, args[i] + " is a bad argument.");
            } // switch
        } // for
    } // void

    // Calls the javac command
    private boolean compile(String... arguments) {
        Tool javac = ToolProvider.getSystemJavaCompiler();
        return javac.run(null, null, null, arguments) == 0;
    }

    // Copy a file or a directory
    private void copy(String inputFile, String outputFile) {
        try {
            Files.copy(new File(inputFile).toPath(), new File(outputFile).toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
        } catch(Exception exception) {
            log(3, exception.getMessage());
        }
    }

    // Prints an information
    private void log(int level, String info) {
        String output;
        PrintStream stream = System.out;
        switch(level) {
            case 0: // title
                output = "\u001B[01;04m"; // bold underlined
                break;
            case 1: // big info
                output = "\u001B[01;35m==> \u001B[00;35m";
                break;
            case 2: // litle info
                output = "\u001B[01;36m -> \u001B[00;36m";
                break;
            case 3: // error
                output = "\u001B[01;31mERROR: \u001B[00;31m";
                stream = System.err;
                break;
            case 4: // warning
                output = "\u001B[03;33mWarning: \u001B[00;33m";
                stream = System.err;
                break;
            case 5: // debug
                if(!debug) return;
                output = "\u001B[02;32m[debug] \u001B[00;32m";
                break;
            default: // just an information
                output = "";
        }
        output += info;
        stream.println(output);
        if(level == 3) {
            System.exit(1);
        }
    }

    // Build the project
    private void build() {
        log(0, "JChess Compilation");
        log(1, "Preparation");
        // delete the build directory if it already exists
        //TODO: Replace it
        //build += "if [ -d " + build_dir + " ] ; then rm -rf " + build_dir + " ; fi\necho \" -> Build directory deleted.\"\n";
        // set the list of modules to build
        String[] modules = {"jchess.engine"};
        log(2, "Created modules build infos");
        log(5, "These modules will be compiled : " + modules);
        // check for JDK > 8
        if(Integer.valueOf(System.getProperty("java.specification.version")) > 8) {
            log(2, "JRE > 8: yes");
        } else {
            log(3, "JRE version lower than 8");
        }
        // compiling
        log(1, "Compilation");
        // compile the files
        //TODO: Create the compile command
        // postbuild commands
        log(1, "Postbuild operations");
        //TODO: Create copying command
        // install the files
        //TODO: Make code
    }

    // If os = "unix"
    private void unix() {
        if(prefix.isEmpty()) {
            prefix = slash + "usr";
        }
        build();
    }

    // If os = "macos"
    private void macos() {
        if(prefix.isEmpty()) {
            prefix = slash + "usr";
        }
        build();
    }

    // If os = "windows"
    private void windows() {
        if(prefix.isEmpty()) {
            prefix = "C:" + slash + "Program files";
        }
        build();
    }

    // Main method
    public static void main(String[] args) {
        new Build(args);
    }
}
