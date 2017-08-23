package com.tamdao.challenge.Game;

import com.tamdao.challenge.BoardAndGrid.AbstractBoard;
import com.tamdao.challenge.BoardAndGrid.Board;
import com.tamdao.challenge.IO.CommandLineIO;
import com.tamdao.challenge.IO.IO;
import com.tamdao.challenge.Players.Player;
import com.tamdao.challenge.Players.UnbeatableComputer;
import com.tamdao.challenge.Rules.Rules;
import com.tamdao.challenge.Rules.RulesFor3x3;
import org.junit.Test;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void twoUnbeatableComputerPlayAgainstEachOtherResultsInATie() throws Exception {
        Scanner scanner = new Scanner(System.in);
        IO io = new CommandLineIO(scanner);
        AbstractBoard board = new Board();
        Rules rules = new RulesFor3x3();
        Player player1 = new UnbeatableComputer(rules, board, "X", "O");
        Player player2 = new UnbeatableComputer(rules, board, "O", "X");
        Game game = new Game(player1, player2, io, rules, board);
        game.play();
        assertEquals("", game.getWinnerSymbol());
    }
}
