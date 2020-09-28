package com.minesweeper.api.model.common;

public class Cell {
    private CellType cellType;
    private String label;
    private int cellNumber;
    private int xPos;
    private int yPos;
    private boolean open;
    private boolean flag;
    private CellType up;
    private CellType left;
    private CellType down;
    private CellType right;

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

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public CellType getUp() {
        return up;
    }

    public void setUp(CellType up) {
        this.up = up;
    }

    public CellType getLeft() {
        return left;
    }

    public void setLeft(CellType left) {
        this.left = left;
    }

    public CellType getDown() {
        return down;
    }

    public void setDown(CellType down) {
        this.down = down;
    }

    public CellType getRight() {
        return right;
    }

    public void setRight(CellType right) {
        this.right = right;
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
