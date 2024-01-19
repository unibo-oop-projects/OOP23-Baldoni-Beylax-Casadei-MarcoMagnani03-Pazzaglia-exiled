package unibo.exiled.model.item;

import java.util.Optional;

public class StandardItem extends ItemBase {

    public StandardItem(String name, String description) {
        super(name, description, Optional.empty(), ItemType.STANDARD);
    }
}
