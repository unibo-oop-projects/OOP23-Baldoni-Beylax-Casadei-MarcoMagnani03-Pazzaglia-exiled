package unibo.exiled.controller.menu;

import java.util.List;

import unibo.exiled.model.menu.Command;
import unibo.exiled.model.menu.Menu;
import unibo.exiled.model.menu.MenuImpl;
import unibo.exiled.model.menu.MenuItem;

/**
 * Controller class for managing the in-game menu.
 */
public class InGameMenuController implements MenuController {
    private final Menu menu;

    /**
     * Constructs an InGameMenuController and initializes the menu with default
     * items.
     */
    public InGameMenuController() {
        this.menu = new MenuImpl();

        this.menu.addMenuItem(new MenuItem("CLOSE MENU", Command.CLOSE_MENU));
        this.menu.addMenuItem(new MenuItem("QUIT", Command.QUIT));
    }

    /**
     * Retrieves the list of menu items.
     *
     * @return The list of menu items.
     */
    @Override
    public List<MenuItem> getMenuItems() {
        return this.menu.getMenuItems();
    }
}
