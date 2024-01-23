package unibo.exiled.controller;

import unibo.exiled.model.GameModel;
import unibo.exiled.model.character.attributes.AttributeType;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.character.player.PlayerImpl;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

public class PlayerController implements CharacterController{
    private final GameModel gameModel;
    private final Player player;
    private final GameMap map;
    
    public PlayerController(GameModel gameModel){
        this.player = gameModel.getPlayer();
        this.map = gameModel.getMap();
    }

    @Override
    public void move(final Direction direction){
        Position positionSpanDirection = direction.getPosition();
        Position newPosition = new Position(player.getPosition().x()+positionSpanDirection.x(), player.getPosition().y()+positionSpanDirection.y());
        if(map.isInBoundaries(newPosition)){
            player.move(newPosition);
        }
    }

    public Position getPlayerPosition(){
        return player.getPosition();
    }
    public int getLevel(){
        return player.getLevel();
    }

    public double getHealth(){
        return player.getAttributes().getAttributeOfType(AttributeType.HEALTH).getValue();
    }

    public double getAttack(){
        return player.getAttributes().getAttributeOfType(AttributeType.ATTACK).getValue();
    }
    public double getDefense(){
        return player.getAttributes().getAttributeOfType(AttributeType.DEFENSE).getValue();
    }
    public double getHealthCap(){
        return player.getAttributes().getAttributeOfType(AttributeType.HEALTHCAP).getValue();
    }
    public Inventory getInventory(){
        return player.getInventory();
    }
}
