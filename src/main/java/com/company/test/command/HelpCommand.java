package com.company.test.command;

import com.company.test.user.UserInteraction;

public class HelpCommand implements Command {

    private final UserInteraction interaction;

    public HelpCommand(final UserInteraction interaction) {
        this.interaction = interaction;
    }

    @Override
    public void run() {
        interaction.message(
                "help       - показать список параметров программы"
                        + "\nconfigure  - задать диапазаны"
                        + "\nspeedcheck - выполнить оценку скорости(выполняется по умолчанию, если не указана команда)\n"
        );
    }
}
