package unibo.exiled.model.character.player;

import unibo.exiled.model.character.Character;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.InventoryImpl;
import unibo.exiled.model.utilities.Position;

/**
 * The interface of the Player.
 */
public interface Player extends Character{
    /**
     * @return the inventory of the player.
     */
    Inventory getInventory();

    /**
     * @return the actual position of the player.
     */
    Position getPosition();
}
