package unibo.exiled.controller;

import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.utilities.Position;

public record MapController(GameMap map) {
    public boolean isInBoundaries(final Position pos) {
        return this.map.isInBoundaries(pos);
    }
}
