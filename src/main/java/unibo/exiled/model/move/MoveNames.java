package unibo.exiled.model.move;

/**
 * An enum containing all the moves in the game.
 */
public enum MoveNames {

    /**
     * A normal basic move.
     */
    COLPACCIO("Colpaccio"),
    /**
     * A normal basic move.
     */
    COLPONE("Colpone"),
    /**
     * A fire basic move.
     */
    FIREBALL("Fireball"),
    /**
     * A grass basic move.
     */
    LEAFBLADE("Leaf Blade"),
    /**
     * A bolt basic move.
     */
    LIGHTBULB("Light bulb"),
    /**
     * A water basic move.
     */
    WATERPISTOL("Water Pistol"),
    /**
     * A flame move.
     */
    FLAMEWHIRL("Flaming Whirl"),
    /**
     * A swift slashing move.
     */
    QUICKSLASH("Quick Slash"),
    /**
     * A storm of sharp petals.
     */
    PETALSTORM("Petal Storm"),
    /**
     * A powerful lightning strike.
     */
    THUNDERSTRIKE("Thunder Strike"),
    /**
     * A sphere of water attack.
     */
    AQUAORB("Aqua Orb"),
    /**
     * A raging inferno.
     */
    INFERNO("Inferno");
    
    private final String name;

    /**
     * The constructor of the move names.
     *
     * @param name The name of the move.
     */
    MoveNames(final String name) {
        this.name = name;
    }

    /**
     * Gets the name of the move.
     *
     * @return A string representing the name of the move to be viewed.
     */
    public String getName() {
        return this.name;
    }
}
