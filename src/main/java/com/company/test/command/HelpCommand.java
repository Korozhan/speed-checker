package com.company.test.command;

public class HelpCommand implements Command {
    @Override
    public void run() {
        System.out.println(
                "help       - показать список параметров программы"
              + "\nconfigure  - задать диапазаны"
              + "\nspeedcheck - выполнить оценку скорости(выполняется по умолчанию, если не указана команда)"
        );
    }
}
