package unibo.exiled.controller;

import java.util.List;

import unibo.exiled.model.menu.Menu;
import unibo.exiled.model.menu.MenuImpl;
import unibo.exiled.model.menu.MenuItem;
import unibo.exiled.model.menu.Command;

public class MenuController {
    private Menu menu;

    private final int STANDARD_MENU_ITEM_WIDTH = 250;
    private final int STANDARD_MENU_ITEM_HEIGHT = 50;

    public MenuController() {
        this.menu = new MenuImpl();

        this.menu.addMenuItem(new MenuItem(STANDARD_MENU_ITEM_WIDTH, STANDARD_MENU_ITEM_HEIGHT, "NEW GAME", Command.NEW_GAME));
        this.menu.addMenuItem(new MenuItem(STANDARD_MENU_ITEM_WIDTH, STANDARD_MENU_ITEM_HEIGHT, "QUIT", Command.QUIT));
    }

    public List<MenuItem> getMenuItems() {
        return menu.getMenuItems();
    }
}
