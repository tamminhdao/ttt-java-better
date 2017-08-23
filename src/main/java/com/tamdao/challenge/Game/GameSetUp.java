package com.tamdao.challenge.Game;

import com.tamdao.challenge.IO.IO;
import com.tamdao.challenge.Validator.UserInputValidator;

public class GameSetUp {
    private IO io;
    private UserInputValidator validator;

    public GameSetUp(IO io, UserInputValidator validator) {
        this.io = io;
        this.validator = validator;
    }

    public void gameIntro() {
        io.insertNewLine();
        io.publishOutput(gameIntroduction());
        io.insertNewLine();
    }

    public GameConfiguration collectGamePreferences() {
        String playerOnesType = selectPlayerType(1);
        String playerOneSymbol = selectPlayerSymbol(1);
        String playerTwosType = selectPlayerType(2);
        String playerTwoSymbol = selectPlayerSymbol(2);
        playerTwoSymbol = checkForUniqueSymbol(playerOneSymbol, playerTwoSymbol);
        return new GameConfiguration(playerOnesType, playerTwosType, playerOneSymbol, playerTwoSymbol);
    }

    private String checkForUniqueSymbol(String playerOneSymbol, String playerTwoSymbol) {
        boolean uniqueSymbols = validator.playersHaveUniqueSymbols(playerOneSymbol, playerTwoSymbol);
        while (!uniqueSymbols) {
            io.publishOutput(checkForUniqueSymbolAlert());
            playerTwoSymbol = selectPlayerSymbol(2);
            uniqueSymbols = validator.playersHaveUniqueSymbols(playerOneSymbol, playerTwoSymbol);
        }
        return playerTwoSymbol;
    }

    private String selectPlayerType(int playerId) {
        io.publishOutput(promptUserToSelectPlayerType(playerId));
        String selectedType = io.obtainInput();
        if (selectedType.equals("H") || selectedType.equals("C")) {
            return selectedType;
        } else {
            io.publishOutput(invalidTypeAlert());
            return selectPlayerType(playerId);
        }
    }

    private String selectPlayerSymbol(int playerId) {
        io.publishOutput(promptUserToChooseASymbol(playerId));
        String selectedSymbol = io.obtainInput();
        boolean validSymbol = validator.validateSymbolSelection(selectedSymbol);
        while (!validSymbol) {
            io.publishOutput(invalidSymbolAlert());
            selectedSymbol = selectPlayerSymbol(playerId);
            validSymbol = validator.validateSymbolSelection(selectedSymbol);
        }
        return selectedSymbol;
    }

    private String gameIntroduction() {
        return "Welcome to Tic Tac Toe!";
    }

    private String promptUserToSelectPlayerType(Integer playerId) {
        return "Select [H] for human or [C] for computer to determine the type of player " + playerId.toString();
    }

    private String promptUserToChooseASymbol(Integer playerId) {
        return "Pick a unique symbol (a string of any length) for player " + playerId.toString();
    }

    private String invalidTypeAlert() {
        return "Invalid type selection.";
    }

    private String invalidSymbolAlert() {
        return "Invalid symbol selection.";
    }

    private String checkForUniqueSymbolAlert() {
        return "Another player already picked that symbol.";
    }
}
