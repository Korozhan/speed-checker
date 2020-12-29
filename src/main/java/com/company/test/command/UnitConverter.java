package com.company.test.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public class UnitConverter {

    private final Pattern pattern = Pattern.compile("(?<value>\\d+)\\s*(?<unit>\\w+)?", Pattern.MULTILINE);

    public long toBits(final String rawInput) {
        requireNonNull(rawInput, "rawInput is null");

        final String effectiveInput = rawInput.trim();
        if (effectiveInput.isEmpty()) {
            throw new IllegalArgumentException("input should not be empty");
        }
        final Matcher matcher = pattern.matcher(effectiveInput);
        if (!matcher.find()) {
            throw new IllegalStateException("Input: " + rawInput);
        }
        final long value = Long.parseLong(matcher.group("value").trim());
        final String unit = ofNullable(matcher.group("unit")).map(String::trim).orElse("");

        return convertBits(value, unit);
    }

    private long convertBits(final long value, final String unit) {
        switch (unit.toUpperCase()) {
            case "":
                return value;
            case "B":
                return 8 * value;
            case "KB":
                return 1024 * 8 * value;
            case "MB":
                return 1024 * 1024 * 8 * value;
            default:
                throw new IllegalArgumentException("Unknown unit " + unit);
        }
    }
}
