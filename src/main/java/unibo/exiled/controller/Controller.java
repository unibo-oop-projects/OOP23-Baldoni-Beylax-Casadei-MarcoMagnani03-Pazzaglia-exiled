package unibo.exiled.controller;

import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.map.GameMapImpl;

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
}
