package ru.tinkoff.edu.java.scrapper.service.jdbc;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.ChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.dto.ChatRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.service.ChatService;

@Service
@RequiredArgsConstructor
public class JdbcChatService implements ChatService {

    private final ChatRepository repository;
    private final JdbcLinkService jdbcLinkService;

    @Override
    public List<Link> getLinksFromChatId(long chatId) {
        var links = repository.findByChatId(chatId).tracks();
        return links.stream().map(link -> new Link().setId(link.linkId())
            .setUrl(jdbcLinkService.getLinkById(link.linkId()).getUrl())).toList();
    }

    @Override
    public List<Long> getChatsFromLinkId(long linkId) {
        return repository.findAll().tracks().stream().filter(track -> track.linkId() == linkId)
            .map(ChatRepositoryResponse::chatId).toList();
    }

    @Override
    public void create(long chatId) {
        if (!repository.findByChatId(chatId).tracks().isEmpty()) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void delete(long chatId) {
        repository.findAll().tracks().removeIf(track -> track.chatId() == chatId);
    }

    @Override
    public Link track(long chatId, final URI url) {
        if (!jdbcLinkService.contains(url)) {
            jdbcLinkService.add(url);
            repository.add(new Chat()
                .setChatId(chatId)
                .setLinkId(jdbcLinkService.getLinkByUrl(url).getId()));
            return new Link().setUrl(url);
        }
        return new Link().setUrl(url);
    }

    @Override
    public Link untrack(long chatId, URI url) {
        if (repository.findByChatId(chatId).tracks().stream()
            .noneMatch(
                track -> jdbcLinkService.getLinkById(track.linkId()).getUrl().equals(url))) {
            throw new UnsupportedOperationException();
        }
        repository.findByChatId(chatId).tracks()
            .removeIf(
                track -> jdbcLinkService.getLinkById(track.linkId()).getUrl().equals(url));
        return new Link().setUrl(url);
    }
}
