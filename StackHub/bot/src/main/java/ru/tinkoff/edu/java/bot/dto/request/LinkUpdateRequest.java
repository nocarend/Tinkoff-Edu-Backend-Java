package ru.tinkoff.edu.java.bot.dto.request;

import java.util.List;

/**
 * Entity for sending updates to the bot.
 *
 * @param id          link id
 * @param url         link
 * @param description message description
 * @param tgChatIds   by whom link are tracking
 */
public record LinkUpdateRequest(long id, String url, String description, List<Long> tgChatIds) {

}
