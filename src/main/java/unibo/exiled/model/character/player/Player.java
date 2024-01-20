package unibo.exiled.model.character.player;

import unibo.exiled.model.character.Character;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.InventoryImpl;

/**
 * The interface of the Player.
 */
public interface Player extends Character{
    /**
     * This method permits the player to get his inventory. 
     */
    Inventory getInventory();
}
