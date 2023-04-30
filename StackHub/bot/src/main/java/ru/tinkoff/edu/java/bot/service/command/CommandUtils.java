package ru.tinkoff.edu.java.bot.service.command;

import lombok.NonNull;

public class CommandUtils {

    private CommandUtils() {
        throw new AssertionError();
    }

    public static String trimmedString(@NonNull String string, int commandLength) {
        return String.format("%-20s", string.trim())
            .substring(0, commandLength);
    }
}