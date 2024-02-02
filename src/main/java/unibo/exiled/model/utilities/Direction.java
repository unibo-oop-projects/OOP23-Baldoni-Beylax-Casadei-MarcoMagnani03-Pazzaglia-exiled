package unibo.exiled.model.utilities;

/**
 * The direction used to move to a different position.
 */
public enum Direction {
    NORTH(0, -1), SOUTH(0, 1), WEST(-1, 0), EAST(1, 0);

    private final Position position;

    Direction(int x, int y) {
        position = new Position(x, y);
    }

    public Position getPosition() {
        return this.position;
    }
}
