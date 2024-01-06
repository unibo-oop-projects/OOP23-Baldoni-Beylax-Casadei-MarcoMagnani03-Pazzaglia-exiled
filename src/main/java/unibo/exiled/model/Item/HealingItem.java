package unibo.exiled.model.Item;

public class HealingItem extends ItemBase implements UsableItem{

    private final double healingAmount;

    public HealingItem(String name, String description,double healingAmount) {
        super(name, description);
        this.healingAmount=healingAmount;
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyItem'");
    }
    
}
