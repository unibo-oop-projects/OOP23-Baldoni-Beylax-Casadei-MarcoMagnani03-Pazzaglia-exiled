package unibo.exiled.controller.menu;

import java.util.List;

import unibo.exiled.model.menu.Command;
import unibo.exiled.model.menu.Menu;
import unibo.exiled.model.menu.MenuImpl;
import unibo.exiled.model.menu.MenuItem;

public class InGameMenuController implements MenuController {
    final private Menu menu;

    public InGameMenuController() {
        this.menu = new MenuImpl();

        this.menu.addMenuItem(new MenuItem("CLOSE MENU", Command.CLOSE_MENU));
        this.menu.addMenuItem(new MenuItem("QUIT", Command.QUIT));
    }

    @Override
    public List<MenuItem> getMenuItems(){
        return this.menu.getMenuItems();
    }
}
