package ru.tinkoff.edu.java.bot.service.dto;

import java.util.List;

/**
 * Dto containing link and other data.
 *
 * @param linkId  link id
 * @param url     link url
 * @param message message that sends to the user
 * @param chats   to whom message should be delivered
 */
public record LinkUpdate(long linkId, String url, String message, List<Long> chats) {

}
