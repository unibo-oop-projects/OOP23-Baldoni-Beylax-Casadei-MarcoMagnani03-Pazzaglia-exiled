package unibo.exiled.model.character.player;

import unibo.exiled.model.character.Character;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.utilities.ElementalType;

/**
 * The interface of the Player.
 */
public interface Player extends Character{

    /**
     * Gets the level of the player.
     * @return the level of the player.
     */
    public int getLevel();

    /**
     * Gets the experience of the player.
     * @return the experience points of the player.
     */
    public double getExperience();

    /**
     * Gets the inventory of the player.
     * @return the inventory containing the player's items.
     */
    public Inventory getInventory();

    /**
     * Sets the elemental type chosen from the player.
     * @param playerClass the class choosen.
     */
    void setPlayerClass(ElementalType playerClass);

    /**
     * Gets the player class.
     * @return the player class.
     */
    ElementalType getPlayerClass();

    /**
     * checks if the character has enough experience to increase his level and if so he does so by increasing all his statistics
     */
    void levelUp();
}
