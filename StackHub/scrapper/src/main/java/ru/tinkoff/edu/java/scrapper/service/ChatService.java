package ru.tinkoff.edu.java.scrapper.service;

import java.net.URI;
import java.util.List;
import ru.tinkoff.edu.java.scrapper.model.Link;

public interface ChatService {

    List<Link> getLinksFromChatId(long chatId);

    void create(long chatId);

    void delete(long chatId);

    Link track(long chatId, URI url);

    Link untrack(long chatId, URI url);
    List<Long> getChatsFromLinkId(long linkId);
}
