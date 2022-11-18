package build.buildscripts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

public class FileManager {
    private File sourceDir, buildDir, fakerootDir;

    File getSourceDir() {
        return sourceDir;
    }

    File getBuildDir() {
        return buildDir;
    }

    File getFakerootDir() {
        return fakerootDir;
    }

    /** File separator */
    static String slash() {
        return System.getProperty("file.separator");
    }

    FileManager(String sourceDirectory, String buildDirectory, String fakerootDirectory) {
        sourceDir = new File(sourceDirectory);
        buildDir = new File(buildDirectory);
        fakerootDir = new File(fakerootDirectory);
    }

    /** Copy a file / folder to another location */
    static boolean copy(File input, File output) {
        if(input.isDirectory()) {
            for(File file : input.listFiles()) {
                copy(file, new File(output.getAbsolutePath() + slash() + file.getName()));
            }
        } else {
            try {
                output.mkdirs();
                Files.copy(input.toPath(), output.toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
            } catch(Exception exception) {
                Log.log(Log.ERROR, exception.getMessage());
                return false;
            }
        }
        return true;
    }
    /** Copy a file / folder to another location */
    static boolean copy(String input, String output) {
        return copy(new File(input), new File(output));
    }

    /** Delete a file or a directory */
    static boolean delete(File... files) {
        for(File file : files) {
            // System.out.println(file.getAbsolutePath());
            if(file.isDirectory()) {
                for(File i : file.listFiles()) {
                    if(!delete(i)) {
                        return false;
                    }
                }
                if(!file.delete()) return false;
            } else {
                if(!file.delete()) return false;
            }
        }
        
        return true;
    }

    /** Creates a new file 
     * @throws IOException
     */
    static boolean create(File file) throws IOException {
        return file.mkdirs() && file.createNewFile();
    }

    /** Creates a directory */
    static boolean mkdir(File file) {
        // file.mkdirs();
        return file.mkdirs();
    }
}
