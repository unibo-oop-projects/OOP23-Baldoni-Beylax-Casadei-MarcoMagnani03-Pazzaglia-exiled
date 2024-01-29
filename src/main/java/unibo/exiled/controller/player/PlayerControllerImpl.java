package unibo.exiled.controller.player;

import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.model.utilities.Positions;

public class PlayerControllerImpl implements PlayerController{

    private final Player player;

    public PlayerControllerImpl(final Player player) {
        this.player = player;
    }
    
    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void movePlayer(final Direction dir, final GameMap map){
        final Position currentPlayerPosition = this.player.getPosition();
        if(map.isInBoundaries(Positions.sum(currentPlayerPosition, dir.getPosition()))){
            this.player.move(Positions.sum(currentPlayerPosition, dir.getPosition()));
        }
    }

    @Override
    public ElementalType getPlayerClass(){
        return this.player.getPlayerClass();
    }

    @Override
    public Position getPlayerPosition() {
        return this.player.getPosition();
    }

    @Override
    public double getHealth() {
        return player.getAttributes().get(AttributeIdentifier.HEALTH).getEvaluated();
    }

    @Override
    public int getLevel() {
        return player.getLevel();
    }
}
