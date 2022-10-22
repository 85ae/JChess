// This package is used to create, read and access config files.
package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

// The class ConfigFile open a configuration file and can read it.
public class ConfigFile {
	private File file;
	private Properties properties;

	/* This constructor accepts 1 arguments:
	 * file: The path of the file, absolute or relative
	 */
	public ConfigFile(String file) {
		properties = new Properties();
		this.file = new File(file);
		if( ! this.file.exists() ) {
			try {
				this.file.createNewFile();
			} catch (Exception ex) {
				ex.getStackTrace();
			}
		}
	}

	// Return the property key
	public String getProperty(String key) {
		try {
			FileInputStream input = new FileInputStream(file);
			properties.load(input);
			return properties.getProperty(key);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			return "";
		}
	}

	// Set the property key to value
	public void setProprety(String key, String value) {
		try {
			FileInputStream input = new FileInputStream(file);
			properties.load(input);
			properties.setProperty(key, value);
			FileOutputStream output = new FileOutputStream(file);
			properties.store(output, "");

		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
