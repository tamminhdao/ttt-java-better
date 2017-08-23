package com.tamdao.challenge.BoardAndGrid;

public abstract class AbstractBoard {
    int boardSize;
    final int defaultBoardSize = 9;
    String[] allCells;
    int cellsPerRow;
    final int defaultCellsPerRow = 3;
    final String emptyCell = " ";

    public abstract void populateBoardWithEmptyCells();
    public abstract int countEmptyCells();
    public abstract void resetCell(int index);
    public abstract int getBoardSize();
    public abstract int getCellsPerRow();
    public abstract String[] getAllSymbols();
    public abstract String getSymbol(int index);
    public abstract void insertSymbol(String symbol, int index);
}
