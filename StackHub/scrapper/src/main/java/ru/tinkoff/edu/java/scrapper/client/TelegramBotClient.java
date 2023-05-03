package ru.tinkoff.edu.java.scrapper.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.dto.request.LinkUpdateRequest;
import ru.tinkoff.edu.java.scrapper.service.updater.dto.LinkUpdate;
@Getter
@Setter
@Service
public class TelegramBotClient {

    private final WebClient webClient;

    public TelegramBotClient(@Qualifier("botClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public void sendUpdate(LinkUpdate update) {
        webClient.post()
            .uri("/updates")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(new LinkUpdateRequest(update.linkId(), update.url(), update.message(),
                    update.chats())),
                LinkUpdateRequest.class)
            .retrieve();
    }
}