package ru.tinkoff.edu.java.bot.dto.response;

import java.util.List;
import ru.tinkoff.edu.java.scrapper.dto.response.LinkResponse;

/**
 * ListLinksResponse
 */

public record ListLinksResponse(List<LinkResponse> links, int size) {

    @Override
    public String toString() {
        if (size == 0) {
            return "Your list is empty.";
        }
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (var link : links) {
            result.append(String.format("%d. %s\n", counter++, link.toString()));
        }
        return result.toString();
    }
}