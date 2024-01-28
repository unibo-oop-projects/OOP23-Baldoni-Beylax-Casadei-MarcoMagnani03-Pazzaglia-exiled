package unibo.exiled.model.map;

public enum CellType {
    PLAINS(0),
    SWAMP(1),
    VOLCANO(2),
    STORM(3),
    FOREST(4);

    private final int index;

    CellType(final int index){
        this.index = index;
    }

    public int getValue(){
        return this.index;
    }
}
