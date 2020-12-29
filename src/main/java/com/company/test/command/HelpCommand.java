package com.company.test.command;

public class HelpCommand implements Command {
    @Override
    public void run() {
        System.out.println("Help text here.");
    }
}
