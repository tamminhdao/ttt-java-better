package com.tamdao.challenge.Validator;

public interface UserInputValidator {
    boolean validateSymbolSelection(String symbolInput);
    boolean playersHaveUniqueSymbols(String player1Symbol, String player2Symbol);
    boolean validateCellSelection(int cellSelection);
}
