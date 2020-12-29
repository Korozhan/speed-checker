package com.company.test;

import com.company.test.command.CommandFactory;
import com.company.test.command.CommandName;
import com.company.test.command.UnitConverter;
import com.company.test.serialization.CsvRangeSerializer;
import com.company.test.serialization.RangeSerializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.util.Optional.ofNullable;

public class SpeedCheckerApplication {

    private static Path createConfigFile() {
        final Path path = Paths.get(System.getProperty("user.home"), ".speed-checker.properties");
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
        final UnitConverter unitConverter = new UnitConverter();
        final Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        final RangeSerializer serializer = new CsvRangeSerializer();

        final CommandFactory commandFactory = new CommandFactory(configPath, scanner, serializer, unitConverter);

        final String configContent = new String(Files.readAllBytes(configPath), StandardCharsets.UTF_8);
        if (configContent.trim().isEmpty()) {
            commandFactory.createByKeyword(CommandName.CONFIGURE).run();
        } else {
            commandFactory.createByKeyword(parseCommandKeyword(args)).run();
        }
    }

    private static CommandName parseCommandKeyword(final String[] args) {
        final String keyword = ofNullable(args)
                .filter(it -> it.length > 0)
                .map(it -> it[0])
                .orElse(CommandName.SPEED_CHECK.getKeyword());
        return CommandName.findByKeyword(keyword).orElseThrow(() ->
                new IllegalArgumentException("Unknown command. Please check help"));
    }
}