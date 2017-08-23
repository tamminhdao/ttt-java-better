package com.tamdao.challenge.BoardAndGrid;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BoardTest {
    private Board board;

    @Before
    public void initBoard() {
        board = new Board();
    }

    @Test
    public void testBoardIsCreatedWithAllEmptyCells() throws Exception {
        String[] allSymbolsOnBoard = board.getAllSymbols();
        String[] expected = {" ", " ", " ", " ", " ", " ", " ", " ", " "};
        assertArrayEquals(expected, allSymbolsOnBoard);
    }

    @Test
    public void testCanInsertSymbolOnABoard() throws Exception {
        board.insertSymbol("X", 0);
        String symbolAtIndexZero = board.getSymbol(0);
        assertEquals("X", symbolAtIndexZero);
    }

    @Test
    public void canGetBoardSize() throws Exception {
        Board fourFourBoard = new Board(4);
        int boardSize = fourFourBoard.getBoardSize();
        assertEquals(16, boardSize);
    }

    @Test
    public void canGetNumberOfCellsPerRow() throws Exception {
        Board fourFourBoard = new Board(4);
        int numberOfCellsPerRow = fourFourBoard.getCellsPerRow();
        assertEquals(4, numberOfCellsPerRow);
    }

    @Test
    public void canResetACell() throws Exception {
        board.insertSymbol("X", 3);
        board.resetCell(3);
        String expected = board.getSymbol(3);
        assertEquals(expected," ");
    }

    @Test
    public void returnCorrectNumberOfEmptyCells() throws Exception {
        board.insertSymbol("O", 2);
        int totalNumberOfEmptyCells = board.countEmptyCells();
        assertEquals(8, totalNumberOfEmptyCells);
    }

    @Test
    public void canGetAllSymbolsOnABoard() throws Exception {
        board.insertSymbol("O", 1);
        board.insertSymbol("M", 3);
        board.insertSymbol("X", 7);
        String[] allSymbols = board.getAllSymbols();
        String[] expected = {" ", "O", " ", "M", " ", " ", " ", "X", " "};
        assertArrayEquals(expected, allSymbols);
    }
}
