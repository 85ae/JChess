package build.buildscripts;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Options {
    String prefix, os, buildDir, srcDir, module;
    boolean test, debug;

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
        module = "jchess.engine";
        debug = false;
        test = false;

        // Modify the properties
        testArguments(args);
    }

    /** Test arguments
     * @param args The arguments passed by the main function
     */
    private void testArguments(String[] args) {
        for(int i = 0; i < args.length; i++) {
            switch(args[i]) {
                case "--test" -> test = true;

                case "--debug" -> debug = true;

                case "--clean" -> {
                    File[] buildDirectories = new File("build").listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            return name.toLowerCase().startsWith("build_");
                        }
                    });
                    FileManager.delete(buildDirectories);
                    Log.log(Log.LITLE, "Build directories cleaned.");
                    System.exit(0);
                }

                case "--prefix" -> {
                    if(i < args.length - 1) {
                        i++;
                    } else {
                        Log.log(Log.FATALERROR, "No prefix gave.");
                    }
                    prefix = args[i];
                }

                case "--os" -> {
                    if(i < args.length - 1) {
                        i++;
                    } else {
                        Log.log(Log.FATALERROR, "No os name gave.");
                    }
                    switch(args[i]) {
                        case "unix", "macos", "windows" -> os = args[i];

                        default -> Log.log(Log.FATALERROR, args[i] + " isn't a right os.\nYou can use only these three systems\n\tunix: For *nix systems, Linux and Solaris\n\tmacos: For MacOS or OSX systems.\n\twindows: For windows.");
                    }
                }

                case "--build-dir" -> {
                    if(i < args.length - 1) {
                        i++;
                    } else {
                        Log.log(Log.FATALERROR, "No build directory gave.");
                    }
                    buildDir = args[i];
                }

                case "--src-dir" -> {
                    if(i < args.length - 1) {
                        i++;
                    } else {
                        Log.log(Log.FATALERROR, "No source directory gave.");
                    }
                    srcDir = args[i];
                }

                case "-h", "--help" -> Log.help(); // prints the help then exit

                default -> Log.log(Log.FATALERROR, args[i] + " is a bad argument.");
            } // switch
        } // for
    } // void
}
