package unibo.exiled.controller.menu;

import java.util.List;

import unibo.exiled.model.menu.Command;
import unibo.exiled.model.menu.Menu;
import unibo.exiled.model.menu.MenuImpl;
import unibo.exiled.model.menu.MenuItem;

public class StartMenuController implements MenuController {
    private Menu menu;

    public StartMenuController() {
        this.menu = new MenuImpl();

        this.menu.addMenuItem(new MenuItem("NEW GAME", Command.NEW_GAME));
        this.menu.addMenuItem(new MenuItem("QUIT", Command.QUIT));
    }

    public List<MenuItem> getMenuItems(){
        return this.menu.getMenuItems();
    }
}
