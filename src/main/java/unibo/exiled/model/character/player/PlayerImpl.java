package unibo.exiled.model.character.player;

import unibo.exiled.model.character.attributes.AttributeFactoryImpl;
import unibo.exiled.model.character.attributes.AttributeType;
import unibo.exiled.model.character.attributes.Attributes;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.InventoryImpl;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.move.MoveSetImpl;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

/**
 * This class represent the implementation of the player.
 */
public class PlayerImpl implements Player {

    private double health;
    private final int levelInc;
    private int level;
    private double exp;

    private Position position;
    private final MoveSetImpl moveSet;
    private final Attributes attributes;
    private final Inventory inventory;

    public PlayerImpl(final Position startingPosition, final double experience, final int levelIncrease){
        this.position = startingPosition;
        this.inventory = new InventoryImpl();
        this.moveSet = new MoveSetImpl();
        this.attributes = new AttributeFactoryImpl().basicPlayerAttributes();
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
    public Attributes getAttributes() {
        return this.attributes;
    }

    public void levelUp(){
        this.attributes.getAttributeOfType(AttributeType.ATTACK)
        .setValue(this.attributes.getAttributeOfType(AttributeType.ATTACK).getValue()+levelInc);
        this.attributes.getAttributeOfType(AttributeType.DEFENSE)
        .setValue(this.attributes.getAttributeOfType(AttributeType.ATTACK).getValue()+levelInc);
        this.attributes.getAttributeOfType(AttributeType.HEALTHCAP)
        .setValue(this.attributes.getAttributeOfType(AttributeType.ATTACK).getValue()+levelInc);
        this.level += 1;
    }
}
