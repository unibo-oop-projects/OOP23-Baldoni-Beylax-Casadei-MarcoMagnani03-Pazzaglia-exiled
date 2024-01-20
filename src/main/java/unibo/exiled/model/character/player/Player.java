package unibo.exiled.model.character.player;

import unibo.exiled.model.character.Character;
import unibo.exiled.model.item.UsableItem;

/**
 * The interface of the Player.
 */
public interface Player extends Character{
    /**
     * This method permits the player to use the selected item. 
     * @param selectedItem is the item the player wants to use.
     */
    void useItem(UsableItem selectedItem);
}
