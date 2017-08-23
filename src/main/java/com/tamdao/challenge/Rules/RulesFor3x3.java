package com.tamdao.challenge.Rules;

import com.tamdao.challenge.BoardAndGrid.AbstractBoard;

public class RulesFor3x3 implements Rules {
    private int[][] winningCombos;
    private final String emptyCell = " ";

    public RulesFor3x3() {
        this.identifyWinningCombos();
    }

    @Override
    public void identifyWinningCombos() {
        this.winningCombos = new int[][] {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    }

    @Override
    public String checkForWinner(AbstractBoard board) {
        for (int index = 0; index < this.winningCombos.length; index++) {
            if (threeInARow(board, index)) {
                return board.getSymbol(this.winningCombos[index][0]);
            }
        }
        return "";
    }

    @Override
    public boolean endsInADraw(AbstractBoard board) {
        return (checkForWinner(board).equals("") && board.countEmptyCells() == 0);
    }

    @Override
    public boolean thereIsAWinner(AbstractBoard board) {
        String winner = checkForWinner(board);
        return !winner.equals("");
    }

    @Override
    public boolean cellIsEmpty(AbstractBoard board, int index) {
        return board.getSymbol(index).equals(emptyCell);
    }

    private boolean threeInARow(AbstractBoard board, int index) {
        return !(board.getSymbol(this.winningCombos[index][0]).equals(" ")) &&
                board.getSymbol(this.winningCombos[index][0]).equals(board.getSymbol(this.winningCombos[index][1])) &&
                board.getSymbol(this.winningCombos[index][1]).equals(board.getSymbol(this.winningCombos[index][2]));
    }
}
