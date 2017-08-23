package com.tamdao.challenge.IO;

import org.junit.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommandLineIOTest {
    @Test
    public void canCaptureAndReturnInput() {
        String input = "X";
        byte[] inputSource = input.getBytes(StandardCharsets.UTF_8);
        InputStream stream = new ByteArrayInputStream(inputSource);
        Scanner scanner = new Scanner(stream);
        CommandLineIO io = new CommandLineIO(scanner);
        String userInput = io.obtainInput();
        assertEquals(userInput, "X");
    }

    @Test
    public void canPrintString() throws Exception {
        OutputStream outputDestination = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputDestination));
        Scanner scanner = new Scanner(System.in);
        CommandLineIO io = new CommandLineIO(scanner);
        io.publishOutput("Test Output");
        assertTrue (outputDestination.toString().contains("Test Output"));
    }

    @Test
    public void canPrintNewLine() throws Exception {
        OutputStream outputDestination = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputDestination));
        Scanner scanner = new Scanner(System.in);
        CommandLineIO io = new CommandLineIO(scanner);
        io.insertNewLine();
        assertTrue (outputDestination.toString().contains("\n"));
    }
}
