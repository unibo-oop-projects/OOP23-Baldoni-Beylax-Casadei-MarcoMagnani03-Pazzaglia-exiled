package unibo.exiled.model.move;

/**
 * An enum containing all the moves in the game.
 */
public enum MoveNames {

    /**
     * A normal basic move.
     */
    COLPACCIO("Colpaccio", "Hits the enemy without much enthusiasm."),
    /**
     * A normal basic move.
     */
    COLPONE("Colpone", "Hits the enemy with a bit more enthusiasm."),
    /**
     * A fire basic move.
     */
    FIREBALL("Fireball", "Throws a really sad fireball at the enemy."),
    /**
     * A grass basic move.
     */
    LEAFBLADE("Leaf Blade", "Summons a blunted blade made of leaves."),
    /**
     * A bolt basic move.
     */
    LIGHTBULB("Light bulb", "Lit oneself body to blind the enemy, not very effective."),
    /**
     * A water basic move.
     */
    WATERPISTOL("Water Pistol", "Spits water from the mouth, pretty disgusting but nothing more."),
    /**
     * A flame move.
     */
    FLAMEWHIRL("Flaming Whirl", "Creates a roundel of flaming braces to throw"),
    /**
     * A swift slashing move.
     */
    QUICKSLASH("Quick Slash", "Swiftly slashes through the enemy with precision."),
    /**
     * A storm of sharp petals.
     */
    PETALSTORM("Petal Storm", "Summons a storm of sharp petals to damage the enemy."),
    /**
     * A powerful lightning strike.
     */
    THUNDERSTRIKE("Thunder Strike", "Summons a powerful lightning strike to hit the enemy."),
    /**
     * A sphere of water attack.
     */
    AQUAORB("Water Orb", "Forms a sphere of water and hurls it at the enemy."),
    /**
     * A raging inferno.
     */
    INFERNO("Inferno", "Unleashes a raging inferno to engulf the enemy."),
    /**
     * Generates a great storm with lots of thunders and shocks.
     */
    THUNDERSTORM("Thunder Storm", "Summons a storm right over the opponent."),
    /**
     * Covers the body in electricity and charges the opponent.
     */
    LOCOMOVOLT("Locomovolt", "Covers the body in electricity and charges the opponent.");

    private final String name;
    private final String description;

    /**
     * The constructor of the move names.
     *
     * @param name        The name of the move.
     * @param description The description of the move.
     */
    MoveNames(final String name, final String description) {
        this.name = name;
        this.description = description;
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
}
