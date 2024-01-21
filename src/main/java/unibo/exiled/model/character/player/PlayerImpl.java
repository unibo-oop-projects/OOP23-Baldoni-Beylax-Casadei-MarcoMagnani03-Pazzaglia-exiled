package unibo.exiled.model.character.player;

import unibo.exiled.model.character.Attributes;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.InventoryImpl;
import unibo.exiled.model.item.ItemFactoryImpl;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.move.MoveSetImpl;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

/**
 * This class represent the implementation of the player.
 */
public class PlayerImpl implements Player {

    private static final double STARTING_HEALTH = 100.0;
    private static final Position STARTING_POSITION = new Position(3, 3);

    private final Inventory inventory;
    private final MoveSetImpl moveSet;
    private PlayerAttribute attributes;
    private int level;
    private double exp;
    private Position position;
    

    public PlayerImpl(){
        this.inventory = new InventoryImpl();
        ItemFactoryImpl factoryItem= new ItemFactoryImpl();
        inventory.addItem(factoryItem.createHealingItem("Healing Potion", "A potion that gives healing to the player", 20));
        inventory.addItem(factoryItem.createHealingItem("Healing Potion", "A potion that gives healing to the player", 20));
        inventory.addItem(factoryItem.createPowerUpItem("Power up potion", "A potion that gives a power up to the player", 20,2));
        inventory.addItem(factoryItem.createPowerUpItem("Power up potion", "A potion that gives a power up to the player", 20,2));
        inventory.addItem(factoryItem.createUnUsableItem("Redemption crystal", "A crystal"));
        inventory.addItem(factoryItem.createUnUsableItem("Redemption crystal", "A crystal"));
        this.attributes = new PlayerAttribute(STARTING_HEALTH);
        this.moveSet = new MoveSetImpl();
        this.position = STARTING_POSITION;
    }

    @Override
    public void move(Direction direction) {
        Position positionSpanDirection = direction.getPosition();
        position = new Position(position.x()+positionSpanDirection.x(), position.y()+positionSpanDirection.y());
    }
    
    //
    // GETTER
    //

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
        return null;
    }


}
