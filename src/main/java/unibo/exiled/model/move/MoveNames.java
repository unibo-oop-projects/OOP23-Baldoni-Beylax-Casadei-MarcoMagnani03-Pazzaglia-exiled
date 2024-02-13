package unibo.exiled.model.move;

import unibo.exiled.utilities.ConstantsAndResourceLoader;

/**
 * An enum containing all the moves in the game.
 */
public enum MoveNames {

    /**
     * A normal basic move.
     */
    COLPACCIO("Colpaccio", "Hits the enemy without much enthusiasm.",
    ConstantsAndResourceLoader.LEVEL_BASE_MOVE),
    /**
     * A normal basic move.
     */
    COLPONE("Colpone", "Hits the enemy with a bit more enthusiasm.",
    ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_FIRST_MOVE),
    /**
     * A fire basic move.
     */
    FIREBALL("Fireball", "Throws a really sad fireball at the enemy.",
    ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_FIRST_MOVE),
    /**
     * A grass basic move.
     */
    LEAFBLADE("Leaf Blade", "Summons a blunted blade made of leaves.",
    ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_FIRST_MOVE),
    /**
     * A bolt basic move.
     */
    LIGHTBULB("Light bulb", "Lit oneself body to blind the enemy, not very effective.",
    ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_FIRST_MOVE),
    /**
     * A water basic move.
     */
    WATERPISTOL("Water Pistol", "Spits water from the mouth, pretty disgusting but nothing more.",
    ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_FIRST_MOVE),
    /**
     * A flame move.
     */
    FLAMEWHIRL("Flaming Whirl", "Creates a roundel of flaming braces to throw",
    ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_SECOND_MOVE),
    /**
     * A swift slashing move.
     */
    QUICKSLASH("Quick Slash", "Swiftly slashes through the enemy with precision.",
    ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_SECOND_MOVE),
    /**
     * A storm of sharp petals.
     */
    PETALSTORM("Petal Storm", "Summons a storm of sharp petals to damage the enemy.",
    ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_SECOND_MOVE),
    /**
     * A powerful lightning strike.
     */
    THUNDERSTRIKE("Thunder Strike", "Summons a powerful lightning strike to hit the enemy.",
    ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_SECOND_MOVE),
    /**
     * A sphere of water attack.
     */
    AQUAORB("Water Orb", "Forms a sphere of water and hurls it at the enemy.",
    ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_SECOND_MOVE),
    /**
     * A raging inferno.
     */
    INFERNO("Inferno", "Unleashes a raging inferno to engulf the enemy.",
    ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_BOSS_MOVE),
    /**
     * Generates a great storm with lots of thunders and shocks.
     */
    THUNDERSTORM("Thunder Storm", "Summons a storm right over the opponent.",
    ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_BOSS_MOVE),
    /**
     * Covers the body in electricity and charges the opponent.
     */
    LOCOMOVOLT("Locomovolt", "Covers the body in electricity and charges the opponent.",
    ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_BOSS_MOVE);
    //TODO: Boss acqua non ha una mossa e ce ne sono 2 fulmine

    private final String name;
    private final String description;
    private final int minimumLevelToLearn;

    /**
     * The constructor of the move names.
     *
     * @param name        The name of the move.
     * @param description The description of the move.
     * @param minimumLevelToLearn the minimum level needed to learn the magic move
     */
    MoveNames(final String name, final String description, final int minimumLevelToLearn) {
        this.name = name;
        this.description = description;
        this.minimumLevelToLearn = minimumLevelToLearn;
    }

    /**
     * Gets the name of the move.
     *
     * @return A string representing the name of the move to be viewed.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the description of the move.
     *
     * @return A string containing the description of the move.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the minimum level neded to learn the move.
     *
     * @return A integer containing minimum level to learn the move.
     */
    public int getMinimumLevelToLearnTheMove() {
        return this.minimumLevelToLearn;
    }
}
