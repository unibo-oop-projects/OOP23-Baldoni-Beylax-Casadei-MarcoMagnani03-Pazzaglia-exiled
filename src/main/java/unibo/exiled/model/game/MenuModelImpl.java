package unibo.exiled.model.game;

import unibo.exiled.model.menu.Command;
import unibo.exiled.model.menu.Menu;
import unibo.exiled.model.menu.MenuImpl;
import unibo.exiled.model.menu.MenuItem;

public class MenuModelImpl implements MenuModel {
    private final Menu inGameMenu;
    private final Menu newGameMenu;

    public MenuModelImpl() {
        this.inGameMenu = new MenuImpl();
        this.newGameMenu = new MenuImpl();

        this.inGameMenu.addMenuItem(new MenuItem("RESUME", Command.CLOSE_MENU));
        this.inGameMenu.addMenuItem(new MenuItem("NEW GAME", Command.NEW_GAME));
        this.inGameMenu.addMenuItem(new MenuItem("QUIT", Command.QUIT));

        this.newGameMenu.addMenuItem(new MenuItem("NEW GAME", Command.NEW_GAME));
        this.newGameMenu.addMenuItem(new MenuItem("QUIT", Command.QUIT));
    }

    @Override
    public Menu getNewGameMenu() {
        return this.newGameMenu;
    }

    @Override
    public Menu getInGameMenu() {
        return this.inGameMenu;
    }
}
