package ru.tinkoff.edu.java.scrapper.service.jdbc;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcChatRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

@RequiredArgsConstructor
public class JdbcChatService implements ChatService {

    private final JdbcChatRepository repository;
    private final LinkService linkService;

    @Override
    public List<Link> getLinksFromChatId(long chatId) {
        var links = repository.findByChatId(chatId);
        return links.stream().map(link -> new Link().setId(link.getLinkId())
            .setUrl(linkService.getLinkById(link.getLinkId()).getUrl())).toList();
    }

    @Override
    public List<Long> getChatsFromLinkId(long linkId) {
        return repository.findAllChats().stream().filter(track -> track.getLinkId() == linkId)
            .map(Chat::getChatId).toList();
    }

    @Override
    public void create(long chatId) {
        if (!repository.findByChatId(chatId).isEmpty()) {
            throw new UnsupportedOperationException();
        }
//        repository.createUser(chatId);
    }

    @Override
    public void delete(long chatId) {
        repository.removeByChatId(chatId);
    }

    @Override
    public Link track(long chatId, final URI url) {
//        if (!repository.findByChatId(chatId).tracks().isEmpty()) {
//            repository.createUser(chatId);
//        }
        if (!linkService.contains(url)) {
            linkService.add(url);
        }
        repository.add(chatId, linkService.getLinkByUrl(url).getId());
        return new Link().setUrl(url);
    }

    @Override
    public Link untrack(long chatId, URI url) {
        if (repository.findByChatId(chatId).stream()
            .noneMatch(
                track -> linkService.getLinkById(track.getLinkId()).getUrl().equals(url))) {
            throw new UnsupportedOperationException();
        }
        var links = repository.findByChatId(chatId).stream()
            .filter(link -> linkService.getLinkById(link.getLinkId()).getUrl() == url).toList();
        if (!links.isEmpty()) {
            repository.removeByTrackId(links.get(0).getTrackId());
        }
        // remove()
        return new Link().setUrl(url);
    }
}
