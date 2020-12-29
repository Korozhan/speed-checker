package com.company.test;

import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Range range = (Range) o;
        return start == range.start &&
                end == range.end &&
                Objects.equals(description, range.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, description);
    }

    @Override
    public String toString() {
        return "Range{" +
                "start=" + start +
                ", end=" + end +
                ", description='" + description + '\'' +
                '}';
    }
}
