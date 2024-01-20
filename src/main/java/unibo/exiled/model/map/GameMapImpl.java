package unibo.exiled.model.map;

public class GameMapImpl implements  GameMap{
    private final int height;
    private final int width;

    public GameMapImpl(final int size){
        if(size % 2 == 0) {
            this.height = size;
            this.width = size;
        }
        else{
            throw new IllegalArgumentException("The size of the map should be an even number.");
        }
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight(){
        return  this.height;
    }
}
