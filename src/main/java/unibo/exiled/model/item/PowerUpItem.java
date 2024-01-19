package unibo.exiled.model.item;

import java.util.Optional;

public class PowerUpItem extends ItemBase implements UsableItem{

    /*private final Statistic boostedStatistic es. attacco, difesa */

    public PowerUpItem(String name, String description, Double powerUpValue) {
        super(name, description, Optional.of(powerUpValue),ItemType.POWERUP);
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyItem'");
    }
    
}
