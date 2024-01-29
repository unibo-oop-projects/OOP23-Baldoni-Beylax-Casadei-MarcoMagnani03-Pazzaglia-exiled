package unibo.exiled.model.character.player;

import unibo.exiled.model.character.Character;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.utilities.ElementalType;

/**
 * The interface of the Player.
 */
public interface Player extends Character{
    /**
     * @return the player level.
     */
    int getLevel();

    /**
     * @return the player experience.
     */
    double getExperience();

    /**
     * @return the inventory of the player.
     */
    Inventory getInventory();

    /**
     * Set the elemental type chosen from the player.
     * @param playerClass the class choosen from the player
     */
    void setPlayerClass(ElementalType playerClass);

    /**
     * Get the elemental type chosen from the player.
     * @return the elemental type of the player
     */
    ElementalType getPlayerClass();
}
