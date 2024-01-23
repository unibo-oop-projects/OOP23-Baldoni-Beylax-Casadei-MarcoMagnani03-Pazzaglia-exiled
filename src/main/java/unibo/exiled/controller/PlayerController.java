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
    public void move(final Direction direction){
        Position positionSpanDirection = direction.getPosition();
        Position newPosition = new Position(
                player.getPosition().x()+positionSpanDirection.x(),
                player.getPosition().y()+positionSpanDirection.y());

        this.player.move(newPosition);
    }
}
