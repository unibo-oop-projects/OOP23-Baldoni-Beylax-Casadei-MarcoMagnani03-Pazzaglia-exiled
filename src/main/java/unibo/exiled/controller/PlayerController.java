package unibo.exiled.controller;

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
        return player.getAttributes().getHealth();
    }

    public double getAttack(){
        return player.getAttributes().getAttack();
    }
    public double getDefense(){
        return player.getAttributes().getDefense();
    }
    public Inventory getInventory(){
        return player.getInventory();
    }
}
