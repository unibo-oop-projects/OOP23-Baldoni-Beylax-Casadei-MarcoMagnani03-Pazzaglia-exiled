package unibo.exiled.config;

import java.util.Objects;

/**
 * A utility class to fall back to default values when loading from the config file is not possible.
 */
public final class FallBackManager {
    private FallBackManager() {

    }

    /**
     * Gets the fallback value of the configuration with the selected name.
     *
     * @param name The name of the value to get.
     * @return A String to be parsed containing the fallback value.
     */
    public static String getFallbackNumberFromConfigName(final String name) {
        Objects.requireNonNull(name);
        try {
            return FallbackValues.valueOf(name).getFallbackValue();
        } catch (IllegalArgumentException exception) {
            if (name.contains("MODIFIER")) {
                return FallbackValues.valueOf("DEFAULT_MODIFIER").getFallbackValue();
            } else {
                throw exception;
            }
        }
    }

    private enum FallbackValues {
        PLAYER_DEFAULT_HEALTH("100.0"),
        PLAYER_DEFAULT_HEALTH_CAP("200.0"),
        PLAYER_EXPERIENCE_CAP("10.0"),
        PLAYER_DEFAULT_EXPERIENCE("0.0"),
        PLAYER_DEFAULT_LEVEL("0"),
        PLAYER_LEVEL_INCREASE("1"),
        PLAYER_ATTRIBUTE_INCREASE_BOUND("6"),
        NUM_ENEMIES("10"),
        NUM_PLAYER_MOVES("4"),
        MAP_SIZE("100"),
        DEFAULT_MODIFIER("1.0"),
        MOVES_LEARNING_INTERVAL("5"),
        EXPERIENCE_MULTIPLIER("0.2"),
        STARTING_SIZE("10"),
        SAFE_ZONE("4");
        private final String defaultValue;

        FallbackValues(final String defaultValue) {
            this.defaultValue = defaultValue;
        }

        public String getFallbackValue() {
            return this.defaultValue;
        }
    }
}
