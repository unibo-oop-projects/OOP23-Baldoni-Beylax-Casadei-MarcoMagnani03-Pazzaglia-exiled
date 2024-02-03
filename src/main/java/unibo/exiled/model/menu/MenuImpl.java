package unibo.exiled.model.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuImpl implements Menu {
    private final List<MenuItem> menuItems;

    public MenuImpl() {
        this.menuItems = new ArrayList<>();
    }

    @Override
    public void addMenuItem(final MenuItem menuItem) {
        this.menuItems.add(menuItem);
    }

    @Override
    public List<MenuItem> getMenuItems() {
        return Collections.unmodifiableList(this.menuItems);
    }
}
