package config;

// This class is used to get a 
public class Lang {
    ConfigFile file;

    // Default constructor
    public Lang() {
        String lang = new ConfigFile(System.getProperty("user.home") + "/.chess/tmp_lang.conf").getProperty("lang");
        file = new ConfigFile("lang/" + lang + ".lang");
    }

    // Get a text with the name key
    public String getText(String key) {
        return file.getProperty(key);
    }

    // Get a char with the name key
    public char getChar(String key) {
        return getText(key).charAt(0);
    }
}
