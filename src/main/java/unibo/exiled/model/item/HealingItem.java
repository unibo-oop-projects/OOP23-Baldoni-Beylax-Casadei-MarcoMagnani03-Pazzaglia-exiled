package unibo.exiled.model.item;

import unibo.exiled.model.character.player.Player;

/**
 * This class represents a usable healing item,
 * the use class allows the character to recover a certain amount of missing life,
 * the amount of healing is determined by the healing value.
 */
public final class HealingItem extends ItemBase implements UsableItem {
    private final double healingAmount;

    /**
     * The constructor of the healing item.
     *
     * @param name          The name of the item.
     * @param description   The description of the item.
     * @param healingAmount The amount the item heals.
     */
    public HealingItem(final String name, final String description, final double healingAmount) {
        super(name, description, ItemType.HEALTH);
        this.healingAmount = healingAmount;
    }

    @Override
    public void use(final Player player) {
        // double healthCap = ((AdditiveAttributeImpl)player.getAttributes().get(AttributeIdentifier.HEALTHCAP)).value();
        // double health = ((AdditiveAttributeImpl)player.getAttributes().get(AttributeIdentifier.HEALTHCAP)).value();

        /*if(health+healingAmount > healthCap){
            player.getAttributes().get(AttributeIdentifier.HEALTH).setValue(healthCap);
        }
        else{
            player.getAttributes().get(AttributeIdentifier.HEALTH).setValue(health+healingAmount);
        }*/
    }


    @Override
    public double getAmount() {
        return this.healingAmount;
    }
}
