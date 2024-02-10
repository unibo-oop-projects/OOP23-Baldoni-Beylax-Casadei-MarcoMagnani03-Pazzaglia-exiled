package unibo.exiled.utilities;

import javax.annotation.concurrent.Immutable;
import java.net.URL;

/**
 * This class provides constants and configuration loading for the game.
 */
@Immutable
public final class ConstantsAndResourceLoader {
    /**
     * The main folder of the player.
     */
    public static final String PLAYER_PATH = "/player";
    /**
     * The main folder of the enemies.
     */
    public static final String ENEMY_PATH = "/enemy";
    /**
     * The name of the player for image purposes.
     */
    public static final String PLAYER_NAME = "boy";
    /**
     * The resource path to images.
     */
    public static final String IMAGES_PATH = "unibo/exiled/images";
    /**
     * The experience level modifier while leveling up.
     */
    public static final double LEVEL_MODIFIER = 0.2;
    /**
     * The neutral modifier to be applied.
     */
    public static final double NEUTRAL_MODIFIER = 1.0;
    /**
     * The cap of experience of the player.
     */
    public static final int PLAYER_EXPERIENCE_CAP = 10;
    /**
     * Describes by how much the attributes of the player increase when leveling up.
     */
    public static final int PLAYER_ATTRIBUTE_INCREASE_BOUND = 6;
    /**
     * The interval of levels between the possibility of learning a new move.
     */
    public static final int PLAYER_MOVES_LEARNING_INTERVAL = 5;
    /**
     * The default number of moves the player can have.
     */
    public static final int PLAYER_MOVES_NUMBER = 4;
    /**
     * The size of the central PLAINS area.
     */
    public static final int STARTING_SAFEZONE_SIZE = 4;
    /**
     * The size of the map.
     */
    public static final int MAP_SIZE = 100;
    /**
     * The level at which the player starts.
     */
    public static final int PLAYER_STARTING_LEVEL = 1;
    /**
     * The number of enemies to be scattered on the map.
     */
    public static final int NUM_ENEMIES = 100;

    private ConstantsAndResourceLoader() {
    }

    /**
     * Gets a URL for multiplatform resource sharing.
     *
     * @param path The path of the resource in the file structure.
     * @return A URL loaded by the java ClassLoader.
     */
    public static URL getResourceURLFromPath(final String path) {
        return ClassLoader.getSystemResource(path);
    }
}
