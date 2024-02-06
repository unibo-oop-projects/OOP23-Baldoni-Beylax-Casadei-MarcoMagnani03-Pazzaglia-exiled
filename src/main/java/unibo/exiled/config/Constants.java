package unibo.exiled.config;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
            + File.separator + "main" + File.separator + "java" + File.separator + "unibo" + File.separator
            + "exiled" + File.separator
            + "config" + File.separator
            + "config.yml";

    /**
     * Default path of the images of the characters.
     */
    public static final String DEF_RESOURCE_PATH =
            "src" + File.separator + "main" + File.separator + "java" + File.separator + "unibo" + File.separator
                    + "exiled" + File.separator
                    + "resources" + File.separator;
    /**
     * The main folder of the player.
     */
    public static final String PLAYER_PATH = "player";
    public static final String ENEMY_PATH = "enemy";
    /**
     * The name of the player for image purposes.
     */
    public static final String PLAYER_NAME = "boy";
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
        if (Objects.nonNull(configPath)) {
            final File file = new File(configPath);
            final Scanner reader;
            String data;
            String cName;
            String value;
            try {
                reader = new Scanner(file, StandardCharsets.UTF_8);
                while (reader.hasNextLine()) {
                    data = reader.nextLine();
                    cName = data.substring(0, data.indexOf(':')).trim();
                    value = data.substring(data.indexOf(':') + 1).trim();
                    CONSTANTS_MAP.put(cName, value);
                }
                reader.close();
            } catch (final IOException ex) {
                LOGGER.log(Level.WARNING, "Error reading the configuration file", ex);
            }
        }
    }

    /**
     * Retrieves the value of a constant by its name.
     *
     * @param name The name of the constant.
     * @return The value of the constant.
     */
    public static String getConstantOf(final String name) {
        if (Objects.isNull(CONSTANTS_MAP.get(name))) {
            return FallBackManager.getFallbackNumberFromConfigName(name);
        } else {
            return CONSTANTS_MAP.get(name);
        }
    }
}
