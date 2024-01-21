package unibo.exiled.model.item;

import unibo.exiled.model.character.player.Player;

/**
 * This interface represent an item tha can be used by the player.
 */
public interface UsableItem extends Item{
    void use(Player player);
}
