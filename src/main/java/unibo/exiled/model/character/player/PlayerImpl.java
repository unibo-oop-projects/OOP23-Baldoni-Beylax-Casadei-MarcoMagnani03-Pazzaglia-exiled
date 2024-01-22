package unibo.exiled.model.character.player;

import unibo.exiled.model.character.attributes.AttributeFactoryImpl;
import unibo.exiled.model.character.attributes.AttributeType;
import unibo.exiled.model.character.attributes.Attributes;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.InventoryImpl;
import unibo.exiled.model.item.ItemFactoryImpl;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.move.MoveSetImpl;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

import java.util.Collections;

/**
 * This class represent the implementation of the player.
 */
public class PlayerImpl implements Player {

    private final Inventory inventory;
    private final MoveSetImpl moveSet;
    private int level;
    private double exp;
    private Position position = new Position(10,10);
    private final Attributes attributes = AttributeFactoryImpl.basicAttributes();
    private final static int ATTRIBUTE_LEVEL_INCREMENTATION=5;

    public PlayerImpl(){
        this.inventory = new InventoryImpl();
        this.moveSet = new MoveSetImpl();
    }

    @Override
    public void move(Direction direction) {
        Position positionSpanDirection = direction.getPosition();
        position = new Position(position.x()+positionSpanDirection.x(), position.y()+positionSpanDirection.y());
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
    public Attributes getAttributes() {
        return this.attributes;
    }

    public void levelUp(){
        this.attributes.getAttributeOfType(AttributeType.ATTACK)
        .setValue(this.attributes.getAttributeOfType(AttributeType.ATTACK).getValue()+ATTRIBUTE_LEVEL_INCREMENTATION);
        this.attributes.getAttributeOfType(AttributeType.DEFENSE)
        .setValue(this.attributes.getAttributeOfType(AttributeType.ATTACK).getValue()+ATTRIBUTE_LEVEL_INCREMENTATION);
        this.attributes.getAttributeOfType(AttributeType.HEALTHCAP)
        .setValue(this.attributes.getAttributeOfType(AttributeType.ATTACK).getValue()+ATTRIBUTE_LEVEL_INCREMENTATION);
        this.level += 1;
    }
}
