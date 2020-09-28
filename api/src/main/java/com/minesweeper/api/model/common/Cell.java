package com.minesweeper.api.model.common;

public class Cell {
    private CellType cellType;
    private String label;
    private int cellNumber;
    private boolean open;
    private boolean flag;

    public Cell(){
        // Empty Constructor
    }

    public Cell(CellType cellType, int cellNumber) {
        this.cellType = cellType;
        this.cellNumber = cellNumber;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
