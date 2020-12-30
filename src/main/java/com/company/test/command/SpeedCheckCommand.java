package com.company.test.command;

import com.company.test.Range;
import com.company.test.reader.RangeReader;
import com.company.test.user.UserInteraction;

import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

public class SpeedCheckCommand implements Command {

    private final RangeReader rangeReader;
    private final UserInteraction interaction;
    private final UnitsConverter unitsConverter;

    public SpeedCheckCommand(final RangeReader rangeReader,
                             final UserInteraction interaction,
                             final UnitsConverter unitsConverter) {
        this.rangeReader = requireNonNull(rangeReader, "rangeReader is null");
        this.interaction = requireNonNull(interaction, "interaction is null");
        this.unitsConverter = requireNonNull(unitsConverter, "unitConverter is null");
    }

    @Override
    public void run() {
        final String availableUnits = Stream.of(Units.values())
                .filter(units -> units != Units.BIT)
                .map(Units::getCode)
                .collect(joining(", ", "(", ")"));

        final long size = unitsConverter.toBits(interaction.askForString(
                "Введите размер передачи в битах или укажите единицы изменерения" + availableUnits + ": "));

        final long speed = unitsConverter.toBits(interaction.askForString(
                "Введите скорость передачи в битах в секунду или укажите единицы" + availableUnits + ": "));

        final Range range = rangeReader.read().stream()
                .filter(it -> it.contains(size / speed))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Range not found"));

        interaction.message(range.getDescription() + "\n");
    }
}
