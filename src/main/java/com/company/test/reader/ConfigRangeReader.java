package com.company.test.reader;

import com.company.test.Range;
import com.company.test.serializer.RangeSerializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toSet;

public class ConfigRangeReader implements RangeReader {
    private final Path configPath;
    private final RangeSerializer serializer;

    public ConfigRangeReader(final Path configPath, final RangeSerializer serializer) {
        this.configPath = requireNonNull(configPath, "configPath is null");
        this.serializer = requireNonNull(serializer, "serializer is null");
    }

    @Override
    public Set<Range> read() {
        try {
            return Files.readAllLines(configPath, StandardCharsets.UTF_8).stream()
                    .map(serializer::deserialize)
                    .collect(toSet());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
