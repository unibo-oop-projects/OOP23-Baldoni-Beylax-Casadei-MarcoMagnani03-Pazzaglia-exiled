package unibo.exiled.controller.player;

import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.Position;

public interface PlayerController {
    Player getPlayer();
    void movePlayer(final Direction dir, final GameMap map);
    //Attributes getter.
    double getHealth();
    int getLevel();
    ElementalType getPlayerClass();
    Position getPlayerPosition();
}
