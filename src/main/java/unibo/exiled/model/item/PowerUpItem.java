package unibo.exiled.model.item;

public class PowerUpItem extends ItemBase implements MultipleUseItem{

    /*private final Statistic boostedStatistic es. attacco, difesa */

    private final int duration;

    public PowerUpItem(final String name,final String description,final double powerUpValue,final int duration) {
        super(name, description);
        this.duration=duration;
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyItem'");
    }

    @Override
    public int getDuration() {
        return this.duration;
    }
    
}
