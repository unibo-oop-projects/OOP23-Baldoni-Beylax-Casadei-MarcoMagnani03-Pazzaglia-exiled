package unibo.exiled.controller;

import java.util.List;

import unibo.exiled.model.menu.Menu;
import unibo.exiled.model.menu.MenuItem;

public record MenuController(Menu menu) {
    public List<MenuItem> getMenuItems() {
        return menu.getMenuItems();
    }
}
