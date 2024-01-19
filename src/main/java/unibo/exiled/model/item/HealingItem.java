package unibo.exiled.model.item;

import java.util.Optional;

public class HealingItem extends ItemBase implements UsableItem{

    public HealingItem(String name, String description,Double healingAmount) {
        super(name, description);
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyItem'");
    }
    
}
