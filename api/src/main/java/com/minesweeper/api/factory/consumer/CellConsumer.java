package com.minesweeper.api.factory.consumer;

import java.util.List;
import java.util.function.Consumer;
import com.minesweeper.api.model.common.Cell;
import com.minesweeper.api.model.common.CellType;
import com.minesweeper.api.model.common.Compass;

public class CellConsumer implements Consumer<Cell> {

    private List<Cell> fieldCells;
    private int rows;
    private int columns;
    private int rowPos;
    private int colPos;

    public CellConsumer(List<Cell> fieldCells, int rows, int columns){
        this.fieldCells = fieldCells;
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public void accept(Cell cell) {
        if(cell.getCellType() == CellType.BLANK){
            int proximityMines = 0;
            this.calculateCoords(cell.getCellNumber());
            proximityMines += getMine(Compass.NORTH, cell.getCellNumber());
            proximityMines += getMine(Compass.NORTH_EAST, cell.getCellNumber());
            proximityMines += getMine(Compass.EAST, cell.getCellNumber());
            proximityMines += getMine(Compass.SOUTH_EAST, cell.getCellNumber());
            proximityMines += getMine(Compass.SOUTH, cell.getCellNumber());
            proximityMines += getMine(Compass.SOUTH_WEST, cell.getCellNumber());
            proximityMines += getMine(Compass.WEST, cell.getCellNumber());
            proximityMines += getMine(Compass.NORTH_WEST, cell.getCellNumber());

            cell.setLabel(proximityMines > 0 ? String.valueOf(proximityMines) : "");
        }
    }
    
    private void calculateCoords(int currentCell){
        this.rowPos = currentCell / this.columns;
        this.colPos = currentCell % this.columns;
    }

    private int getMine(final Compass compass, final int currentCell){
        int pos = 0;
        switch(compass){
            case NORTH:
                if(this.rowPos == 1) return 0;
                pos = currentCell - this.rows;
                return searchMine(pos) ? 1 : 0;
            case NORTH_EAST:
                if(this.rowPos == 1 || this.colPos == this.columns) return 0;
                pos = currentCell - this.rows + 1;
                return searchMine(pos) ? 1 : 0;
            case EAST:
                if(this.colPos == this.columns) return 0;
                pos = currentCell + 1;
                return searchMine(pos) ? 1 : 0;
            case SOUTH_EAST:
                if(this.rowPos == this.rows || this.colPos == this.columns) return 0;
                pos = currentCell + this.rows + 1;
                return searchMine(pos) ? 1 : 0;
            case SOUTH:
                if(this.rowPos == this.rows) return 0;
                pos = currentCell + this.rows;
                return searchMine(pos) ? 1 : 0;
            case SOUTH_WEST:
                if(this.rowPos == this.rows || this.colPos == 1) return 0;
                pos = currentCell + this.rows - 1;
                return searchMine(pos) ? 1 : 0;
            case WEST:
                if(this.colPos == 1) return 0;
                pos = currentCell - 1;
                return searchMine(pos) ? 1 : 0;
            case NORTH_WEST:
                if(this.rowPos == 1 || this.colPos == 1) return 0;
                pos = currentCell - this.rows - 1;
                return searchMine(pos) ? 1 : 0;
            default:
                return 0;
        }
    }

    private boolean searchMine(int position){
        final Cell cell = this.fieldCells.stream().filter(x -> x.getCellNumber() == position).findFirst().orElse(null);
        return cell != null && cell.getCellType() == CellType.MINE;
    }
}
