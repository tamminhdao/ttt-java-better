package com.tamdao.challenge.Game;

import com.tamdao.challenge.BoardAndGrid.AbstractBoard;
import com.tamdao.challenge.BoardAndGrid.Grid;
import com.tamdao.challenge.IO.IO;
import com.tamdao.challenge.Players.Player;
import com.tamdao.challenge.Rules.Rules;

public class Game {
    private Rules rules;
    private AbstractBoard board;
    private Grid grid;
    private Player player1;
    private Player player2;
    private IO io;
    private final int CELL_OFFSET = 1;

    public Game(Player player1, Player player2, IO io, Rules rules, AbstractBoard board) {
        this.player1 = player1;
        this.player2 = player2;
        this.io = io;
        this.rules = rules;
        this.board = board;
        this.grid = new Grid(board);
    }

    public void play() {
        renderBoard();
        while (gameInPlay()) {
            makeMove(board, player1);
            renderBoard();
            if (!gameInPlay()) {
                break;
            }
            makeMove(board, player2);
            renderBoard();
        }
        getResult();
    }

    public String getWinnerSymbol() {
        return this.rules.checkForWinner(board);
    }

    private boolean gameInPlay() {
        boolean hasNoWinner = rules.checkForWinner(board).equals("");
        boolean isNotADraw = !rules.endsInADraw(board);
        return (hasNoWinner && isNotADraw);
    }

    private void makeMove (AbstractBoard board, Player player) {
        notifyPlayersTurn(player);
        int cellIndex = getCellIndex(player);
        board.insertSymbol(player.getSymbol(), cellIndex);
    }

    private int getCellIndex(Player player) {
        int cellSelection = player.obtainValidCellSelection();
        return cellSelection - CELL_OFFSET;
    }

    private void notifyPlayersTurn(Player player) {
        io.publishOutput(turnNotification(player));
    }

    private void renderBoard() {
        io.insertNewLine();
        io.publishOutput(grid.getStringRepresentationOfGrid());
        io.insertNewLine();
    }

    private void getResult() {
        String winner = rules.checkForWinner(board);
        if (rules.thereIsAWinner(board)) {
            announceWinner(winner);
        } else {
            announceTie();
        }
    }

    private void announceWinner (String winner) {
        io.publishOutput(gameOverAnnouncement());
        io.publishOutput(winnerAnnouncement(winner));
    }

    private void announceTie () {
        io.publishOutput(gameOverAnnouncement());
        io.publishOutput(tieAnnouncement());
    }

    private String turnNotification(Player player) {
        return "Player " + player.getSymbol() + "'s turn";
    }

    private String gameOverAnnouncement() {
        return "Game Over!";
    }

    private String winnerAnnouncement(String winner) {
        return "The winner is: " + winner;
    }

    private String tieAnnouncement() {
        return "It's a tie";
    }
}
