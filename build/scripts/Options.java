package build.buildscripts;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Options {
    String prefix, os, buildDir, srcDir;
    boolean test, debug, jchess_app, jchess_engine, jchess_interface;
    String[] modules;

    /** Contructor. */
    public Options(String[] args) {
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
        buildDir = "build" + FileManager.slash() + "build_" + new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
        srcDir = "src";
        prefix = "";
        debug = false;
        test = false;
        jchess_app = true;
        jchess_engine = true;
        jchess_interface = true;

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
            Log.log(Log.FATALERROR, "The project won't be compiled because all the modules are disabled.");
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

                case "--clean":
                    File[] buildDirectories = new File("build").listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            return name.toLowerCase().startsWith("build_");
                        }
                    });
                    FileManager.delete(buildDirectories);
                    Log.log(Log.LITLE, "Build directories cleaned.");
                    break;

                case "--prefix":
                    if(i < args.length - 1) {
                        i++;
                    } else {
                        Log.log(Log.FATALERROR, "No prefix gave.");
                    }
                    prefix = args[i];
                    break;

                case "--os":
                    if(i < args.length - 1) {
                        i++;
                    } else {
                        Log.log(Log.FATALERROR, "No os name gave.");
                    }
                    switch(args[i]) {
                        case "unix":
                        case "macos":
                        case "windows":
                            os = args[i];
                            break;

                        default:
                            Log.log(Log.FATALERROR, args[i] + " isn't a right os.\nYou can use only these three systems\n\tunix: For *nix systems, Linux and Solaris\n\tmacos: For MacOS or OSX systems.\n\twindows: For windows.");
                    }
                    break;

                case "--build-dir":
                    if(i < args.length - 1) {
                        i++;
                    } else {
                        Log.log(Log.FATALERROR, "No build directory gave.");
                    }
                    buildDir = args[i];
                    break;

                case "--src-dir":
                    if(i < args.length - 1) {
                        i++;
                    } else {
                        Log.log(Log.FATALERROR, "No source directory gave.");
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
                    Log.help(); // there's no break command because this method exits the program

                default:
                    Log.log(Log.FATALERROR, args[i] + " is a bad argument.");
            } // switch
        } // for
    } // void
}
