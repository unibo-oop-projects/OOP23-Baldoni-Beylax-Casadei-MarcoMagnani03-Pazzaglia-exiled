package unibo.exiled.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides constants and configuration loading for the game.
 */
public final class Constants {

    /**
     * Default path to the configuration file.
     */
    public static final String DEF_CONFIG_PATH = "src"
            + File.separator
            + "main"
            + File.separator
            + "java"
            + File.separator
            + "unibo"
            + File.separator
            + "exiled"
            + File.separator
            + "config"
            + File.separator
            + "config.yml";

    private static final Map<String, String> CONSTANTS_MAP = new HashMap<>();

    private static final Logger LOGGER = Logger.getLogger(Constants.class.getName());

    private Constants() {
    }

    /**
     * Loads configuration from the specified path.
     *
     * @param configPath The path to the configuration file.
     */
    public static void loadConfiguration(final String configPath) {
        final File file = new File(configPath);
        final Scanner reader;
        String data;
        String cName;
        String value;
        try {
            reader = new Scanner(file);
            while (reader.hasNextLine()) {
                data = reader.nextLine();
                cName = data.substring(0, data.indexOf(':')).trim();
                value = data.substring(data.indexOf(':') + 1).trim();
                CONSTANTS_MAP.put(cName, value);
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error reading the configuration file", ex);
        }
    }

    /**
     * Retrieves the value of a constant by its name.
     *
     * @param name The name of the constant.
     * @return The value of the constant.
     */
    public static String getConstantOf(final String name) {
        return CONSTANTS_MAP.get(name);
    }
}
