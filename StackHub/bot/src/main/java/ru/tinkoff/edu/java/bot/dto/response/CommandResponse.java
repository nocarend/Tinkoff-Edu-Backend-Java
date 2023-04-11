package ru.tinkoff.edu.java.bot.dto.response;

public record CommandResponse(String name, String description) {

    @Override
    public String toString() {
        return name + " " + description;
    }
}
