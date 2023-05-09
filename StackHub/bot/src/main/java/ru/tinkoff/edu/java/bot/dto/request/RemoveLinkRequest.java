package ru.tinkoff.edu.java.bot.dto.request;

import java.net.URI;

/**
 * Entity for deleting links from tracking list.
 *
 * @param link url
 */
public record RemoveLinkRequest(URI link) {

}