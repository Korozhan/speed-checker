package com.company.test.command;

import com.company.test.reader.ConfigRangeReader;
import com.company.test.reader.ConsoleRangeReader;
import com.company.test.serializer.RangeSerializer;
import com.company.test.user.UserInteraction;

import java.nio.file.Path;

public class CommandFactory {
    private final Path configPath;
    private final UserInteraction interaction;
    private final RangeSerializer serializer;
    private final UnitsConverter unitsConverter;

    public CommandFactory(final Path configPath,
                          final UserInteraction interaction,
                          final RangeSerializer serializer,
                          final UnitsConverter unitsConverter) {
        this.configPath = configPath;
        this.interaction = interaction;
        this.serializer = serializer;
        this.unitsConverter = unitsConverter;
    }

    public Command createByName(final CommandName commandName) {
        if (commandName == CommandName.SPEED_CHECK) {
            return new SpeedCheckCommand(
                    new ConfigRangeReader(configPath, serializer), interaction, unitsConverter);
        } else if (commandName == CommandName.CONFIGURE) {
            return new ConfigureCommand(
                    new ConsoleRangeReader(interaction), configPath, serializer);
        } else {
            return new HelpCommand(interaction);
        }
    }
}
