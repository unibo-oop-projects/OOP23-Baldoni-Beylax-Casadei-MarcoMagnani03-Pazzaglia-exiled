package unibo.exiled.controller;

import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

public class PlayerController {
    private Player player;
    
    public PlayerController(Player player){
        this.player = player;
    }

    private boolean canMove(){
        return false; // TODO
    }

    public void move(Direction direction){
        // TODO
    }

    public Position getPlayerPosition(){
        return player.getPosition();
    }

}
