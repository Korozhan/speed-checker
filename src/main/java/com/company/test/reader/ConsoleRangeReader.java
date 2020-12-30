package com.company.test.reader;

import com.company.test.Range;
import com.company.test.user.UserInteraction;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class ConsoleRangeReader implements RangeReader {
    private final UserInteraction interaction;

    public ConsoleRangeReader(final UserInteraction interaction) {
        this.interaction = requireNonNull(interaction, "interaction is null");
    }

    @Override
    public List<Range> read() {
        interaction.message("Нужно задать диапазоны для корректной работы программы\n");
        final List<Range> ranges = new ArrayList<>();
        boolean oneMoreRange = Boolean.TRUE;
        while (oneMoreRange) {
            createRange(ranges);
            final String answer = interaction.askForString("Добавить еще диапазон? (Y/n): ").trim();//"[yYnN]"
            if (answer.equalsIgnoreCase("n")) {
                createLastRange(ranges);
                oneMoreRange = Boolean.FALSE;
            }
        }
        return ranges;
    }

    private void createLastRange(final List<Range> ranges) {
        final long start = getLastEnd(ranges);
        final String description = interaction.askForString("\tукажите название для последнего диапазона времени(больше " + start + " секунд): ");
        addRange(ranges, Long.MAX_VALUE, description);
    }

    private void createRange(final List<Range> ranges) {
        final long start = getLastEnd(ranges);
        interaction.message("Создание диапазона. Нижняя граница диапазона времени в секундах " + start + "\n");
        final long end = interaction.askForNumber("\tукажите верхнюю границу диапазона времени(больше " + start + " секунд): ");
        final String description = interaction.askForString("\tукажите название для диапазона: ");

        addRange(ranges, end, description);
    }

    private void addRange(final List<Range> ranges, final long end, final String description) {
        final long start = getLastEnd(ranges);

        final Range range = new Range(start, end, description);
        if (!ranges.contains(range)) {
            ranges.add(range);
        }
    }

    private long getLastEnd(final List<Range> ranges) {
        return ranges.isEmpty() ? 0 : ranges.get(ranges.size() - 1).getEnd();
    }
}
