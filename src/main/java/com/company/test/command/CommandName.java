package com.company.test.command;

import java.util.Optional;
import java.util.stream.Stream;

public enum CommandName {
    HELP("help"),
    CONFIGURE("configure"),
    SPEED_CHECK("speedcheck");

    private final String keyword;

    CommandName(final String keyword) {
        this.keyword = keyword;
    }

    public static Optional<CommandName> findByKeyword(final String name) {
        return Stream.of(values())
                .filter(cmd -> cmd.getKeyword().equalsIgnoreCase(name))
                .findFirst();
    }

    public String getKeyword() {
        return keyword;
    }
}
