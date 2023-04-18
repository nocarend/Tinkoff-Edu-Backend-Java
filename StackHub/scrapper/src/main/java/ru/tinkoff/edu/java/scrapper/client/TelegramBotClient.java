package ru.tinkoff.edu.java.scrapper.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.response.client.GitHubResponse;

@Service
@Getter
@Setter
public class TelegramBotClient {

    private final WebClient webClient;

    public TelegramBotClient(@Qualifier("botClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public void sendUpdate() {
        webClient.post()
            .uri("/updates")
            .retrieve()
            .bodyToMono(GitHubResponse.class)
            .block();
    }
}
