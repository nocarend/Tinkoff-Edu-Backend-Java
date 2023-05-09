package ru.tinkoff.edu.java.bot.dto.request;

import java.net.URI;

/**
 * Entity for adding links with controller.
 *
 * @param link url
 */
public record AddLinkRequest(URI link) {

}

