package ru.tinkoff.edu.java.bot.dto.response;

import java.net.URI;

/**
 * Dto for link.
 *
 * @param id  database link id
 * @param url link url
 */
public record LinkResponse(long id, URI url) {

    @Override
    public String toString() {
        return url.toString();
    }
}