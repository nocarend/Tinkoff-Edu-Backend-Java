package ru.tinkoff.edu.java.bot.dto.response;

import java.util.List;

/**
 * Response dto containing list of links and its size.
 *
 * @param links links list
 * @param size  size of links list
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