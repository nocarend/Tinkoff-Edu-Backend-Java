package ru.tinkoff.edu.java.scrapper.dto.response;

import java.util.List;

/**
 * ListLinksResponse
 */

public record ListLinksResponse(List<LinkResponse> links, int size) {

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (var link : links) {
            result.append(String.format("%d. %s\n", counter++, link.toString()));
        }
        return result.toString();
    }
}