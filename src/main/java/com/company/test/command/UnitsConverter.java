package com.company.test.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public class UnitsConverter {

    private static final Pattern NUMBER_WITH_UNITS_PATTERN = Pattern.compile("(?<value>\\d+)\\s*(?<units>\\w+)?", Pattern.MULTILINE);

    public long toBits(final String rawInput) {
        requireNonNull(rawInput, "rawInput is null");

        final String effectiveInput = rawInput.trim();
        if (effectiveInput.isEmpty()) {
            throw new IllegalArgumentException("Input should not be empty");
        }
        final Matcher matcher = NUMBER_WITH_UNITS_PATTERN.matcher(effectiveInput);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Input should contain number: " + rawInput);
        }
        final long value = Long.parseLong(matcher.group("value"));
        final String units = ofNullable(matcher.group("units")).orElse("");

        return value * Units.getInBitSize(units);
    }
}
