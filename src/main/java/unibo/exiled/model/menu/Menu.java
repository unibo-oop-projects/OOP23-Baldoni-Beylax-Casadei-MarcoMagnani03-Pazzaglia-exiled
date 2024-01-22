package unibo.exiled.model.menu;

import java.util.List;

public interface Menu {
    void addMenuItem(MenuItem menuItem);
    List<MenuItem> getMenuItems();
}
