package ru.tinkoff.edu.java.bot.dto.response;

/**
 * Translates command to string representation.
 *
 * @param name        command name
 * @param description command description
 */
public record CommandResponse(String name, String description) {

    @Override
    public String toString() {
        return name + " " + description;
    }
}
