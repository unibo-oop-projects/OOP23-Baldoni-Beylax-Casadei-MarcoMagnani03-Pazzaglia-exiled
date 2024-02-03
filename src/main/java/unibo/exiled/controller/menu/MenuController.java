package unibo.exiled.controller.menu;

import java.util.List;

import unibo.exiled.model.menu.MenuItem;

/**
 * Interface for controllers managing menus.
 */
public interface MenuController {

    /**
     * Retrieves the list of menu items.
     *
     * @return The list of menu items.
     */
    List<MenuItem> getMenuItems();
}
