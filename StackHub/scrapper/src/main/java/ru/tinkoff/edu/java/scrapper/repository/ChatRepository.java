package ru.tinkoff.edu.java.scrapper.repository;

import java.util.List;
import ru.tinkoff.edu.java.scrapper.model.Chat;

public interface ChatRepository {

    void add(long chatId, long linkId);

    void removeByTrackId(long trackId);

    void removeByChatId(long chatId);

    List<Chat> findAllChats();

    List<Chat> findByChatId(long chatId);
}
