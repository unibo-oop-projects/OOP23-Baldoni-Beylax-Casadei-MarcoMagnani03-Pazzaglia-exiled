package unibo.exiled.model.character.player;

import unibo.exiled.model.character.attributes.AttributeFactoryImpl;
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
    private Position position;
    private final Attributes attributes = AttributeFactoryImpl.basicAttributes();

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
        return this.level;
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
        this.level += 1;
    }
}
