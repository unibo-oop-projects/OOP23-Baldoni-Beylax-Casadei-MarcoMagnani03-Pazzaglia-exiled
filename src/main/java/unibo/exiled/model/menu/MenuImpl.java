package unibo.exiled.model.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuImpl implements Menu {
    private List<MenuItem> menuItems;

    public MenuImpl() {
        this.menuItems = new ArrayList<>();
    }

    public void addMenuItem(MenuItem menuItem) {
        this.menuItems.add(menuItem);
    }

    public void removeMenuItem(MenuItem menuItem){
        this.menuItems.remove(menuItem);
    }

    public List<MenuItem> getMenuItems() {
        return this.menuItems;
    }
}
