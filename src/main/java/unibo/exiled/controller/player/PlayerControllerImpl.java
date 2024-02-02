package unibo.exiled.controller.player;

import unibo.exiled.model.GameModel;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.Position;

public record PlayerControllerImpl(GameModel model) implements PlayerController {

    @Override
    public void movePlayer(final Direction dir) {
        model.movePlayer(dir);
    }

    @Override
    public ElementalType getPlayerClass() {
        return model.getPlayer().getPlayerClass();
    }

    @Override
    public Position getPlayerPosition() {
        return model.getPlayer().getPosition();
    }

    @Override
    public double getHealth() {
        return model.getPlayer().getHealth();
    }

    @Override
    public int getLevel() {
        return model.getPlayer().getLevel();
    }
}
