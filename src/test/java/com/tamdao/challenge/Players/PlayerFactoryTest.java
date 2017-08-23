package com.tamdao.challenge.Players;

import java.util.Scanner;
import com.tamdao.challenge.BoardAndGrid.AbstractBoard;
import com.tamdao.challenge.BoardAndGrid.Board;
import com.tamdao.challenge.IO.CommandLineIO;
import com.tamdao.challenge.IO.IO;
import com.tamdao.challenge.Rules.Rules;
import com.tamdao.challenge.Rules.RulesFor3x3;
import com.tamdao.challenge.Validator.CommandLineValidator;
import com.tamdao.challenge.Validator.UserInputValidator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PlayerFactoryTest {
    private Scanner scanner;
    private IO io;
    private AbstractBoard board;
    private Rules rules;
    private UserInputValidator validator;
    private PlayerFactory playerFactory;

    @Before
    public void setUp() throws Exception {
        scanner = new Scanner(System.in);
        io = new CommandLineIO(scanner);
        board = new Board();
        rules = new RulesFor3x3();
        validator = new CommandLineValidator(board);
        playerFactory = new PlayerFactory(rules, board, io, validator);
    }

    @Test
    public void canCreateHumanPlayer() throws Exception {
        Player testPlayer = playerFactory.createPlayer("H", "human", "ai");
        Player humanPlayer = new HumanPlayer(io, validator, "human");
        assertEquals(testPlayer.getClass(), humanPlayer.getClass());
    }

    @Test
    public void canCreateComputerPlayer() throws Exception {
        Player testPlayer = playerFactory.createPlayer("C", "human", "ai");
        Player computerPlayer = new UnbeatableComputer(rules, board, "ai", "opponent");
        assertEquals(testPlayer.getClass(), computerPlayer.getClass());
    }
}
