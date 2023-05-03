package ru.tinkoff.edu.java.scrapper.service.jpa;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

@RequiredArgsConstructor
public class JpaChatService implements ChatService {

    private final JpaChatRepository repository;
    private final LinkService linkService;

    @Transactional(readOnly = true)
    @Override
    public List<Link> getLinksFromChatId(long chatId) {
        var links = repository.findByChatId(chatId);
        return links.stream().map(link -> new Link().setId(link.getLinkId())
            .setUrl(linkService.getLinkById(link.getLinkId()).getUrl())).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public void create(long chatId) {
        if (!repository.findByChatId(chatId).isEmpty()) {
            throw new UnsupportedOperationException();
        }
    }

    @Transactional
    @Override
    public void delete(long chatId) {
        repository.findByChatId(chatId).forEach(track -> repository.deleteById(
            track.getTrackId()));
    }

    @Transactional
    @Override
    public Link track(long chatId, URI url) {
        if (!linkService.contains(url)) {
            linkService.add(url);
        }
        repository.add(chatId, linkService.getLinkByUrl(url).getId());
        return new Link().setUrl(url.toString());
    }

    @Transactional
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

    @Transactional
    @Override
    public List<Long> getChatsFromLinkId(long linkId) {
        return repository.findAllChats().stream().filter(track -> track.getLinkId() == linkId)
            .map(Chat::getChatId).toList();
    }
}
