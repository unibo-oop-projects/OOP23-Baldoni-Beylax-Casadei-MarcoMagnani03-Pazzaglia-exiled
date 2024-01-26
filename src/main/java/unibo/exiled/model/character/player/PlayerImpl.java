package unibo.exiled.model.character.player;

import unibo.exiled.model.character.CharacterImpl;
import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.AttributeFactoryImpl;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.InventoryImpl;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.ItemFactory;
import unibo.exiled.model.item.ItemFactoryImpl;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.move.MoveSetImpl;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.Position;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This class represent the implementation of the player.
 */
public class PlayerImpl extends CharacterImpl implements Player {

    private double health;
    private final int levelInc;
    private int level;
    private double exp;

    private Position position;
    private final MoveSetImpl moveSet;
    private final Map<AttributeIdentifier, Attribute> attributes;
    private final Inventory inventory;
    private ElementalType playerClass;

    public PlayerImpl(final Position startingPosition, final double experience, final int levelIncrease,final int maxMovesNumber){
        super(List.of("player","boy_up","boy_down","boy_left","boy_right"));
        this.position = startingPosition;
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
        this.moveSet = new MoveSetImpl(maxMovesNumber);
        this.attributes = new AttributeFactoryImpl().createBasicAttributes();
        this.exp = experience;
        this.levelInc = levelIncrease;
    }

    @Override
    public void move(final Position newPosition) {
        position = newPosition;
    }
    public void move(final Direction direction) {
        final Position newPos = new Position(
                this.position.x() + direction.getPosition().x(),
                this.position.y() + direction.getPosition().y());
        this.move(newPos);
    }

    @Override
    public double getHealth() {
        return health;
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

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Map<AttributeIdentifier, Attribute> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    public void levelUp(){
        this.attributes.get(AttributeIdentifier.ATTACK)
        .setValue(this.attributes.get(AttributeIdentifier.ATTACK).getValue().get() + levelInc);
        this.attributes.get(AttributeIdentifier.DEFENSE)
        .setValue(this.attributes.get(AttributeIdentifier.DEFENSE).getModifier().get()+ levelInc / 10);
        this.attributes.get(AttributeIdentifier.HEALTHCAP)
        .setValue(this.attributes.get(AttributeIdentifier.HEALTHCAP).getValue().get() + levelInc);
        this.level += 1;
    }


    @Override
    public void setPlayerClass(ElementalType playerClass) {
        if(Objects.isNull(playerClass)){
            this.playerClass=playerClass;
        }
    }

    @Override
    public ElementalType getPlayerClass() {
        return this.playerClass;
    }
}
