package unibo.exiled.controller.player;

import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.Position;

public interface PlayerController {
    Player getPlayer();
    void movePlayer(final Direction dir);
    //Attributes getter.
    double getHealth();
    int getLevel();
    ElementalType getPlayerClass();
    Position getPlayerPosition();
}
