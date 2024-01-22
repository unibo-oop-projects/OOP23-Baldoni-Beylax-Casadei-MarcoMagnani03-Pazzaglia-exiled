package unibo.exiled.controller;

import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.character.player.PlayerImpl;
import unibo.exiled.model.map.CellType;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.map.GameMapImpl;
import unibo.exiled.model.utilities.Position;

public class Controller {
    private final GameMap map;

    public Controller(final int mapSize) {
        this.map = new GameMapImpl(mapSize);
    }

    public int getMapHeight(){
        return this.map.getHeight();
    }
    public int getMapWidth() {
        return this.map.getWidth();
    }

    public CellType getCellType(final Position cell){
        return map.getCellType(cell);
    }
}
