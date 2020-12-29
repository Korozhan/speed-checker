package com.company.test.command;

import com.company.test.Range;
import com.company.test.reader.RangeReader;

import java.util.Scanner;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class SpeedCheckCommand implements Command {

    private final RangeReader rangeReader;
    private final Scanner scanner;
    private final UnitConverter unitConverter;

    public SpeedCheckCommand(final RangeReader rangeReader,
                             final Scanner scanner,
                             final UnitConverter unitConverter) {
        this.rangeReader = requireNonNull(rangeReader, "rangeReader is null");
        this.scanner = requireNonNull(scanner, "scanner is null");
        this.unitConverter = requireNonNull(unitConverter, "unitConverter is null");
    }

    @Override
    public void run() {
        final Set<Range> ranges = rangeReader.read();
        System.out.print("Введите размер передачи в битах или укажите единицы изменерения(B, KB, MB): ");
        final long size = unitConverter.toBits(scanner.next());
        System.out.print("Введите скорость передачи в битах в секунду или укажите единицы изменерения(B, KB, MB): ");
        final long speed = unitConverter.toBits(scanner.next());
        final Range range = ranges.stream()
                .filter(it -> it.contains(size / speed))
                .findFirst().orElseThrow(() -> new IllegalStateException("Range not found"));
        System.out.println(range.getDescription());
    }
}