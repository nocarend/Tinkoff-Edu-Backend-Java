package ru.tinkoff.edu.java.scrapper.service;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.dto.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.repository.ChatRepository;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository repository;
    private final LinkService linkService;

    public ListLinksResponse getLinksFromChatId(long chatId) {
        var links = repository.findByChatId(chatId).tracks();
        return new ListLinksResponse(
            links.stream().map(
                link -> new LinkResponse(link.linkId(), linkService.getByLinkId(link.linkId()).url()
                )).toList(), links.size());
    }

    public void create(long chatId) {
        if (!repository.findByChatId(chatId).tracks().isEmpty()) {
            throw new UnsupportedOperationException();
        }
//        repository.createUser(chatId);
    }

    public void delete(long chatId) {
        repository.findAll().tracks().removeIf(track -> track.chatId() == chatId);
    }

    public LinkResponse track(long chatId, final URI url) {
//        if (!repository.findByChatId(chatId).tracks().isEmpty()) {
//            repository.createUser(chatId);
//        }
        if (!linkService.contains(url)) {
            linkService.add(url);
            repository.add(new Chat()
                .setChatId(chatId)
                .setLinkId(linkService.getByUrl(url).id()));
            return new LinkResponse(url.hashCode(), url);
        }
        var urlResponse = URI.create("");
        return new LinkResponse(url.hashCode(), urlResponse);
    }

    public LinkResponse untrack(long chatId, URI url) {
        if (repository.findByChatId(chatId).tracks().stream()
            .noneMatch(track -> linkService.getByLinkId(track.linkId()).url().equals(url))) {
            throw new UnsupportedOperationException();
        }
        repository.findByChatId(chatId).tracks()
            .removeIf(track -> linkService.getByLinkId(track.linkId()).url().equals(url));
        return new LinkResponse(url.hashCode(), url);
    }
}
