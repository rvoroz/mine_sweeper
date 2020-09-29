package com.minesweeper.api.factory.consumer;

import java.util.List;
import java.util.function.Consumer;
import com.minesweeper.api.model.common.Cell;
import com.minesweeper.api.model.common.enums.CellType;
import com.minesweeper.api.model.common.enums.Compass;

public class CellConsumer implements Consumer<Cell> {

    private List<Cell> fieldCells;
    private int rows;
    private int columns;
    private int rowPos;
    private int colPos;

    public CellConsumer(List<Cell> fieldCells, int rows, int columns) {
        this.fieldCells = fieldCells;
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public void accept(Cell cell) {
        if (cell.getCellType() == CellType.BLANK) {
            int proximityMines = 0;
            this.calculateCoords(cell.getCellNumber());
            proximityMines += getProximityMines(cell.getCellNumber());
            cell.setLabel(proximityMines > 0 ? String.valueOf(proximityMines) : "");
        }
    }

    private void calculateCoords(int currentCell) {
        this.rowPos = (currentCell / this.columns) + 1;
        this.colPos = (currentCell % this.columns) + 1;
    }

    private int getProximityMines(final int currentCell) {
        int mines = 0;
        for (Compass compass : Compass.values()) {

            int pos = 0;
            switch (compass) {
                case NORTH:
                    if (this.rowPos == 1)
                        continue;
                    pos = currentCell - this.rows;
                    mines += searchMine(pos) ? 1 : 0;
                    break;
                case NORTH_EAST:
                    if (this.rowPos == 1 || this.colPos == this.columns)
                        continue;
                    pos = currentCell - this.rows + 1;
                    mines += searchMine(pos) ? 1 : 0;
                    break;
                case EAST:
                    if (this.colPos == this.columns)
                        continue;
                    pos = currentCell + 1;
                    mines += searchMine(pos) ? 1 : 0;
                    break;
                case SOUTH_EAST:
                    if (this.rowPos == this.rows || this.colPos == this.columns)
                        continue;
                    pos = currentCell + this.rows + 1;
                    mines += searchMine(pos) ? 1 : 0;
                    break;
                case SOUTH:
                    if (this.rowPos == this.rows)
                        continue;
                    pos = currentCell + this.rows;
                    mines += searchMine(pos) ? 1 : 0;
                    break;
                case SOUTH_WEST:
                    if (this.rowPos == this.rows || this.colPos == 1)
                        continue;
                    pos = currentCell + this.rows - 1;
                    mines += searchMine(pos) ? 1 : 0;
                    break;
                case WEST:
                    if (this.colPos == 1)
                        continue;
                    pos = currentCell - 1;
                    mines += searchMine(pos) ? 1 : 0;
                    break;
                case NORTH_WEST:
                    if (this.rowPos == 1 || this.colPos == 1)
                        continue;
                    pos = currentCell - this.rows - 1;
                    mines += searchMine(pos) ? 1 : 0;
                    break;
                default:
                    // Do nothing
            }
        }

        return mines;
    }

    private boolean searchMine(int position) {
        final Cell cell = this.fieldCells.stream().filter(x -> x.getCellNumber() == position).findFirst().orElse(null);
        return cell != null && cell.getCellType() == CellType.MINE;
    }
}
