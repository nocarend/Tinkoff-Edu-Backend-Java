package ru.tinkoff.edu.java.scrapper.service;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.dto.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public ListLinksResponse getLinksFromChatId(long chatId) {
        List<Link> links = repository.getFromChatId(chatId);
        return new ListLinksResponse(links.stream().map(link -> new LinkResponse(link.getId(),
            link.getUrl())).toList(), links.size());
    }

    public void create(long chatId) {
        if (repository.contains(chatId)) {
            throw new UnsupportedOperationException();
        }
        repository.createUser(chatId);
    }

    public void delete(long chatId) {
        repository.getAllUsers().removeIf(user -> user.getChatId() == chatId);
    }

    public LinkResponse addLink(long chatId, URI url) {
        if (!repository.contains(chatId)) {
            repository.createUser(chatId);
        }
        if (!repository.containsLink(chatId, url)) {
            repository.getFromChatId(chatId).add(new Link(url));
            return new LinkResponse(url.hashCode(), url);
        }
        url = URI.create("");
        return new LinkResponse(url.hashCode(), url);
    }

    public LinkResponse deleteLink(long chatId, URI url) {
        if (!repository.containsLink(chatId, url)) {
            throw new UnsupportedOperationException();
        }
        repository.getFromChatId(chatId).removeIf(link -> link.getUrl().equals(url));
        return new LinkResponse(url.hashCode(), url);
    }
}
