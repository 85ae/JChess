package jchess.ui.locale;

/**
 * This interface permits access to language informations.<br/>
 * The recommended way to use it is to simply define the {@link #get(String)} method to parse from a language file.<br/>
 */
public interface Locale {
    /**
     * This method returns a text.<br/>
     * You may use the {@link java.util.Properties} class to parse a file.
     * The keys that are generally sent to this method are under the form "jchess.<em>info.asked</em>".
     * @param key the key to search.
     * @return the asked text, used to display something for example.
     */
    public String get(String key);
}
