import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import javax.tools.Tool;
import javax.tools.ToolProvider;

/** The build class
 * Type `java Build.java [OPTIONS]` to build the program.
 * To see more help about the usage, type `java Build.java --help`.
 * @author 85ae
 * @version 0.0.1
 */
class Build {
    private String prefix, os, buildDir, srcDir, slash;
    private boolean test, debug, jchess_app, jchess_engine, jchess_interface;
    private String[] modules;

    /** Create the build / install / test / clean files
     * @param args The arguments passed by the main method
     */
    private Build(String[] args) {
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
        this.buildDir = "." + slash + "build";
        this.srcDir = "." + slash + "src";
        this.prefix = "";
        this.debug = false;
        this.test = false;
        this.jchess_app = true;
        this.jchess_engine = true;
        this.jchess_interface = true;

        // Modify the properties
        testArguments(args);
        if(jchess_engine) {
            if(jchess_interface) {
                if(jchess_app) {
                    modules = new String[] {"jchess.engine", "jchess.interface", "jchess.app"};
                }
                modules = new String[] {"jchess.engine", "jchess.interface"};
            }
            modules = new String[] {"jchess.engine"};
        } else if(jchess_interface) {
            modules = new String[] {"jchess.interface"};
        } else {
            log(3, "The project won't be compiled because all the modules are disabled.");
        }

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

    /** Test arguments
     * @param args The arguments passed by the main function
     */
    private void testArguments(String[] args) {
        for(int i = 0; i < args.length; i++) {
            switch(args[i]) {
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
                    buildDir = args[i];
                    break;

                case "--src-dir":
                    if(i < args.length - 1) {
                        i++;
                    } else {
                        log(3, "No source directory gave.");
                    }
                    srcDir = args[i];
                    break;

                case "--no-engine":
                    jchess_engine = false;
                    jchess_app = false;
                    break;

                case "--no-interface":
                    jchess_interface = false;
                case "--no-app":
                    jchess_app = false;
                    break;

                case "-h":
                case "--help":
                    help(); // there's no break command because this method exit the program

                default:
                    log(3, args[i] + " is a bad argument.");
            } // switch
        } // for
    } // void

    /** Print an help message then exit */
    private void help() {
        String helpMessage = "";
        helpMessage += "Jchess Build Script\n";
        helpMessage += "\n";
        helpMessage += "Usage: java Build.java [OPTIONS]\n";
        helpMessage += "\n";
        helpMessage += "Where OPTIONS can be:\n";
        helpMessage += "\t--help, -h\tPrint this help message.\n";
        helpMessage += "\t--prefix PREFIX\tThe directory (`/usr` by default on Linux / Unix / Macos, `C:\\Program files\\JChess` on Windows) where are placed the library files. DO NOT set the root on Windows (write `\\Program files\\JChess` and not `C:\\Program files\\JChess` for example).\n";
        helpMessage += "\t--os OS\tMake the scripts for os `OS` (can be `unix` for a *nix or linux-based system, `macos` or `windows`).\n";
        helpMessage += "\t--test\tEnable testing (disabled by default).\n";
        helpMessage += "\t--debug\tEnable debugging (disabled by default).\n";
        helpMessage += "\t--build-dir DIR\tBuild in the directory `DIR` instead of `./build`.\n";
        helpMessage += "\t--src-dir DIR\tSearch the sources in the directory `DIR` instead of `./src`.\n";
        helpMessage += "\t--no-app\tDisable the `jchess.app` module.\n";
        helpMessage += "\t--no-engine\tDisable the `jchess.engine` and `jchess.app` modules.\n";
        helpMessage += "\t--no-interface\tDisable the `jchess.interface` and `jchess.app` modules.\n";

        System.out.println(helpMessage);
        System.exit(0);
    }

    /** Calls the javac command
     * @param arguments A list of arguments to pass to compiler.
     * @return True if the compilation passed without any error, else false.
     */
    private boolean compile(String... arguments) {
        Tool javac = ToolProvider.getSystemJavaCompiler();
        return javac.run(null, null, null, arguments) == 0;
    }

    /// Copy a file or a directory
    private void copy(String input, String output) {
        File inputFile = new File(input);
        File outputFile = new File(output);
        if(inputFile.isDirectory()) {
            for(String file : inputFile.list()) {
                copy(input + slash + file, output + slash + file);
            }
        } else {
            try {
                outputFile.mkdirs();
                Files.copy(inputFile.toPath(), outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
            } catch(Exception exception) {
                log(3, exception.getMessage());
            }
        }
    }

    /** Prints an information
     * @param level The log level. can be: <ul><li>0: Title</li><li>1: Big information</li><li>2: Litle information</li><li>3: Error (exit)</li><li>4: Warning</li><li>5: Debug</li><li>Any other: just a text</li></ul>
     * @param info The text to display.
     */
    private void log(int level, String info) {
        String output;
        PrintStream stream = System.out;
        switch(level) {
            case 0: // title
                output = "\u001B[01;04m"; // bold underlined
                break;
            case 1: // big info
                output = "\n\u001B[01;35m==> \u001B[00;35m";
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
        output += "\u001B[00m";
        stream.println(output);
        if(level == 3) {
            System.exit(1);
        }
    }

    /// Build the project
    private void build() {
        log(0, "JChess Compilation");
        log(1, "Preparation");
        // delete the build directory if it already exists
        File buildDirectory = new File(buildDir);
        if(buildDirectory.exists()) {
            if(buildDirectory.delete()) {
                log(2, "Build diretory deleted.");
            } else {
                log(4, "Build directory can't be deleted.");
                log(5, "Build directory is " + buildDirectory.getAbsolutePath());
            }
        }
        // create it
        if(buildDirectory.mkdirs()) {
            log(2, "Build directory created.");
        } else {
            log(5, "The build directory is " + buildDirectory.getAbsolutePath());
            log(3, "Build directory wasn't created. The build directory must can be created to compile correctly the files.");
        }
        log(5, "These modules will be compiled : " + Arrays.deepToString(modules));
        // check for JDK > 8
        if(Integer.valueOf(System.getProperty("java.specification.version")) > 8) {
            log(2, "JRE > 8: yes");
        } else {
            log(3, "JRE version lower than 9");
        }
        // compiling
        log(1, "Compilation");
        // compile the files
        for(String module : modules) {
            boolean result;
            if(debug) {
                result = compile("--module-source-path", srcDir, "-d", buildDir, "--module", module, "--debug", "--verbose");
            } else {
                result = compile("--module-source-path", srcDir, "-d", buildDir, "--module", module);
            }
            if(result) {
                log(2, "Module " + module + " compiled.");
            } else {
                log(3, "Module " + module + " wasn't compiled. Some errors occured.");
            }
        }
        log(2, "All the modules were compiled.");
        // post-build commands
        //log(1, "Post-build operations");
        // install the files in fake root
        log(1, "Installing in fakeroot environment...");
        // create the fakeroot directory
        File fakerootDir = new File(buildDir + slash + "fakeroot");
        if(fakerootDir.mkdir()) {
            log(2, "Fakeroot directory successfully created.");
        } else {
            log(5, "The fakeroot directory is " + fakerootDir.getAbsolutePath());
            log(3, "Fakeroot directory wasn't created. The fakeroot directory must can be created to install correctly the files.");
        }
        // install in the fakeroot directory
        for(String module : modules) {
            copy(buildDir + slash + module, fakerootDir + prefix + slash + "bin" + slash + module);
            log(2, "Module " + module + " was copied in the fakeroot environment.");
        }
    }

    // If os = "unix"
    private void unix() {
        if(prefix.isEmpty()) {
            prefix = slash + "usr";
        }
        build();
        log(1, "Post-install operations");
        //TODO: Add post-install commands
        log(1, "Tips");
        //TODO: Display tips
    }

    // If os = "macos"
    private void macos() {
        if(prefix.isEmpty()) {
            prefix = slash + "usr";
        }
        build();
        log(1, "Post-install operations");
        //TODO: Add post-install commands
        log(1, "Tips");
        //TODO: Display tips
    }

    // If os = "windows"
    private void windows() {
        if(prefix.isEmpty()) {
            prefix = slash + "Program files" + slash + "JChess";
        }
        build();
        log(1, "Post-install operations");
        //TODO: Add post-install commands
        log(1, "Tips");
        log(-1, "You can type \n\tCopy-Item -Recurse -Path Path\\To\\JChess\\build\\fakeroot\\*\\* -Destination C:\\\nin an admin powershell.");
    }

    /// Main method
    public static void main(String[] args) {
        new Build(args);
    }
}
