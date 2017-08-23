package com.tamdao.challenge.IO;

import java.io.PrintStream;
import java.util.Scanner;

public class CommandLineIO implements IO {
    private Scanner scanner;
    private PrintStream outStream;

    public CommandLineIO(Scanner scanner) {
        this.scanner = scanner;
        this.outStream = System.out;
    }

    @Override
    public String obtainInput() {
        String input = this.scanner.nextLine();
        input = input.trim();
        return input;
    }

    @Override
    public void publishOutput(String output) {
        this.outStream.println(output);
    }

    @Override
    public void insertNewLine() {
        this.outStream.println("\n");
    }
}
