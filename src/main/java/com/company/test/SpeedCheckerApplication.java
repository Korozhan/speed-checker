package com.company.test;

import com.company.test.command.CommandFactory;
import com.company.test.command.CommandName;
import com.company.test.command.UnitsConverter;
import com.company.test.serializer.CsvRangeSerializer;
import com.company.test.serializer.RangeSerializer;
import com.company.test.user.ConsoleUserInteraction;
import com.company.test.user.UserInteraction;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;

import static java.util.Optional.ofNullable;

public class SpeedCheckerApplication {

    private static Path createConfigFile() {
        final Path path = Paths.get(System.getProperty("user.home"), ".speed-checker");
        if (path.toFile().exists()) {
            return path;
        }
        try {
            if (!path.toFile().createNewFile()) {
                throw new IllegalStateException("Ошибка при создании файла конфигурации");
            }
            return path;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static Path resolveConfigPath() {
        return ofNullable(System.getenv("SPEED_CHECKER_CONFIG_PATH"))
                .map(Paths::get)
                .orElseGet(SpeedCheckerApplication::createConfigFile);
    }

    public static void main(String[] args) throws IOException {

        final Path configPath = resolveConfigPath();
        final UnitsConverter unitsConverter = new UnitsConverter();
        final Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        final UserInteraction interaction = new ConsoleUserInteraction(scanner);
        final RangeSerializer serializer = new CsvRangeSerializer();

        final CommandFactory commandFactory = new CommandFactory(configPath, interaction, serializer, unitsConverter);

        if (isEmptyConfiguration(configPath)) {
            commandFactory.createByName(CommandName.CONFIGURE).run();
            commandFactory.createByName(CommandName.SPEED_CHECK).run();
        } else {
            final Optional<CommandName> commandName = parseCommandName(args);
            if (commandName.isPresent()) {
                commandFactory.createByName(commandName.get()).run();
            } else {
                interaction.message("Неизвестная команда, ознакомьтесь с инструкцией.\n");
                commandFactory.createByName(CommandName.HELP).run();
            }
        }
    }

    private static boolean isEmptyConfiguration(final Path configPath) throws IOException {
        final String configContent = new String(Files.readAllBytes(configPath), StandardCharsets.UTF_8);
        return configContent.trim().isEmpty();
    }

    private static Optional<CommandName> parseCommandName(final String[] args) {
        final String keyword = ofNullable(args)
                .filter(it -> it.length > 0)
                .map(it -> it[0])
                .orElse(CommandName.SPEED_CHECK.getKeyword());
        return CommandName.findByKeyword(keyword);
    }
}
