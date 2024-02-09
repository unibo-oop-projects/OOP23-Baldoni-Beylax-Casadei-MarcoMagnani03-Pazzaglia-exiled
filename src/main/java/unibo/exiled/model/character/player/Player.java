package unibo.exiled.model.character.player;

import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.UsableItem;
import unibo.exiled.model.utilities.ElementalType;

/**
 * The interface of the Player.
 */
public interface Player extends GameCharacter {

    /**
     * Gets the level of the player.
     *
     * @return the level of the player.
     */
    int getLevel();

    /**
     * Gets the experience of the player.
     *
     * @return the experience points of the player.
     */
    int getExperience();

    /**
     * Gets the inventory of the player.
     *
     * @return the inventory containing the player's items.
     */
    Inventory getInventory();

    /**
     * Adds an item to the player inventory.
     * 
     * @param item the item to be added.
     */
    void addItemToInventory(Item item);

    /**
     * Gets the player class.
     *
     * @return the player class.
     */
    ElementalType getPlayerClass();

    /**
     * Sets the elemental type chosen from the player.
     *
     * @param playerClass the class choosen.
     */
    void setPlayerClass(ElementalType playerClass);

    /**
     * Adds experience, if it exceeds the levelUp cap by increasing statistics.
     *
     * @param exp experience provided to the user.
     */
    void addExperience(int exp);

    /**
     * Uses the specified UsableItem. The effect of the item is applied to the
     * player,
     * and the item is consumed from the player's inventory.
     *
     * @param item The UsableItem to be used.
     */
    void useItem(UsableItem item);

    /**
     * Gets the experience cap of the player.
     *
     * @return the experience cap of the player.
     */
    int getCapExperience();

}
