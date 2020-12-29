package com.company.test.reader;

import com.company.test.Range;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class ConsoleRangeReader implements RangeReader {
    private final Scanner scanner;

    public ConsoleRangeReader(final Scanner scanner) {
        this.scanner = requireNonNull(scanner, "scanner is null");
    }

    @Override
    public Set<Range> read() {
        System.out.println("Нужно задать диапазоны для корректной работы программы");
        long start = 0;
        final Set<Range> ranges = new HashSet<>();
        for (int i = 1; ; i++) {
            System.out.printf("Создание %d диапазона. Нижняя граница диапазона времени в секундах %d\n", i, start);
            System.out.printf("\tукажите верхнюю границу диапазона времени(больше %d секунд): ", start);
            long end = scanner.nextLong();

            System.out.print("\tукажите название диапазона: ");
            String description = scanner.next();

            System.out.print("Добавить еще диапазон? (Y/n): ");
            final String answer = scanner.next("[yYnN]");

            System.out.println(description + ", start: " + start + ", end: " + end);

            ranges.add(new Range(start, end, description));
            start = end;
            if (answer.equalsIgnoreCase("n")) {
                System.out.printf("\tукажите название последнего %d диапазона времени(больше %d секунд): ", i + 1, start);
                description = scanner.next();
                end = Long.MAX_VALUE;
                System.out.println("последний " + description + ", start: " + start + ", end: " + end);
                ranges.add(new Range(start, end, description));
                break;
            }
        }
        return ranges;
    }
}
