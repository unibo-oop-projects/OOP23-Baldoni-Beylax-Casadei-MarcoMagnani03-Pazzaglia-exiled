package unibo.exiled.model.character.player;

import unibo.exiled.model.character.CharacterImpl;
import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.AttributeFactoryImpl;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.attributes.AttributeImpl;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.InventoryImpl;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.ItemContainer;
import unibo.exiled.model.item.ItemContainer;
import unibo.exiled.model.item.ItemFactory;
import unibo.exiled.model.item.ItemFactoryImpl;
import unibo.exiled.model.item.ItemNames;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.move.MoveSetFactoryImpl;
import unibo.exiled.model.move.MoveSetImpl;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.ItemType;
import unibo.exiled.model.utilities.Position;

import java.util.*;

/**
 * This class represent the implementation of the player.
 */
public class PlayerImpl extends CharacterImpl implements Player {

    private final int levelInc;
    private int level;
    private double currentExp;
    private double expCap;
    private final Inventory inventory;
    private final MoveSet moveSet;
    private ElementalType playerClass;

    public PlayerImpl(final double experienceCap, final double initialExperience, final int levelIncrease) {
        super(new AttributeFactoryImpl().createPlayerAttributes(), List.of("player", "boy_up", "boy_down", "boy_left", "boy_right"));
        this.inventory = initializeInventory();
        this.moveSet = new MoveSetFactoryImpl().defaultNormalMoveSet();
        this.expCap = experienceCap;
        this.currentExp = initialExperience;
        this.levelInc = levelIncrease;
    }

    // This method is used for testing purposes only.
    private Inventory initializeInventory() {
        Inventory inventory = new InventoryImpl();
        Item healingItem = ItemContainer.getItemByName(ItemNames.HEALTH_POTION.getName()).get();
        inventory.addItem(healingItem);
        Item healingItem1 = ItemContainer.getItemByName(ItemNames.HEALTH_POTION.getName()).get();
        inventory.addItem(healingItem1);
        Item powerUpItem = ItemContainer.getRandomItemByType(ItemType.HEALTH).get();
        inventory.addItem(powerUpItem);
        Item powerUpItem1 = ItemContainer.getRandomItemByType(ItemType.POWERUP).get();
        inventory.addItem(powerUpItem1);
        Item powerUpItemDefence = ItemContainer.getRandomItemByType(ItemType.RESOURCE).get();
        inventory.addItem(powerUpItemDefence);
        return inventory;
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
        return this.currentExp;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public void levelUp() {
        if (checkPossibleLevelUp()) {
            performLevelUp();
        }
    }

    private boolean checkPossibleLevelUp() {
        if (currentExp >= expCap) {
            this.expCap = this.expCap + this.expCap / 20 * 100;
            this.currentExp -= this.expCap - currentExp;
            return true;
        } else {
            return false;
        }
    }

    private void performLevelUp() {
        this.increaseAttributeModifierBy(AttributeIdentifier.ATTACK, levelInc / 10);
        this.increaseAttributeValue(AttributeIdentifier.HEALTH, levelInc);
        this.increaseAttributeValue(AttributeIdentifier.HEALTHCAP, levelInc);
        this.increaseAttributeValue(AttributeIdentifier.DEFENSE, levelInc);
    }

    @Override
    public void setPlayerClass(ElementalType playerClass) {
        if (!Objects.isNull(playerClass)) {
            this.playerClass = playerClass;
        }
    }

    @Override
    public ElementalType getPlayerClass() {
        return this.playerClass;
    }
}

