package unibo.exiled.model.map;

import unibo.exiled.model.utilities.Position;

import java.util.HashMap;
import java.util.Map;

public class GameMapImpl implements  GameMap{
    private final int height;
    private final int width;
    private final Map<Position,CellType> cellStates;

    private final void fillCellRange(final CellType type, final int fromY, final int toY, final int fromX, final int toX){
        for(int i = fromY ; i < toY; i++){
            for(int j = fromX; j < toX; j++){
                final Position pos = new Position(j,i);
                if(type.equals(CellType.PLAINS)){
                    this.cellStates.replace(pos,type);
                }
                else{
                    this.cellStates.put(pos,type);
                }
            }
        }
    }

    private final void fillCells(final int startingArea){
        this.fillCellRange(CellType.VOLCANO,0,height / 2, 0, width / 2);
        this.fillCellRange(CellType.SWAMP,height / 2, this.height,0,width / 2);
        this.fillCellRange(CellType.STORM,0, this.height / 2, width / 2,width);
        this.fillCellRange(CellType.FOREST,height / 2,height, width / 2, width);
        this.fillCellRange(CellType.PLAINS,startingArea,height - startingArea, startingArea , width-startingArea);
    }

    public GameMapImpl(final int size){
        if(size % 2 == 0) {
            this.height = size;
            this.width = size;
            this.cellStates = new HashMap<>();
            final int startingSize = (size / 2) - 3;
            this.fillCells(startingSize);
        }
        else{
            throw new IllegalArgumentException("The size of the map should be an even number.");
        }
    }

    //Gets the type of cell.
    @Override
    public CellType getCellType(final Position cell) {
        return this.cellStates.get(cell);
    }

    @Override
    public boolean isInBoundaries(final Position cell) {
        return this.cellStates.containsKey(cell);
    }

    //Dimensions getters
    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight(){
        return  this.height;
    }
}
