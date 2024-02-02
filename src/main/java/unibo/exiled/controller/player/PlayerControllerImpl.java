package unibo.exiled.controller.player;

import unibo.exiled.model.GameModel;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.attributes.CombinedAttributeImpl;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.model.utilities.Positions;

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
