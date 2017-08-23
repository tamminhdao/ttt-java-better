package com.tamdao.challenge.Players;

import com.tamdao.challenge.BoardAndGrid.AbstractBoard;
import com.tamdao.challenge.BoardAndGrid.Board;
import com.tamdao.challenge.IO.CommandLineIO;
import com.tamdao.challenge.IO.IO;
import com.tamdao.challenge.Validator.CommandLineValidator;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {
    private IO simulateHumanPlayerInput (String input) {
        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(stream);
        return new CommandLineIO(scanner);
    }

    @Test
    public void playerCanSelectAValidCell() throws Exception {
        IO io = simulateHumanPlayerInput("1");
        AbstractBoard board = new Board();
        CommandLineValidator validator = new CommandLineValidator(board);
        HumanPlayer player = new HumanPlayer(io, validator, "X");
        int cellNumber = player.obtainValidCellSelection();
        assertEquals(1, cellNumber);
    }

    @Test
    public void canGetSymbol() throws Exception {
        Scanner scanner = new Scanner(System.in);
        IO io = new CommandLineIO(scanner);
        AbstractBoard board = new Board();
        CommandLineValidator validator = new CommandLineValidator(board);
        HumanPlayer player = new HumanPlayer(io, validator, "ABC123");
        String playerSymbol = player.getSymbol();
        assertEquals("ABC123", playerSymbol);
    }
}
