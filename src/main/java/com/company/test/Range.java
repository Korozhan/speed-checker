package com.company.test;

import static java.util.Objects.requireNonNull;

public class Range {
    private final long start;
    private final long end;
    private final String description;

    public Range(final long start, final long end, final String description) {
        if (start > end) {
            throw new IllegalArgumentException(
                    "start (" + start + ") should be less than end (" + end + ")");
        }
        this.start = start;
        this.end = end;
        this.description = requireNonNull(description, "description is null");
    }

    public boolean contains(final long value) {
        return value >= start && value < end;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public String getDescription() {
        return description;
    }
}
