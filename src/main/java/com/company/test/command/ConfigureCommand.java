package com.company.test.command;

import com.company.test.reader.RangeReader;
import com.company.test.serialization.RangeSerializer;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Path;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

public class ConfigureCommand implements Command {

    private final Path configPath;
    private final RangeSerializer serializer;
    private final RangeReader consoleRangeReader;

    public ConfigureCommand(final RangeReader consoleRangeReader, final Path configPath, final RangeSerializer serializer) {
        this.configPath = requireNonNull(configPath, "configPath is null");
        this.serializer = requireNonNull(serializer, "serializer is null");
        this.consoleRangeReader = requireNonNull(consoleRangeReader, "consoleRangeReader is null");
    }

    @Override
    public void run() {
        final String csv = consoleRangeReader.read().stream()
                .map(serializer::serialize)
                .collect(joining("\n"));

        try (final Writer writer = new PrintWriter(configPath.toFile())) {
            writer.write(csv);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read range from console.", e);
        }
    }
}
