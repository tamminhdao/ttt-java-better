package com.tamdao.challenge;

import com.tamdao.challenge.BoardAndGrid.AbstractBoard;
import com.tamdao.challenge.BoardAndGrid.Board;
import com.tamdao.challenge.Game.Game;
import com.tamdao.challenge.Game.GameConfiguration;
import com.tamdao.challenge.Game.GameSetUp;
import com.tamdao.challenge.IO.CommandLineIO;
import com.tamdao.challenge.IO.IO;
import com.tamdao.challenge.Players.Player;
import com.tamdao.challenge.Players.PlayerFactory;
import com.tamdao.challenge.Rules.Rules;
import com.tamdao.challenge.Rules.RulesFor3x3;
import com.tamdao.challenge.Validator.CommandLineValidator;
import com.tamdao.challenge.Validator.UserInputValidator;
import java.util.Scanner;

public class main {
    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        IO io = new CommandLineIO(scanner);
        AbstractBoard board = new Board();
        Rules rules = new RulesFor3x3();
        UserInputValidator validator = new CommandLineValidator(board);

        GameSetUp gameSetUp = new GameSetUp(io, validator);
        gameSetUp.gameIntro();
        GameConfiguration configuration = gameSetUp.collectGamePreferences();
        String playerOnesType = configuration.playerOnesType;
        String playerTwosType = configuration.playerTwosType;
        String playerOneSymbol = configuration.playerOneSymbol;
        String playerTwoSymbol = configuration.playerTwoSymbol;

        PlayerFactory playerFactory = new PlayerFactory(rules, board, io, validator);
        Player playerOne = playerFactory.createPlayer(playerOnesType, playerOneSymbol, playerTwoSymbol);
        Player playerTwo = playerFactory.createPlayer(playerTwosType, playerTwoSymbol, playerOneSymbol);

        Game game = new Game(playerOne, playerTwo, io, rules, board);
        game.play();
    }
}
