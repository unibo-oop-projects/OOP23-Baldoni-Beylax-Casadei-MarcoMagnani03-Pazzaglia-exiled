package unibo.exiled.controller;

import java.util.List;

import unibo.exiled.model.menu.MenuItem;

public interface MenuController {
    /**
     * Gets the new game menu items.
     * 
     * @return the menu items in new game menu.
     */
    List<MenuItem> getNewGameMenuItems();

    /**
     * Gets the in game menu items.
     * 
     * @return the menu items in in game menu.
     */
    List<MenuItem> getInGameMenuItems();
}
