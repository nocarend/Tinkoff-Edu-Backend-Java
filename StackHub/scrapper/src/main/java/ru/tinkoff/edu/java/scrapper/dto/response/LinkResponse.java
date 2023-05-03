package ru.tinkoff.edu.java.scrapper.dto.response;

import java.net.URI;

/**
 * LinkResponse
 */

public record LinkResponse(long id, URI url) {

    @Override
    public String toString() {
        return String.valueOf(url);
    }
}