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
        double healthCap = player.getAttributes().get(AttributeIdentifier.HEALTHCAP).getValue().get();
        double health = player.getAttributes().get(AttributeIdentifier.HEALTHCAP).getValue().get();
        if(health+healingAmount > healthCap){
            player.getAttributes().get(AttributeIdentifier.HEALTH).setValue(healthCap);
        }
        else{
            player.getAttributes().get(AttributeIdentifier.HEALTH).setValue(health+healingAmount);
        }
    }



    @Override
    public double getAmount() {
        return this.healingAmount;
    }
}
