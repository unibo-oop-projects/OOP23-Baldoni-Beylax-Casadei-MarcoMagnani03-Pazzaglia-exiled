package unibo.exiled.model.character.player;

import unibo.exiled.model.character.Character;
import unibo.exiled.model.item.Inventory;

/**
 * The interface of the Player.
 */
public interface Player extends Character{

    /**
     * @return the player health.
     */
    double getHealth();

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
}
