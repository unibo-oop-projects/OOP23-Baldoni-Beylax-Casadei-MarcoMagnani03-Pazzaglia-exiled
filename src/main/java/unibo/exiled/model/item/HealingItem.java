package unibo.exiled.model.item;

import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.utilities.ItemType;

/**
 * This class represents a usable healing item, 
 * the use class allows the character to recover a certain amount of missing life, 
 * the amount of healing is determined by the healing value.
 */
public class HealingItem extends ItemBase implements UsableItem{
    double healingAmount;
    public HealingItem(String name, String description,double healingAmount) {
        super(name, description,ItemType.HEALTH);
        this.healingAmount=healingAmount;
    }

    @Override
    public void use(Player player) {
        player.getAttributes().get(AttributeIdentifier.HEALTH).setValue(player.getAttributes().get(AttributeIdentifier.HEALTH).getValue().get()+healingAmount);
    }



    @Override
    public double getAmount() {
        return this.healingAmount;
    }
}
