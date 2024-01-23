package unibo.exiled.model.character.player;

import unibo.exiled.config.Constants;
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

    private final int levelInc;
    private int level;
    private double exp;

    private Position position;
    private final MoveSetImpl moveSet;
    private final Attributes attributes;
    private final Inventory inventory;

    public PlayerImpl(){
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        this.position = new Position((int)Constants.getConstantOf("PLAYER_STARTING_POSITION_X"), (int)(Constants.getConstantOf("PLAYER_STARTING_POSITION_Y")));
        this.inventory = new InventoryImpl();
        this.moveSet = new MoveSetImpl();
        this.attributes = new AttributeFactoryImpl().basicPlayerAttributes();
        this.exp = Constants.getConstantOf("PLAYER_DEFAULT_EXPERIENCE");
        this.levelInc = (int)Constants.getConstantOf("PLAYER_LEVEL_INCREASE");
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
        .setValue(this.attributes.getAttributeOfType(AttributeType.ATTACK).getValue()+levelInc);
        this.attributes.getAttributeOfType(AttributeType.DEFENSE)
        .setValue(this.attributes.getAttributeOfType(AttributeType.ATTACK).getValue()+levelInc);
        this.attributes.getAttributeOfType(AttributeType.HEALTHCAP)
        .setValue(this.attributes.getAttributeOfType(AttributeType.ATTACK).getValue()+levelInc);
        this.level += 1;
    }
}
