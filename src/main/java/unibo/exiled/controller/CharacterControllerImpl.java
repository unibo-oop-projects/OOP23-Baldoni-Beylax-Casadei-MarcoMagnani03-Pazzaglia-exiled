package unibo.exiled.controller;

import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.game.GameModel;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.Position;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the CharacterController interface.
 */
public class CharacterControllerImpl implements CharacterController {

    private final GameModel model;

    /**
     * Constructor for the CharacterControllerImpl.
     *
     * @param model The game model to manage the game.
     */
    public CharacterControllerImpl(final GameModel model) {
        this.model = model;
    }

    /**
     * Gets the image paths of a character.
     *
     * @param folderPath The folder path for the character images.
     * @param name       The name of the character.
     * @return A list containing the paths of the character images.
     */
    @Override
    public List<String> getImagePathOfCharacter(final String folderPath, final String name) {
        return List.of(
                folderPath,
                name + "_up",
                name + "_down",
                name + "_left",
                name + "_right");
    }

    /**
     * Gets the health attribute of the player.
     *
     * @return The health attribute value of the player.
     */
    @Override
    public double getPlayerHealth() {
        return this.model.getPlayerAttributeOf(AttributeIdentifier.HEALTH);
    }

    /**
     * Gets the level of the player.
     *
     * @return The level of the player.
     */
    @Override
    public int getPlayerLevel() {
        return this.model.getPlayerLevel();
    }

    /**
     * Gets the class name of the player.
     *
     * @return The class name of the player.
     */
    @Override
    public String getPlayerClassName() {
        return this.model.getPlayerClass().getName();
    }

    /**
     * Moves the player in the specified direction.
     *
     * @param direction The direction in which the player should move.
     */
    @Override
    public void movePlayer(final Direction direction) {
        this.model.movePlayer(direction);
    }

    /**
     * Moves the enemies in the game.
     */
    @Override
    public void moveEnemies() {
        this.model.moveEnemies();
    }

    /**
     * Gets the position of the player.
     *
     * @return The position of the player.
     */
    @Override
    public Position getPlayerPosition() {
        return this.model.getPlayerPosition();
    }

    /**
     * Gets the names of the magic moves available in the game.
     *
     * @return A list of magic move names.
     */
    @Override
    public List<String> getMagicMoveNames() {
        return this.model.getMagicMoves().stream().map(move -> move.name()).collect(Collectors.toUnmodifiableList());
    }

    /**
     * Gets the names of the player's move set.
     *
     * @return A list of player move names.
     */
    @Override
    public List<String> getPlayerMoveSet() {
        return this.model.getPlayerMoveSet().getMagicMoves().stream().map(move -> move.name())
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Performs an attack based on the specified condition.
     *
     * @param cond The condition for the attack.
     */
    @Override
    public void attack(final boolean cond) {
        // TODO: Implement attack logic
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    /**
     * Sets the class of the player.
     *
     * @param playerClass The ElementalType representing the player's class.
     */
    @Override
    public void assignPlayerClass(final ElementalType playerClass) {
        model.assignPlayerClass(playerClass);
    }
}
