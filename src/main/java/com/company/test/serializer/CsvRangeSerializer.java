package com.company.test.serializer;

import com.company.test.Range;

import static java.util.Objects.requireNonNull;

public class CsvRangeSerializer implements RangeSerializer {

    @Override
    public String serialize(final Range range) {
        return range.getStart() + "," + range.getEnd() + "," + range.getDescription();
    }

    @Override
    public Range deserialize(final String csvLine) {
        requireNonNull(csvLine, "text is null");

        if (csvLine.trim().isEmpty()) {
            throw new IllegalArgumentException("csv line shouldn't be null");
        }
        final String[] columns = csvLine.trim().split(",");
        if (columns.length != 3) {
            throw new IllegalStateException("csv line should have 3 column");
        }
        return new Range(
                Long.parseLong(columns[0]),
                Long.parseLong(columns[1]),
                columns[2]);
    }
}
