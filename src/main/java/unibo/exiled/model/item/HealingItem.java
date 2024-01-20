package unibo.exiled.model.item;

/**
 * This class represents a usable healing item, 
 * the use class allows the character to recover a certain amount of missing life, 
 * the amount of healing is determined by the healing value.
 */
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
