package unibo.exiled.controller;

import java.util.List;

import unibo.exiled.model.menu.Menu;
import unibo.exiled.model.menu.MenuItem;

public class MenuController {
    private Menu menu;

    public MenuController(final Menu menu) {
        this.menu = menu;
    }

    public List<MenuItem> getMenuItems() {
        return menu.getMenuItems();
    }
}
