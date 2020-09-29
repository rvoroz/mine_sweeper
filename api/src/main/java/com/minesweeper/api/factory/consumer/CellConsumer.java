package com.minesweeper.api.factory.consumer;

import java.util.List;
import java.util.function.Consumer;
import com.minesweeper.api.model.common.Cell;
import com.minesweeper.api.model.common.enums.CellType;
import com.minesweeper.api.utils.DigUtils;

public class CellConsumer implements Consumer<Cell> {

    private List<Cell> fieldCells;
    private int rows;
    private int columns;

    public CellConsumer(List<Cell> fieldCells, int rows, int columns) {
        this.fieldCells = fieldCells;
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public void accept(Cell cell) {
        if (cell.getCellType() == CellType.BLANK) {
            int proximityMines = 0;
            List<Integer> adjacentCells = DigUtils.getAdjacentCells(cell.getCellNumber(), this.rows, this.columns);
            proximityMines += getProximityMines(adjacentCells);
            cell.setLabel(proximityMines > 0 ? String.valueOf(proximityMines) : "");
        }
    }

    private int getProximityMines(final List<Integer> adjacentCells) {
        int mines = 0;
        for(Integer a : adjacentCells){
            mines += searchMine(a) ? 1 : 0;
        }
        return mines;
    }

    private boolean searchMine(int position) {
        final Cell cell = this.fieldCells.stream().filter(x -> x.getCellNumber() == position).findFirst().orElse(null);
        return cell != null && cell.getCellType() == CellType.MINE;
    }
}
