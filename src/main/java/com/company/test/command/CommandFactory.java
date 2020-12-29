package com.company.test.command;

import com.company.test.reader.ConfigRangeReader;
import com.company.test.reader.ConsoleRangeReader;
import com.company.test.serializer.RangeSerializer;

import java.nio.file.Path;
import java.util.Scanner;

public class CommandFactory {
    private final Path configPath;
    private final Scanner scanner;
    private final RangeSerializer serializer;
    private final UnitsConverter unitsConverter;

    public CommandFactory(final Path configPath,
                          final Scanner scanner,
                          final RangeSerializer serializer,
                          final UnitsConverter unitsConverter) {
        this.configPath = configPath;
        this.scanner = scanner;
        this.serializer = serializer;
        this.unitsConverter = unitsConverter;
    }

    public Command createByName(final CommandName commandName) {
        if (commandName == CommandName.SPEED_CHECK) {
            return new SpeedCheckCommand(
                    new ConfigRangeReader(configPath, serializer), scanner, unitsConverter);
        } else if (commandName == CommandName.CONFIGURE) {
            return new ConfigureCommand(
                    new ConsoleRangeReader(scanner), configPath, serializer);
        } else {
            return new HelpCommand();
        }
    }
}
