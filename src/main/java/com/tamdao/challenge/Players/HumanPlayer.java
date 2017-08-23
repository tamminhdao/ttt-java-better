package com.tamdao.challenge.Players;

import com.tamdao.challenge.IO.IO;
import com.tamdao.challenge.Validator.UserInputValidator;

public class HumanPlayer implements Player {
    private String symbol;
    private IO io;
    private UserInputValidator validator;

    public HumanPlayer(IO io, UserInputValidator validator, String symbol) {
        this.io = io;
        this.validator = validator;
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public int obtainValidCellSelection() {
        int cell;
        io.publishOutput(promptHumanPlayerToEnterCellSelection());
        cell = obtainCellNumber();
        cell = validateCellNumber(cell);
        return cell;
    }

    private int validateCellNumber(int cell) {
        boolean isValid = validator.validateCellSelection(cell);
        if (!isValid) {
            io.publishOutput(invalidCellNumberSelectionAlert());
            cell = obtainValidCellSelection();
        }
        return cell;
    }

    private int obtainCellNumber() {
        while (true) {
            try {
                String selection = io.obtainInput();
                return Integer.parseInt(selection);
            } catch (NumberFormatException e) {
                io.publishOutput(numberFormatExceptionAlert());
            }
        }
    }

    private String promptHumanPlayerToEnterCellSelection() {
        return "Enter your cell selection: ";
    }

    private String numberFormatExceptionAlert() {
        return "Invalid input. Cell selection has to be a number. ";
    }

    private String invalidCellNumberSelectionAlert() {
        return "Invalid cell number. Choose one of the available cells.";
    }
}
