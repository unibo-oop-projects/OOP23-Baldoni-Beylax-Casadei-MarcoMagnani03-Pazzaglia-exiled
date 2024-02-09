package unibo.exiled.model.game;

import unibo.exiled.model.menu.Menu;

public interface MenuModel {
    /**
     * Gets the new game menu.
     * 
     * @return the new game menu.
     */
    Menu getNewGameMenu();

    /**
     * Gest the in game menu.
     * 
     * @return the in game menu.
     */
    Menu getInGameMenu();
}