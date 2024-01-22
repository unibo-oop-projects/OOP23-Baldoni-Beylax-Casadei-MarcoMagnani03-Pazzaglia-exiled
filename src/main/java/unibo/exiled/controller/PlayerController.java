package unibo.exiled.controller;

import unibo.exiled.model.character.attributes.AttributeType;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.character.player.PlayerImpl;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

public class PlayerController {
    private final Player player;
    
    public PlayerController(){
        this.player = new PlayerImpl();
    }

    public void move(Direction direction){
        player.move(direction);
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
