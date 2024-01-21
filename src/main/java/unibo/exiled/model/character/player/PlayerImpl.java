package unibo.exiled.model.character.player;

import java.util.HashSet;
import java.util.Set;

import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.InventoryImpl;
import unibo.exiled.model.item.ItemFactoryImpl;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

/**
 * This class represent the implementation of the player.
 */
public class PlayerImpl implements Player {

    private final Inventory inventory;
    private double health;
    private Set<MagicMove> moveSet;
    private Position position;

    public PlayerImpl(){
        this.inventory = new InventoryImpl();
        //Aggiunti degli item per test
        ItemFactoryImpl factoryItem= new ItemFactoryImpl();
        inventory.addItem(factoryItem.createHealingItem("Healing Potion", "A potion that gives healing to the player", 20));
        inventory.addItem(factoryItem.createHealingItem("Healing Potion", "A potion that gives healing to the player", 20));
        inventory.addItem(factoryItem.createPowerUpItem("Power up potion", "A potion that gives a power up to the player", 20,2));
        inventory.addItem(factoryItem.createPowerUpItem("Power up potion", "A potion that gives a power up to the player", 20,2));
        inventory.addItem(factoryItem.createUnUsableItem("Redemption crystal", "A crystal"));
        inventory.addItem(factoryItem.createUnUsableItem("Redemption crystal", "A crystal"));
        this.health = 100.0;
        this.moveSet = new HashSet<>();
        this.position = new Position(3, 3); // Starting position of the player
    }

    @Override
    public MoveSet getMoveSet() {
        throw new IllegalStateException(); // TODO
    }

    @Override
    public void move(Direction direction) {
        Position positionSpanDirection = direction.getPosition();
        position = new Position(position.x()+positionSpanDirection.x(), position.y()+positionSpanDirection.y());
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }
}
