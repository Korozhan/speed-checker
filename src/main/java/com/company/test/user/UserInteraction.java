package com.company.test.user;

public interface UserInteraction {

    String askForString(final String question);

    long askForNumber(final String question);

    void message(final String message);
}
