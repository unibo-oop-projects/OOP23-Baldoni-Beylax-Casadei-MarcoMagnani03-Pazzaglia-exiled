package unibo.exiled.model.character.player;

import unibo.exiled.model.character.CharacterImpl;
import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.AttributeFactoryImpl;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.attributes.AttributeImpl;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.InventoryImpl;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.ItemFactory;
import unibo.exiled.model.item.ItemFactoryImpl;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.move.MoveSetFactoryImpl;
import unibo.exiled.model.move.MoveSetImpl;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.Position;

import java.util.*;

/**
 * This class represent the implementation of the player.
 */
public class PlayerImpl extends CharacterImpl implements Player {

    private final int levelInc;
    private int level;
    private double exp;
    private final Inventory inventory;
    private final MoveSet moveSet;

    private ElementalType playerClass;

    public PlayerImpl(final double experience, final int levelIncrease) {
        super(new AttributeFactoryImpl().createPlayerAttributes(),List.of("player", "boy_up", "boy_down", "boy_left", "boy_right"));

        this.inventory = new InventoryImpl();
        ItemFactory itemFactory = new ItemFactoryImpl();
        Item healingItem = itemFactory.createHealingItem("Health Potion", "Restores health", 50);
        this.inventory.addItem(healingItem);
        Item powerUpItem = itemFactory.createPowerUpItem("Strength Elixir", "Boosts attack", 10, 5, AttributeIdentifier.ATTACK);
        Item powerUpItem1 = itemFactory.createPowerUpItem("Strength Elixir", "Boosts attack", 10, 5, AttributeIdentifier.ATTACK);
        Item powerUpItemDefence = itemFactory.createPowerUpItem("Strength Elixir", "Boosts attack", 10, 5, AttributeIdentifier.DEFENSE);
        this.inventory.addItem(powerUpItem);
        this.inventory.addItem(powerUpItem1);
        this.inventory.addItem(powerUpItemDefence);
        this.moveSet = new MoveSetFactoryImpl().defaultBasicMoveSet();
        this.exp = experience;
        this.levelInc = levelIncrease;
    }

    @Override
    public MoveSet getMoveSet() {
        return moveSet;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public double getExperience() {
        return this.exp;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    public void levelUp(){
        this.increaseAttributeModifierBy(AttributeIdentifier.ATTACK,levelInc / 10);
        this.increaseAttributeValue(AttributeIdentifier.HEALTH,levelInc);
        this.increaseAttributeValue(AttributeIdentifier.HEALTHCAP,levelInc);
        this.increaseAttributeValue(AttributeIdentifier.DEFENSE,levelInc);
    }


    @Override
    public void setPlayerClass(ElementalType playerClass) {
        if(!Objects.isNull(playerClass)){
            this.playerClass=playerClass;
        }
    }

    @Override
    public ElementalType getPlayerClass() {
        return this.playerClass;
    }
}
