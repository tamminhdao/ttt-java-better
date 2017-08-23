package com.tamdao.challenge.Players;

import com.tamdao.challenge.AI.Minimax;
import com.tamdao.challenge.BoardAndGrid.AbstractBoard;
import com.tamdao.challenge.Rules.Rules;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class UnbeatableComputer implements Player {
    private AbstractBoard board;
    private Rules rules;
    private String selfSymbol;
    private Minimax algorithm;
    private final int CELL_OFFSET = 1;

    public UnbeatableComputer (Rules rules, AbstractBoard board, String selfSymbol, String opponentSymbol) {
        this.rules = rules;
        this.board = board;
        this.selfSymbol = selfSymbol;
        algorithm = new Minimax(rules, board, selfSymbol, opponentSymbol);
    }

    @Override
    public String getSymbol() {
        return this.selfSymbol;
    }

    @Override
    public int obtainValidCellSelection() {
        HashMap<Integer, Integer> scoreAndCell = new HashMap<>();
        testAndScoreEveryEmptyCell(scoreAndCell);
        int cellIndex = pickCellWithTheHighestScore(scoreAndCell);
        return cellIndex + CELL_OFFSET;
    }

    private int pickCellWithTheHighestScore(HashMap<Integer, Integer> scoreAndCell) {
        Set allKeys = scoreAndCell.keySet();
        ArrayList<Integer> listOfKeys = new ArrayList<>(allKeys);
        int maxScore = Collections.max(listOfKeys);
        return scoreAndCell.get(maxScore);
    }

    private void testAndScoreEveryEmptyCell(HashMap<Integer, Integer> scoreAndCell) {
        for (int index = 0; index < board.getBoardSize(); index++) {
            if (rules.cellIsEmpty(board, index)) {
                board.insertSymbol(selfSymbol, index);
                int score = algorithm.scoreACell(board, false, 0);
                scoreAndCell.put(score, index);
                board.resetCell(index);
            }
        }
    }
}
