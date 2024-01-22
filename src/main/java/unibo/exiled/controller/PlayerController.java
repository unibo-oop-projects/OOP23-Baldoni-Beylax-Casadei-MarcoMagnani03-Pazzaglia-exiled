package unibo.exiled.controller;

import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.character.player.PlayerImpl;
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

    public Player getPlayer(){
        return player;
    }

}
