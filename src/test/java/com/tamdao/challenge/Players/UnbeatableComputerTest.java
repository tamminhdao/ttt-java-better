package com.tamdao.challenge.Players;

import com.tamdao.challenge.BoardAndGrid.AbstractBoard;
import com.tamdao.challenge.BoardAndGrid.Board;
import com.tamdao.challenge.Rules.Rules;
import com.tamdao.challenge.Rules.RulesFor3x3;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UnbeatableComputerTest {
    private AbstractBoard board;
    private Rules rules;
    private UnbeatableComputer smartAI;
    private final int CELL_OFFSET = 1;

    @Before
    public void initBoardAndRules() {
        board = new Board();
        rules = new RulesFor3x3();
        smartAI = new UnbeatableComputer(rules, board, "AI", "O");
    }

    @Test
    public void canGetSymbol() throws Exception {
        String symbol = smartAI.getSymbol();
        assertEquals("AI", symbol);
    }

    @Test
    public void takeAWin() throws Exception {
        board.insertSymbol("O", 6);
        board.insertSymbol("O", 8);
        board.insertSymbol("AI", 4);
        board.insertSymbol("AI", 7);
        int cellNumberOnTheGrid = smartAI.obtainValidCellSelection();
        int cellIndex = cellNumberOnTheGrid - CELL_OFFSET;
        assertEquals(1, cellIndex);
    }

    @Test
    public void canBlockAWin() throws Exception {
        board.insertSymbol("O", 2);
        board.insertSymbol("O", 4);
        board.insertSymbol("AI", 8);
        int cellNumberOnTheGrid = smartAI.obtainValidCellSelection();
        int cellIndex = cellNumberOnTheGrid - CELL_OFFSET;
        assertEquals(6, cellIndex);
    }

    @Test
    public void preventOpponentFromCreatingAFork() throws Exception {
        board.insertSymbol("O", 1);
        board.insertSymbol("O", 3);
        board.insertSymbol("O", 8);
        board.insertSymbol("AI", 4);
        board.insertSymbol("AI", 7);
        int cellNumberOnTheGrid = smartAI.obtainValidCellSelection();
        int cellIndex = cellNumberOnTheGrid - CELL_OFFSET;
        assertEquals(2, cellIndex);
    }

    @Test
    public void takeCenterCell() throws Exception {
        board.insertSymbol("O", 0);
        int cellNumberOnTheGrid = smartAI.obtainValidCellSelection();
        int cellIndex = cellNumberOnTheGrid - CELL_OFFSET;
        assertEquals(4, cellIndex);
    }

    @Test
    public void takeIntoAccountDepth() throws Exception {
        board.insertSymbol("O", 1);
        board.insertSymbol("O", 5);
        board.insertSymbol("O", 8);
        board.insertSymbol("AI", 6);
        board.insertSymbol("AI", 7);
        int cellNumberOnTheGrid = smartAI.obtainValidCellSelection();
        int cellIndex = cellNumberOnTheGrid - CELL_OFFSET;
        assertEquals(2, cellIndex);
    }
}
