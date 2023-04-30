package ru.tinkoff.edu.java.scrapper.service.updater.dto;

import java.util.List;
import ru.tinkoff.edu.java.scrapper.model.Chat;

public record LinkUpdate(long linkId, String url, String message, List<Long> chats) {

}
