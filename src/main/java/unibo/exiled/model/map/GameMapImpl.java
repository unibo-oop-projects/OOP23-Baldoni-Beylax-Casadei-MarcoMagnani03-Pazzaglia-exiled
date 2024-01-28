package unibo.exiled.model.map;

import unibo.exiled.model.utilities.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

public class GameMapImpl implements GameMap{
    private final int height;
    private final int width;
    private final Map<Position,CellType> cellStates;

    private void fillCellRange(final CellType type, final int fromY, final int toY, final int fromX, final int toX){
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

    private void fillCells(final int startingArea){
        // Random generation of the game map
        List<CellType> cellTypes = new ArrayList<>(List.of(CellType.values()));
        cellTypes.remove(CellType.PLAINS); // Removed CellType.PLAINS from cellTypes because it's the starting player spawn area. 
        Collections.shuffle(cellTypes);

        this.fillCellRange(cellTypes.get(0), 0,height / 2, 0, width / 2);
        this.fillCellRange(cellTypes.get(1), height / 2, this.height,0,width / 2);
        this.fillCellRange(cellTypes.get(2), 0, this.height / 2, width / 2,width);
        this.fillCellRange(cellTypes.get(3), height / 2,height, width / 2, width);
        this.fillCellRange(CellType.PLAINS, startingArea,height - startingArea, startingArea , width-startingArea);
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
        if(this.isInBoundaries(cell)){
            return this.cellStates.get(cell);
        }
        else{
            throw new NoSuchElementException("The selected position is out of boundaries.");
        }
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
