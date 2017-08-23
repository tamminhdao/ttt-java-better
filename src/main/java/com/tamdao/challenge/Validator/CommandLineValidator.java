package com.tamdao.challenge.Validator;

import com.tamdao.challenge.BoardAndGrid.AbstractBoard;

public class CommandLineValidator implements UserInputValidator {
    private AbstractBoard board;

    public CommandLineValidator(AbstractBoard board) {
        this.board = board;
    }

    @Override
    public boolean validateSymbolSelection(String symbolInput) {
        return symbolInput.length() > 0 && !symbolInput.equals("\n");
    }

    @Override
    public boolean playersHaveUniqueSymbols(String player1Symbol, String player2Symbol) {
        return !player1Symbol.equals(player2Symbol);
    }

    @Override
    public boolean validateCellSelection(int cellSelectionInput) {
        return selectWithinBoardSizeRange(cellSelectionInput) && selectedCellCurrentlyEmpty(cellSelectionInput);
    }

    private boolean selectWithinBoardSizeRange(int cellSelectionInput) {
        final int CELL_OFFSET = 1;
        int indexCeiling = board.getBoardSize() + CELL_OFFSET;
        return 0 < cellSelectionInput && cellSelectionInput < indexCeiling;
    }

    private boolean selectedCellCurrentlyEmpty(int cellSelectionInput) {
        return board.getSymbol(cellSelectionInput-1).equals(" ");
    }
}
