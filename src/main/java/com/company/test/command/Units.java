package com.company.test.command;

import java.util.Optional;
import java.util.stream.Stream;

public enum Units {
    BIT("", 1),
    BYTE("B", 8),
    KB("KB", 8192),
    MB("MB", 8388608);

    private final String code;
    private final Integer bitSize;

    Units(final String code, final int bitSize) {
        this.bitSize = bitSize;
        this.code = code;
    }

    public static Integer getInBitSize(final String code) {
        return getByCode(code)
                .map(Units::getBitSize)
                .orElseThrow(() -> new IllegalArgumentException("Unknown units " + code));
    }

    public static Optional<Units> getByCode(final String code) {
        return Stream.of(values())
                .filter(units -> units.getCode().equalsIgnoreCase(code))
                .findFirst();
    }

    public String getCode() {
        return code;
    }

    public Integer getBitSize() {
        return bitSize;
    }
}
