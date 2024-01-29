package unibo.exiled.model.utilities;

public class Positions {
    public static Position sum(final Position pos1, final Position pos2){
        return new Position(pos1.x() + pos2.x(), pos1.y() + pos2.y());
    }
}
