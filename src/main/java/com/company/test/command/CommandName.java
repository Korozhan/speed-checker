package com.company.test.command;

import java.util.Optional;

public enum CommandName {
    HELP("help"),
    CONFIGURE("configure"),
    SPEED_CHECK("speedcheck");

    private final String keyword;

    CommandName(final String keyword) {
        this.keyword = keyword;
    }

    public static Optional<CommandName> findByKeyword(final String name) {
        for (final CommandName cmd : values()) {
            if (cmd.getKeyword().equalsIgnoreCase(name)) {
                return Optional.of(cmd);
            }
        }
        return Optional.empty();
    }

    public String getKeyword() {
        return keyword;
    }
}
