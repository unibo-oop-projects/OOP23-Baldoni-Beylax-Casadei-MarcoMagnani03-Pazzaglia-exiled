package unibo.exiled.controller;

import java.util.List;

import unibo.exiled.model.character.CharacterClass;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

/**
 * Controller for managing the characters.
 */
public interface CharacterController {
    /**
     * Gets the image paths for a character.
     *
     * @param folderPath The folder path where the character images are stored.
     * @param name       The name of the character.
     * @return A list containing the paths of the images for the character.
     */
    List<String> getImagePathOfCharacter(String folderPath, String name);

    /**
     * Moves the player in the specified direction.
     *
     * @param direction The direction in which the player should move.
     */
    void movePlayer(Direction direction);

    /**
     * Gets the health of the player.
     *
     * @return A double representing the health of the player.
     */
    double getPlayerHealth();

    /**
     * Gets the cap health of the player.
     *
     * @return A double representing the cap of the health of the player.
     */
    double getPlayerHealthCap();

    /**
     * Gets the level of the player.
     *
     * @return A integer representing the level of the player.
     */
    int getPlayerLevel();

    /**
     * Gets the class name of the player.
     *
     * @return A string representing the class name of the player.
     */
    String getPlayerClassName();

    /**
     * Retrieves the current experience of the player.
     *
     * @return The current experience of the player.
     */
    int getPlayerCurrentExperience();

    /**
     * Retrieves the experience cap of the player.
     *
     * @return The experience cap of the player.
     */
    int getPlayerExperienceCap();

    /**
     * Adds experience to the player.
     * 
     * @param amount the experience the player gained killink an enemy.
     */
    void addPlayerExperience(int amount);

    /**
     * Gets the position of the player.
     *
     * @return The position of the player.
     */
    Position getPlayerPosition();

    /**
     * Gets the names of every magic move.
     *
     * @return A list of every magic move name.
     */
    List<String> getMagicMoveNames();

    /**
     * Returns the description of a magic move.
     * 
     * @param magicMoveName the name of the move to find.
     * @return the description of a magic move.
     */
    String getMagicMoveDescription(String magicMoveName);

    /**
     * Returns the damage to a magic move.
     * 
     * @param magicMoveName the name of the move to find.
     * @return the damage to the move.
     */
    double getMagicMoveDamage(String magicMoveName);

    /**
     * Gets the move set of the player.
     *
     * @return A list of the player moves.
     */
    List<String> getPlayerMoveSet();

    /**
     * Moves the enemies in the game.
     */
    void moveEnemies();

    /**
     * Performs an attack routine.
     * 
     * @param isPlayerAttacking if is the player or the enemy attacking.
     * @param moveName          the name of the move performed.
     * @param combatPosition    the position of the combat.
     * @return if the character that took damage is dead or not.
     */
    boolean attack(boolean isPlayerAttacking, String moveName, Position combatPosition);

    /**
     * Sets the elemental class of the player.
     *
     * @param playerClass The ElementalType representing the new elemental class of
     *                    the player.
     */
    void assignPlayerClass(CharacterClass playerClass);

    /**
     * Gets the state of the character in a certain position.
     *
     * @param position The position where the character is.
     * @return True if the character is moving, false otherwise.
     */
    boolean getIfCharacterInPositionIsMoving(Position position);

    /**
     * Returns the health of the character at the specified position.
     *
     * @param position The position of the character whose health is to be obtained.
     * @return The health of the character at the specified position.
     * @throws IllegalArgumentException if the specified position is null or
     *                                  invalid.
     */
    double getCharacterHealthFromPosition(Position position);

    /**
     * Returns the health cap of the character at the specified position.
     *
     * @param position The position of the character whose health cap is to be
     *                 obtained.
     * @return The health cap of the character at the specified position.
     * @throws IllegalArgumentException if the specified position is null or
     *                                  invalid.
     */
    double getCharacterHealthCapFromPosition(Position position);

    /**
     * Return the name of the class of the character at the specified position.
     *
     * @param position The position of the character whose class name is to be
     *                 obtained.
     * @return The name of the class of the character
     */
    String getCharacterClassNameFromPosition(Position position);

    /**
     * Returns a random move name.
     * 
     * @param position the position of the character from which get the move name.
     * @return a move name.
     */
    String getCharacterRandomMoveNameFromPosition(Position position);

    /**
     * Removes the enemy from a position.
     * 
     * @param position the enemy position.
     */
    void removeEnemyFromPosition(Position position);

    /**
     * Gets the enemy dropped experience.
     * 
     * @param position the position of the enemy.
     * @return the experience droped from the enemy.
     */
    int getEnemyExperienceDropFromPosition(Position position);
}
