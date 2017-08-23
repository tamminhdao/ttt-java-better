package com.tamdao.challenge.Validator;

import com.tamdao.challenge.BoardAndGrid.AbstractBoard;
import com.tamdao.challenge.BoardAndGrid.Board;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CommandLineValidatorTest {
    private CommandLineValidator commandLineValidator;
    private AbstractBoard board;

    @Before
    public void canCreateValidator() {
        board = new Board();
        commandLineValidator = new CommandLineValidator(board);
    }

    @Test
    public void returnTrueWhenInputSymbolIsASingleCharacterString() {
        String user_input = "a";
        boolean result = commandLineValidator.validateSymbolSelection(user_input);
        assertEquals(true, result);
    }

    @Test
    public void returnTrueWhenInputSymbolIsAMultiCharacterString() {
        String user_input = "XoxO";
        boolean result = commandLineValidator.validateSymbolSelection(user_input);
        assertEquals(true, result);
    }

    @Test
    public void returnFalseWhenInputSymbolIsAnEmptyString() {
        String user_input = "";
        boolean result = commandLineValidator.validateSymbolSelection(user_input);
        assertEquals(false, result);
    }

    @Test
    public void returnTrueWhenInputSymbolIsASpecialCharacter() {
        String user_input = "#";
        boolean result = commandLineValidator.validateSymbolSelection(user_input);
        assertEquals(true, result);
    }

    @Test
    public void returnFalseWhenInputSymbolIsANewLineCharacter() {
        String user_input = "\n";
        boolean result = commandLineValidator.validateSymbolSelection(user_input);
        assertEquals(false, result);
    }

    @Test
    public void returnTrueWhenPlayersHaveUniqueSymbols() throws Exception {
        String player1Symbol = "X";
        String player2Symbol = "O";
        boolean result = commandLineValidator.playersHaveUniqueSymbols(player1Symbol, player2Symbol);
        assertEquals(true, result);
    }

    @Test
    public void returnFalseWhenPlayersPickTheSameSymbols() throws Exception {
        String player1Symbol = "XO";
        String player2Symbol = "XO";
        boolean result = commandLineValidator.playersHaveUniqueSymbols(player1Symbol, player2Symbol);
        assertEquals(false, result);
    }

    @Test
    public void returnTrueWhenInputCellSelectionIsInRange() {
        int user_input = 1;
        boolean result = commandLineValidator.validateCellSelection(user_input);
        assertEquals(true, result);
    }

    @Test
    public void returnFalseWhenInputCellSelectionIsOutOfRange() {
        int user_input = 10;
        boolean result = commandLineValidator.validateCellSelection(user_input);
        assertEquals(false, result);
    }

    @Test
    public void returnFalseWhenInputCellSelectionIsANegativeNumber() {
        int user_input = -10;
        boolean result = commandLineValidator.validateCellSelection(user_input);
        assertEquals(false, result);
    }

    @Test
    public void returnFalseIfCellIsAlreadyOccupied() throws Exception {
        board.insertSymbol("X", 0);
        int user_input = 1;
        boolean result = commandLineValidator.validateCellSelection(user_input);
        assertEquals(false, result);
    }
}
