package ru.tinkoff.edu.java.scrapper.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.response.client.StackOverflowResponse;

@Getter
@Setter
@Service
public class StackOverflowClient {

    @Qualifier("stackoverflowClient")
    @Autowired
    private WebClient webClient;

    public StackOverflowResponse getStackOverflowResponse(String questionId) {
        return webClient.get()
            .uri("/questions/{id}", questionId)
            .retrieve()
            .bodyToMono(StackOverflowResponse.class)
            .block();
    }
}
