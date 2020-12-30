package com.company.test.reader;

import com.company.test.Range;
import com.company.test.user.UserInteraction;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class ConsoleRangeReader implements RangeReader {
    private final UserInteraction interaction;

    public ConsoleRangeReader(final UserInteraction interaction) {
        this.interaction = requireNonNull(interaction, "interaction is null");
    }

    @Override
    public Set<Range> read() {
        interaction.message("Нужно задать диапазоны для корректной работы программы\n");
        long start = 0;
        final Set<Range> ranges = new HashSet<>();
        for (int i = 1; ; i++) {
            interaction.message("Создание " + i + " диапазона. Нижняя граница диапазона времени в секундах " + start + "\n");
            long end = interaction.askForNumber("\tукажите верхнюю границу диапазона времени(больше " + start + " секунд): ");
            String description = interaction.askForString("\tукажите название диапазона: ");
            final String answer = interaction.askForString("Добавить еще диапазон? (Y/n): ").trim();//"[yYnN]"

            ranges.add(new Range(start, end, description));
            start = end;
            if (answer.equalsIgnoreCase("n")) {
                description = interaction.askForString("\tукажите название последнего " + (i + 1) + " диапазона времени(больше " + start + " секунд): ");
                ranges.add(new Range(start, Long.MAX_VALUE, description));
                break;
            }
        }
        return ranges;
    }
}
