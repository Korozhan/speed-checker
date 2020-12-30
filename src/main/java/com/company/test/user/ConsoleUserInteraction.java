package com.company.test.user;

import java.util.Scanner;

public class ConsoleUserInteraction implements UserInteraction {

    private final Scanner scanner;

    public ConsoleUserInteraction(final Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String askForString(final String question) {
        message(question);
        return scanner.next();
    }

    @Override
    public long askForNumber(final String question) {
        message(question);
        return scanner.nextLong();
    }

    @Override
    public void message(final String message) {
        System.out.print(message);
    }
}
