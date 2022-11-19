package build.buildscripts;

import java.util.Arrays;

public class Builder {
    public Builder(String[] args) {
        Options options = new Options(args);
        FileManager filer = new FileManager(options.srcDir, options.buildDir, options.buildDir + FileManager.slash() + "fakeroot");

        // I don't add an 'if options.prefix.isEmpty' condition because it verify that's a correct os
        switch(options.os) {
            case "unix":
                if(options.prefix.isEmpty()) {
                    options.prefix = FileManager.slash() + "usr";
                }
                break;

            case "macos":
                if(options.prefix.isEmpty()) {
                    options.prefix = FileManager.slash() + "usr";
                }
                break;

            case "windows":
                if(options.prefix.isEmpty()) {
                    options.prefix = FileManager.slash() + "Program files" + FileManager.slash() + "JChess";
                }
                break;

            default:
                Log.log(Log.FATALERROR, "Bad os " + options.os + ". Type `java Configure.java --help' to see the os you can specify.");
        }

        Log.log(Log.TITLE, "JChess Compilation");
        Log.log(Log.BIG, "Preparation");
        // delete the build directory if it already exists
        if(filer.getBuildDir().exists()) {
            if(FileManager.delete(filer.getBuildDir())) {
                Log.log(Log.LITLE, "Build diretory deleted.");
            } else {
                Log.log(Log.WARNING, "Build directory can't be deleted.");
                if(options.debug) Log.log(Log.DEBUG, "Build directory is " + filer.getBuildDir().getAbsolutePath());
            }
        }
        // create it
        if(FileManager.mkdir(filer.getBuildDir())) {
            Log.log(Log.LITLE, "Build directory created.");
        } else {
            if(options.debug) Log.log(Log.DEBUG, "The build directory is " + filer.getBuildDir().getAbsolutePath());
            Log.log(Log.FATALERROR, "Build directory wasn't created. The build directory must can be created to compile correctly the files.");
        }
        if(options.debug) Log.log(Log.DEBUG, "These modules will be compiled : " + Arrays.deepToString(options.modules));
        // check for JDK > 8
        if(Integer.valueOf(System.getProperty("java.specification.version")) >= 14) {
            Log.log(Log.LITLE, "JRE >= 14: yes");
        } else {
            Log.log(Log.FATALERROR, "JRE version lower than 14");
        }
        // compiling
        Log.log(Log.BIG, "Compilation");
        // compile the files
        for(String module : options.modules) {
            boolean result;
            String[] arguments = new String[]{"--module-source-path", filer.getSourceDir().getAbsolutePath(), "-d", filer.getBuildDir().getAbsolutePath(), "--module", module};
            if(options.debug) {
                String[] finalArguments = Arrays.copyOf(arguments, arguments.length + 2);
                finalArguments[finalArguments.length - 2] = "--debug";
                finalArguments[finalArguments.length - 1] = "--verbose";
                result = Compilation.compile(finalArguments);
            } else {
                result = Compilation.compile(arguments);
            }
            if(result) {
                Log.log(Log.LITLE, "Module " + module + " compiled.");
            } else {
                Log.log(Log.ERROR, "Module " + module + " wasn't compiled. Some errors occured.");
            }
        }
        Log.log(Log.LITLE, "All the modules were compiled.");
        // post-build commands
        //log(1, "Post-build operations");
        // install the files in fake root
        Log.log(Log.BIG, "Installing in fakeroot environment...");
        // create the fakeroot directory
        if(FileManager.mkdir(filer.getFakerootDir())) {
            Log.log(Log.LITLE, "Fakeroot directory successfully created.");
        } else {
            if(options.debug) Log.log(Log.DEBUG, "The fakeroot directory is " + filer.getFakerootDir().getAbsolutePath());
            Log.log(Log.FATALERROR, "Fakeroot directory wasn't created. The fakeroot directory must can be created to install correctly the files.");
        }
        // install in the fakeroot directory
        for(String module : options.modules) {
            FileManager.copy(filer.getBuildDir().getAbsolutePath() + FileManager.slash() + module, filer.getFakerootDir().getAbsolutePath() + options.prefix + FileManager.slash() + "lib" + FileManager.slash() + module);
            Log.log(Log.LITLE, "Module " + module + " was copied in the fakeroot environment.");
        }

        // Post-install operations
        Log.log(Log.BIG, "Post-install operations");

        switch(options.os) {
            case "unix":
                Log.log(Log.BIG, "Tips");
                Log.log(Log.SAMPLE, "You can type \n\tcp -r " + filer.getFakerootDir().getAbsolutePath() + "/* /\nas root.");
                break;

            case "macos":
                Log.log(Log.BIG, "Tips");
                Log.log(Log.SAMPLE, "You can type \n\tcp -r " + filer.getFakerootDir().getAbsolutePath() + "/* /\nas root.");
                break;

            case "windows":
                Log.log(Log.BIG, "Tips");
                Log.log(Log.SAMPLE, "You can type \n\tCopy-Item -Recurse -Path " + filer.getFakerootDir().getAbsolutePath() + "\\*\\* -Destination C:\\\nin an admin powershell.");
                break;

            default:
                Log.log(Log.FATALERROR, "Bad os " + options.os + ".");
        }
    }
}
