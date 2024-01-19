package unibo.exiled.model.map;

public class GameMapImpl implements  GameMap{
    private final int size;

    public GameMapImpl(final int size){
        if(size % 2 == 0) {
            this.size = size;
        }
        else{
            throw new IllegalArgumentException("The size of the map should be an even number.");
        }
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
