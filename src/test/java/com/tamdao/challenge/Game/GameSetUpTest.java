package com.tamdao.challenge.Game;

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

public class GameSetUpTest {
    @Test
    public void canReturnGamePreferences() throws Exception {
        AbstractBoard board = new Board();
        CommandLineValidator validator = new CommandLineValidator(board);

        String input = "H\nX\nC\nO";
        byte[] inputSource = input.getBytes(StandardCharsets.UTF_8);
        InputStream stream = new ByteArrayInputStream(inputSource);
        Scanner scanner = new Scanner(stream);
        IO io = new CommandLineIO(scanner);

        GameSetUp gameSetUp = new GameSetUp(io, validator);
        GameConfiguration configuration = gameSetUp.collectGamePreferences();

        assertEquals("H", configuration.playerOnesType);
        assertEquals("C", configuration.playerTwosType);
        assertEquals("X", configuration.playerOneSymbol);
        assertEquals("O", configuration.playerTwoSymbol);
    }
}
