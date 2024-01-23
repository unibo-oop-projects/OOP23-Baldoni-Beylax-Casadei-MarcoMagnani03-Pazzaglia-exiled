package unibo.exiled.controller;

import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

public class PlayerController implements CharacterController{
    private final Player player;

    public PlayerController(final Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void move(final Position position) {
        this.player.move(position);
    }
}
