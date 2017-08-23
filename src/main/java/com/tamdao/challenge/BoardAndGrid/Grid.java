package com.tamdao.challenge.BoardAndGrid;

import java.util.ArrayList;
import java.util.Collections;

public class Grid {
    private AbstractBoard board;
    private int boardSize;
    private int cellsPerRow;
    private final int CELL_OFFSET = 1;

    public Grid(AbstractBoard board) {
        this.board = board;
        this.boardSize = board.getBoardSize();
        this.cellsPerRow = board.getCellsPerRow();
    }

    public String getStringRepresentationOfGrid() {
        StringBuilder grid = new StringBuilder ("");
        for (int cellIndex = 0; cellIndex <= this.boardSize - this.cellsPerRow; cellIndex += this.cellsPerRow){
            grid.append(drawARow(cellIndex));
            if (cellIndex < this.boardSize - this.cellsPerRow) {
                grid.append(drawRowDivider());
            }
        }
        grid.append("\n");
        return grid.toString();
    }

    private int findMaxStringLengthOfSymbols() {
        String[] boardArray = this.board.getAllSymbols();
        ArrayList<Integer> symbolLength = new ArrayList<>();
        for (int i = 0; i < boardArray.length; i++) {
            symbolLength.add(boardArray[i].length());
        }
        return Collections.max(symbolLength);
    }

    private String getCenteredSymbols(String symbol, Integer maxLength) {
        if (symbol.length() == maxLength) {
            return symbol;
        } else {
            int paddingOnBothSides = maxLength - symbol.length();
            if (paddingOnBothSides % 2 == 0) {
                String padding = getPaddingSpace(paddingOnBothSides);
                return padding + symbol + padding;
            } else {
                String paddingLeft = getPaddingSpace(paddingOnBothSides);
                String paddingRight = paddingLeft + " ";
                return paddingLeft + symbol + paddingRight;
            }
        }
    }

    private String getPaddingSpace (int paddingOnBothSides) {
        String unit = "";
        int paddingOnEachSide = paddingOnBothSides / 2;
        for (int j = 0; j < paddingOnEachSide; j++) {
            unit += " ";
        }
        return unit;
    }

    private String drawARow(int startingCell) {
        StringBuilder row = new StringBuilder ("");
        int maxLengthOfSymbols = findMaxStringLengthOfSymbols();
        int numberOfRowsOfTopPadding = numberOfRowOfPaddingAboveTheSymbol();
        int numberOfRowsOfBottomPadding = numberOfRowOfPaddingBelowTheSymbol();
        row.append(rowsOfPadding(numberOfRowsOfTopPadding, drawARowOfUpperPadding()));
        for (int cellIndex = startingCell; cellIndex < startingCell + cellsPerRow; cellIndex++) {
            String spaceOrPipe = lastCellOfARow(cellIndex) ? " " : " |";
            String cellContent = indexOrSymbol(cellIndex);
            row.append(" " + getCenteredSymbols(cellContent, maxLengthOfSymbols) + spaceOrPipe);
        }
        row.append(rowsOfPadding(numberOfRowsOfBottomPadding, drawARowOfBottomPadding()));
        return row.toString();
    }

    private String indexOrSymbol(int cellIndex) {
        if (board.getSymbol(cellIndex).equals(" ")) {
            Integer cellId = cellIndex + CELL_OFFSET;
            return cellId.toString();
        } else {
            return board.getSymbol(cellIndex);
        }
    }

    private boolean lastCellOfARow(int cellIndex) {
        return cellIndex % cellsPerRow == this.cellsPerRow - CELL_OFFSET;
    }

    private String drawRowDivider() {
        String horizontalLine = "\n";
        for (int i = 0; i < this.cellsPerRow; i++) {
            horizontalLine += drawDividerOrPaddingUnit("-");
            horizontalLine += " ";
        }
        horizontalLine += "\n";
        return horizontalLine;
    }

    private String rowsOfPadding(int numberOfRows, String padding) {
        StringBuilder rowOfPadding = new StringBuilder ("");
        for (int i = 0; i < numberOfRows; i++) {
            rowOfPadding.append(padding);
        }
        return rowOfPadding.toString();
    }

    private int numberOfTotalRowOfPaddingAboveAndBelowTheSymbol() {
        int maxLengthOfSymbols = findMaxStringLengthOfSymbols();
        int WIDTH_TO_HEIGHT_RELATIVE_CONVERSION_UNIT = 3;
        return maxLengthOfSymbols/WIDTH_TO_HEIGHT_RELATIVE_CONVERSION_UNIT;
    }

    private int numberOfRowOfPaddingAboveTheSymbol() {
        return numberOfTotalRowOfPaddingAboveAndBelowTheSymbol()/2;
    }

    private int numberOfRowOfPaddingBelowTheSymbol() {
        int halfOfTotal = numberOfTotalRowOfPaddingAboveAndBelowTheSymbol()/2;
        int oddOrEven = numberOfTotalRowOfPaddingAboveAndBelowTheSymbol() % 2;
        int numberOfLowerPaddingRows = (oddOrEven == 0)? halfOfTotal : (halfOfTotal+1);
        return numberOfLowerPaddingRows;
    }

    private String drawARowOfUpperPadding() {
        String padding = "";
        padding = drawPadding(padding);
        padding += "\n";
        return padding;
    }

    private String drawARowOfBottomPadding() {
        String padding = "\n";
        padding = drawPadding(padding);
        return padding;
    }

    private String drawPadding(String padding) {
        for (int i = 0; i < this.cellsPerRow; i++) {
            padding += drawDividerOrPaddingUnit(" ");
            if (i != this.cellsPerRow - 1) {
                padding += "|";
            }
        }
        return padding;
    }

    private String drawDividerOrPaddingUnit(String pattern) {
        String unit = "";
        int paddedSpaceCharacterOnBothEndsOfSymbols = 2;
        int maxLengthOfSymbols = findMaxStringLengthOfSymbols();
        for (int j = 0; j < maxLengthOfSymbols + paddedSpaceCharacterOnBothEndsOfSymbols; j++) {
            unit += pattern;
        }
        return unit;
    }
}
