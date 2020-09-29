package com.minesweeper.api.factory;

import java.util.ArrayList;
import java.util.List;

import com.minesweeper.api.factory.consumer.CellConsumer;
import com.minesweeper.api.model.common.Cell;
import com.minesweeper.api.model.common.enums.CellType;
import com.minesweeper.api.model.common.Field;
import com.minesweeper.api.model.common.Game;

public class FieldFactory {
    private final Field newField;

    public FieldFactory() {
        this.newField = new Field();
    }

    public Field build(final Game  game){
        this.newField.setRows(game.getConfig().getRows());
        this.newField.setColumns(game.getConfig().getColumns());
        this.newField.setTotalCells(game.getConfig().getTotalCells());
        this.newField.setCells(buildFieldCells(game));
        this.setCellsLabels();
        return this.newField;
    }

    private List<Cell> buildFieldCells(final Game  game){
        final List<Cell> fieldCells = new ArrayList<>();
        for(int i = 0; i < game.getConfig().getTotalCells(); i++){
            final int currentCell = i;
            Cell newCell;
            newCell = game.getMines().stream().filter(m -> m.getCellNumber() == currentCell).findFirst().orElse(null);
            if(newCell == null){
               newCell = new Cell(CellType.BLANK, i);
            }
            if(game.getOpenedCells().contains(i)){
                newCell.setOpen(true);
            }
            if(game.getFlaggedCells().contains(i)){
                newCell.setFlag(true);
            }
            fieldCells.add(newCell);
        }

        return fieldCells;
    }

    private void setCellsLabels(){
        CellConsumer cellConsumer = new CellConsumer(this.newField.getCells(), this.newField.getRows(), this.newField.getColumns());
        this.newField.getCells().forEach(cellConsumer);
    }
}
