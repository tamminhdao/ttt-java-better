package com.tamdao.challenge.Players;

import com.tamdao.challenge.BoardAndGrid.AbstractBoard;
import com.tamdao.challenge.IO.IO;
import com.tamdao.challenge.Rules.Rules;
import com.tamdao.challenge.Validator.UserInputValidator;

public class PlayerFactory {
    private Rules rules;
    private AbstractBoard board;
    private IO io;
    private UserInputValidator validator;

    public PlayerFactory(Rules rules, AbstractBoard board, IO io, UserInputValidator validator) {
        this.rules = rules;
        this.board = board;
        this.io = io;
        this.validator = validator;
    }

    public Player createPlayer(String playerType, String symbol, String opponentSymbol) {
        switch (playerType) {
            case "H":
                return new HumanPlayer(io, validator, symbol);
            case "C":
                return new UnbeatableComputer(rules, board, symbol, opponentSymbol);
            default:
                return new UnbeatableComputer(rules, board, symbol, opponentSymbol);
        }
    }
}
