package com.company.test.command;

import com.company.test.Range;
import com.company.test.reader.RangeReader;

import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

public class SpeedCheckCommand implements Command {

    private final RangeReader rangeReader;
    private final Scanner scanner;
    private final UnitsConverter unitsConverter;

    public SpeedCheckCommand(final RangeReader rangeReader,
                             final Scanner scanner,
                             final UnitsConverter unitsConverter) {
        this.rangeReader = requireNonNull(rangeReader, "rangeReader is null");
        this.scanner = requireNonNull(scanner, "scanner is null");
        this.unitsConverter = requireNonNull(unitsConverter, "unitConverter is null");
    }

    @Override
    public void run() {
        final String availableUnits = Stream.of(Units.values())
                .filter(units -> units != Units.BIT)
                .map(Units::getCode)
                .collect(joining(", ", "(", ")"));

        System.out.print("Введите размер передачи в битах или укажите единицы изменерения" + availableUnits + ": ");
        final long size = unitsConverter.toBits(scanner.next());

        System.out.print("Введите скорость передачи в битах в секунду или укажите единицы" + availableUnits + ": ");
        final long speed = unitsConverter.toBits(scanner.next());

        final Range range = rangeReader.read().stream()
                .filter(it -> it.contains(size / speed))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Range not found"));

        System.out.println(range.getDescription());
    }
}
