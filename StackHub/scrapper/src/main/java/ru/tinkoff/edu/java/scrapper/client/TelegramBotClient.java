package ru.tinkoff.edu.java.scrapper.client;

import java.net.URI;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.dto.request.LinkUpdateRequest;

@Getter
@Setter
@Service
public class TelegramBotClient {

    private final WebClient webClient;

    public TelegramBotClient(@Qualifier("botClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public void sendUpdate(long linkId, URI url, String description, List<Long> tgChatIds) {
        webClient.post()
            .uri("/updates")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(new LinkUpdateRequest(linkId, url.toString(), description, tgChatIds)),
                LinkUpdateRequest.class)
            .retrieve();
    }
}