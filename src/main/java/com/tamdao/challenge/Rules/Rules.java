package com.tamdao.challenge.Rules;

import com.tamdao.challenge.BoardAndGrid.AbstractBoard;

public interface Rules {
    void identifyWinningCombos();
    String checkForWinner(AbstractBoard board);
    boolean endsInADraw(AbstractBoard board);
    boolean thereIsAWinner(AbstractBoard board);
    boolean cellIsEmpty(AbstractBoard board, int index);
}
