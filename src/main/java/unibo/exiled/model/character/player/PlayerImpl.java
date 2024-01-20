package unibo.exiled.model.character.player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import unibo.exiled.controller.InventoryController;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.InventoryImpl;
import unibo.exiled.model.item.UsableItem;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Pair;
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
        this.health = 100.0;
        this.moveSet = new HashSet<>();
        this.position = new Position(3, 3); // Starting position of the player
    }

    @Override
    public MoveSet getMoveSet() {
        throw new IllegalStateException();
    }

    @Override
    public void move(Direction direction) {
        Position positionSpanDirection = direction.getPosition();
        position = new Position(position.x()+positionSpanDirection.x(), position.y()+positionSpanDirection.y());
    }
    
    @Override
    public void useItem(UsableItem selectedItem) {
        InventoryController ic = new InventoryController(inventory);
        ic.useItem(selectedItem);
    }
}
