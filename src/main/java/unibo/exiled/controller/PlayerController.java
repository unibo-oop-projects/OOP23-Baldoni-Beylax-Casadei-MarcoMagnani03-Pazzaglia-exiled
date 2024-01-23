package unibo.exiled.controller;

import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.utilities.Direction;

public class PlayerController {
    private final Player player;

    public PlayerController(final Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return  this.player;
    }

    public void move(Direction direction){
        player.move(direction);
    }
}
