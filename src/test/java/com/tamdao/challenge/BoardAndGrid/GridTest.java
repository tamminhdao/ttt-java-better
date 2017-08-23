package com.tamdao.challenge.BoardAndGrid;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GridTest {
    private AbstractBoard board;
    private Grid tictactoe;

    @Before
    public void initBoard() {
        board = new Board();
        tictactoe = new Grid(board);
    }

    @Test
    public void canRenderGridWithOnlyCellNumberId() throws Exception {
        String newGrid = tictactoe.getStringRepresentationOfGrid();

        String expected =   " 1 | 2 | 3 " + "\n--- --- --- \n" +
                            " 4 | 5 | 6 " + "\n--- --- --- \n" +
                            " 7 | 8 | 9 " + "\n";

        assertEquals (expected, newGrid);
    }

    private void populateBoard(int numberOfCells) {
        for (int i = 0; i < numberOfCells; i++) {
            if (i % 2 == 0) {
                board.insertSymbol("X", i);
            } else {
                board.insertSymbol("O", i);
            }
        }
    }

    private void populateBoardWithSymbolsOfDifferentStringLength(int numberOfCells) {
        for (int i = 0; i < numberOfCells; i++) {
            if (i % 2 == 0) {
                board.insertSymbol("ABCDEF", i);
            } else {
                board.insertSymbol("$$$", i);
            }
        }
    }

    @Test
    public void canRenderGridWithSymbolsInserted() throws Exception {
        populateBoard(4);
        String newGrid = tictactoe.getStringRepresentationOfGrid();

        String expected =   " X | O | X " + "\n--- --- --- \n" +
                            " O | 5 | 6 " + "\n--- --- --- \n" +
                            " 7 | 8 | 9 " + "\n";

        assertEquals (expected, newGrid);
    }

    @Test
    public void gridSizeAdjustToSymbolsLength() throws Exception {
        populateBoardWithSymbolsOfDifferentStringLength(3);
        String newGrid = tictactoe.getStringRepresentationOfGrid();

        String expected =   "        |        |        \n" +
                            " ABCDEF |  $$$   | ABCDEF \n" +
                            "        |        |        \n" +
                            "-------- -------- -------- \n" +
                            "        |        |        \n" +
                            "   4    |   5    |   6    \n" +
                            "        |        |        \n" +
                            "-------- -------- -------- \n" +
                            "        |        |        \n" +
                            "   7    |   8    |   9    \n" +
                            "        |        |        \n";

        assertEquals (expected, newGrid);
    }

    @Test
    public void canRenderFourByFourBoardWithSymbolsOfVariousLength() throws Exception {
        Board fourBoard = new Board(4);
        Grid fourGrid = new Grid(fourBoard);
        fourBoard.insertSymbol("12345", 1);
        fourBoard.insertSymbol("hello world", 4);
        String fourByFourBoard = fourGrid.getStringRepresentationOfGrid();

        String expected =   "             |             |             |             \n" +
                            "      1      |    12345    |      3      |      4      \n" +
                            "             |             |             |             \n" +
                            "             |             |             |             \n" +
                            "------------- ------------- ------------- ------------- \n" +
                            "             |             |             |             \n" +
                            " hello world |      6      |      7      |      8      \n" +
                            "             |             |             |             \n" +
                            "             |             |             |             \n" +
                            "------------- ------------- ------------- ------------- \n" +
                            "             |             |             |             \n" +
                            "      9      |     10      |     11      |     12      \n" +
                            "             |             |             |             \n" +
                            "             |             |             |             \n" +
                            "------------- ------------- ------------- ------------- \n" +
                            "             |             |             |             \n" +
                            "     13      |     14      |     15      |     16      \n" +
                            "             |             |             |             \n" +
                            "             |             |             |             \n";

        assertEquals(expected, fourByFourBoard);
    }
}
