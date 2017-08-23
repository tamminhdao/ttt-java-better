package com.tamdao.challenge.BoardAndGrid;

public class Board extends AbstractBoard {

    public Board() {
        boardSize = defaultBoardSize;
        cellsPerRow = defaultCellsPerRow;
        allCells = new String[defaultBoardSize];
        populateBoardWithEmptyCells();
    }

    public Board(int numberOfCellsPerRow) {
        cellsPerRow = numberOfCellsPerRow;
        boardSize = (int) Math.pow(cellsPerRow, 2);
        allCells = new String[this.boardSize];
        populateBoardWithEmptyCells();
    }

    public void populateBoardWithEmptyCells() {
        for (int i = 0; i < allCells.length; i++) {
            allCells[i] = emptyCell;
        }
    }

    public int countEmptyCells() {
        int numberOfEmptyCells = 0;
        for (int i = 0; i < allCells.length; i++) {
            if (allCells[i].equals(emptyCell)) {
                numberOfEmptyCells += 1;
            }
        }
        return numberOfEmptyCells;
    }

    public void resetCell (int index) {
        allCells[index] = emptyCell;
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public int getCellsPerRow() {
        return this.cellsPerRow;
    }

    public String[] getAllSymbols() {
        return this.allCells;
    }

    public String getSymbol(int index) {
        return this.allCells[index];
    }

    public void insertSymbol(String symbol, int index) {
        this.allCells[index] = symbol;
    }
}
