package unibo.exiled.controller;

import java.util.List;

import unibo.exiled.model.game.MenuModel;
import unibo.exiled.model.game.MenuModelImpl;
import unibo.exiled.model.menu.MenuItem;

public class MenuControllerImpl implements MenuController {
    private final MenuModel menuModel;

    public MenuControllerImpl() {
        this.menuModel = new MenuModelImpl();
    }

    @Override
    public List<MenuItem> getNewGameMenuItems() {
        return this.menuModel.getNewGameMenu().getMenuItems();
    }

    @Override
    public List<MenuItem> getInGameMenuItems() {
        return this.menuModel.getInGameMenu().getMenuItems();
    }
}
