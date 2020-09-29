package com.minesweeper.api.utils;

import java.util.ArrayList;
import java.util.List;

import com.minesweeper.api.model.common.enums.Compass;

public class DigUtils {

    public static List<Integer> getAdjacentCells(final int currentCell, int rows, int columns) {
        
        int rowPos = (currentCell / columns) + 1;
        int colPos = (currentCell % columns) + 1;
        List<Integer> cells = new ArrayList<>();
        for (Compass compass : Compass.values()) {
            switch (compass) {
                case NORTH:
                    if (rowPos == 1)
                        continue;
                    cells.add(currentCell - rows);
                    break;
                case NORTH_EAST:
                    if (rowPos == 1 || colPos == columns)
                        continue;
                    cells.add(currentCell - rows + 1);
                    
                    break;
                case EAST:
                    if (colPos == columns)
                        continue;
                    cells.add(currentCell + 1);

                    break;
                case SOUTH_EAST:
                    if (rowPos == rows || colPos == columns)
                        continue;
                    cells.add(currentCell + rows + 1);
                    break;
                case SOUTH:
                    if (rowPos == rows)
                        continue;
                    cells.add(currentCell + rows);
                    break;
                case SOUTH_WEST:
                    if (rowPos == rows || colPos == 1)
                        continue;
                    cells.add(currentCell + rows - 1);
                    break;
                case WEST:
                    if (colPos == 1)
                        continue;
                    cells.add(currentCell - 1);
                    break;
                case NORTH_WEST:
                    if (rowPos == 1 || colPos == 1)
                        continue;
                    cells.add(currentCell - rows - 1);
                    break;
                default:
                    // Do nothing
            }
        }

        return cells;
    }
}
