package com.tamdao.challenge.AI;

import com.tamdao.challenge.BoardAndGrid.AbstractBoard;
import com.tamdao.challenge.BoardAndGrid.Board;
import com.tamdao.challenge.Rules.Rules;
import com.tamdao.challenge.Rules.RulesFor3x3;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MinimaxTest {
    private AbstractBoard board;
    private Rules rules;

    @Before
    public void initComponents() {
        board = new Board();
        rules = new RulesFor3x3();
    }

    @Test
    public void canReturnWinOrLoseScoreWhenTheAiItselfWins() throws Exception {
        populateBoard_X_Wins(board);
        Minimax minimax = new Minimax(rules, board, "X", "O");
        int score = minimax.scoreACell(board, true, 0);
        assertEquals(9, score);
    }

    @Test
    public void canReturnWinOrLoseScoreWhenOpponentWins() throws Exception {
        populateBoard_O_Wins(board);
        Minimax minimax = new Minimax(rules, board, "X", "O");
        int score = minimax.scoreACell(board, true, 0);
        assertEquals(-9, score);
    }

    @Test
    public void canReturnCorrectScoreIfGameEndsInATie() throws Exception {
        populateBoard_GameEndsInATie(board);
        Minimax minimax = new Minimax(rules, board, "X", "O");
        int score = minimax.scoreACell(board, true, 0);
        assertEquals(0, score);
    }

    private void populateBoard_X_Wins(AbstractBoard board) {
        for (int i = 0; i < 7; i++) {
            if (i % 2 == 0) {
                board.insertSymbol("X", i);
            } else {
                board.insertSymbol("O", i);
            }
        }
    }

    private void populateBoard_O_Wins(AbstractBoard board) {
        for (int i = 0; i < 7; i++) {
            if (i % 2 == 0) {
                board.insertSymbol("O", i);
            } else {
                board.insertSymbol("X", i);
            }
        }
    }

    private void populateBoard_GameEndsInATie(AbstractBoard board) {
        board.insertSymbol("X", 0);
        board.insertSymbol("X", 1);
        board.insertSymbol("O", 2);
        board.insertSymbol("O", 3);
        board.insertSymbol("O", 4);
        board.insertSymbol("X", 5);
        board.insertSymbol("X", 6);
        board.insertSymbol("O", 7);
        board.insertSymbol("X", 8);
    }
}
