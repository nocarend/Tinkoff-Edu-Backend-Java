package ru.tinkoff.edu.java.bot.dto.response;

import java.util.List;

public record ListCommandsResponse(List<CommandResponse> commands) {

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (var command : commands) {
            result.append(String.format("%d. %s\n", counter++, command.toString()));
        }
        return result.toString();
    }
}
