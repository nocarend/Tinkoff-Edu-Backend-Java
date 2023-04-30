package ru.tinkoff.edu.java.scrapper.service.jooq;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jooq.JooqChatRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

@RequiredArgsConstructor
public class JooqChatService implements ChatService {

    private final JooqChatRepository repository;
    private final LinkService linkService;

    @Override
    public List<Link> getLinksFromChatId(long chatId) {
        return repository.findByChatId(chatId).stream()
            .map(track -> linkService.getLinkById(track.getLinkId())).toList();
    }

    @Override
    public void create(long chatId) {
    }

    @Override
    public void delete(long chatId) {
        repository.removeByChatId(chatId);
    }

    @Override
    public Link track(long chatId, URI url) {
        if (!linkService.contains(url)) {
            linkService.add(url);
        }
        repository.add(chatId, linkService.getLinkByUrl(url).getId());
        return new Link().setUrl(url.toString());
    }

    @Override
    public Link untrack(long chatId, URI url) {
        repository.findByChatId(chatId).stream()
            .noneMatch(
                track -> {
                    linkService.getLinkById(track.getLinkId());
                    return false;
                });
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Long> getChatsFromLinkId(long linkId) {
        return repository.findAllChats().stream().filter(track -> track.getLinkId() == linkId)
            .map(Chat::getChatId).toList();
    }
}