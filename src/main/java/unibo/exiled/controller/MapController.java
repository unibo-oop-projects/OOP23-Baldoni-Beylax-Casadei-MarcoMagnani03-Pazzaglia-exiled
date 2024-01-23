package unibo.exiled.controller;

import unibo.exiled.model.map.GameMap;

public class MapController {
    private final GameMap map;
    public MapController(final GameMap map){
        this.map = map;
    }
    public GameMap getMap(){
        return  this.map;
    }
}
