package unibo.exiled.controller.menu;

import java.util.List;

import unibo.exiled.model.menu.Command;
import unibo.exiled.model.menu.Menu;
import unibo.exiled.model.menu.MenuImpl;
import unibo.exiled.model.menu.MenuItem;

/**
 * Controller for managing the start menu.
 */
public class StartMenuController implements MenuController {

    private final Menu menu;

    /**
     * Constructs a StartMenuController and initializes the menu with "NEW GAME" and "QUIT" options.
     */
    public StartMenuController() {
        this.menu = new MenuImpl();
        this.menu.addMenuItem(new MenuItem("NEW GAME", Command.NEW_GAME));
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
