package com.tamdao.challenge.AI;

import com.tamdao.challenge.BoardAndGrid.AbstractBoard;
import com.tamdao.challenge.Rules.Rules;
import java.util.ArrayList;
import java.util.Collections;

public class Minimax {
    private Rules rules;
    private AbstractBoard board;
    private String selfSymbol;
    private String opponentSymbol;

    public Minimax(Rules rules, AbstractBoard board, String selfSymbol, String opponentSymbol) {
        this.rules = rules;
        this.board = board;
        this.selfSymbol = selfSymbol;
        this.opponentSymbol = opponentSymbol;
    }

    public int scoreACell(AbstractBoard board, boolean maximizingPlayersTurn, int depth) {
        depth += 1;

        if (rules.thereIsAWinner(board)) {
            return getWinOrLoseScore(depth);
        } else if (rules.endsInADraw(board)) {
            return 0;
        } else {
            ArrayList<Integer> scores = new ArrayList<>();
            String selfOrOpponent = maximizingPlayersTurn ? this.selfSymbol : this.opponentSymbol;

            for (int index = 0; index < board.getBoardSize(); index++) {
                if (rules.cellIsEmpty(board, index)) {
                    board.insertSymbol(selfOrOpponent, index);
                    int score = scoreACell(board, !maximizingPlayersTurn, depth);
                    scores.add(score);
                    board.resetCell(index);
                }
            }

            int minOrMaxScore = maximizingPlayersTurn ? Collections.max(scores) : Collections.min(scores);
            return minOrMaxScore;
        }
    }

    private int getWinOrLoseScore(int depth) {
        if (rules.checkForWinner(board).equals(selfSymbol)) {
            return 10 - depth;
        }
        return -10 + depth;
    }
}
