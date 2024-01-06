package unibo.exiled.model.Item;

public class BoostItem extends ItemBase implements UsableItem{

    private final double boostValue;
    /*private final Statistic boostedStatistic es. attacco, difesa */

    public BoostItem(String name, String description, double boostValue) {
        super(name, description);
        this.boostValue = boostValue;
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyItem'");
    }
    
}
