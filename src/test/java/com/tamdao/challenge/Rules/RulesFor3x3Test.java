package com.tamdao.challenge.Rules;

import com.tamdao.challenge.BoardAndGrid.AbstractBoard;
import com.tamdao.challenge.BoardAndGrid.Board;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RulesFor3x3Test {
    private RulesFor3x3 gameRules;
    private AbstractBoard board;

    @Before
    public void createRules() {
        gameRules = new RulesFor3x3();
        board = new Board();
    }

    @Test
    public void winningByRow() throws Exception {
        for (int i = 0; i < 3; i++) {
            board.insertSymbol("X", i);
        }
        String winner = gameRules.checkForWinner(board);
        assertEquals("X", winner);
    }

    @Test
    public void winningByColumn() throws Exception {
        for (int i = 0; i < 7; i += 3) {
            board.insertSymbol("O", i);
        }
        String winner = gameRules.checkForWinner(board);
        assertEquals("O", winner);
    }

    @Test
    public void winningByDiagonalTopRightToBottomLeft() throws Exception {
        for (int i = 2; i < 7; i += 2) {
            board.insertSymbol("O", i);
        }
        String winner = gameRules.checkForWinner(board);
        assertEquals("O", winner);
    }

    @Test
    public void winningByDiagonalTopLeftToBottomRight() throws Exception {
        for (int i = 0; i < 9; i += 4) {
            board.insertSymbol("X", i);
        }
        String winner = gameRules.checkForWinner(board);
        assertEquals("X", winner);
    }

    @Test
    public void returnEmptyStringForWinnerIfGameIsInProgress() throws Exception {
        board.insertSymbol("X", 0);
        board.insertSymbol("O", 1);
        String winner = gameRules.checkForWinner(board);
        assertEquals("", winner);
    }

    private void simulateAGameWhichEndsInATie() {
        board.insertSymbol("X", 0);
        board.insertSymbol("O", 1);
        board.insertSymbol("X", 2);
        board.insertSymbol("O", 3);
        board.insertSymbol("O", 4);
        board.insertSymbol("X", 5);
        board.insertSymbol("O", 6);
        board.insertSymbol("X", 7);
        board.insertSymbol("O", 8);
    }

    @Test
    public void gameTiesWhenThereIsNoWinAndNoMoreEmptyCells() throws Exception {
        simulateAGameWhichEndsInATie();
        boolean draw = gameRules.endsInADraw(board);
        assertTrue(draw);
    }

    @Test
    public void returnEmptyStringForWinnerIfGameEndsInATie() throws Exception {
        simulateAGameWhichEndsInATie();
        String winner = gameRules.checkForWinner(board);
        assertEquals("", winner);
    }
}
