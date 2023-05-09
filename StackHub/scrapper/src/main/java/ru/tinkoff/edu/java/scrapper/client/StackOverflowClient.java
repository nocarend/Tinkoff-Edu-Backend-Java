package ru.tinkoff.edu.java.scrapper.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.response.client.ListStackOverflowAnswerResponse;
import ru.tinkoff.edu.java.scrapper.dto.response.client.ListStackOverflowCommentResponse;
import ru.tinkoff.edu.java.scrapper.dto.response.client.StackOverflowBaseResponse;

/**
 * Create a StackOverflow entity.
 */
@Getter
@Setter
@Service
public class StackOverflowClient {

    private final WebClient webClient;

    public StackOverflowClient(@Qualifier("stackoverflowClient") WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Get data from stackoverflow link.
     *
     * @param questionId stackoverflow question id
     * @return link data
     */
    public StackOverflowBaseResponse getStackOverflowResponse(String questionId) {
        return webClient.get()
            .uri("/questions/{id}", questionId)
            .retrieve()
            .bodyToMono(StackOverflowBaseResponse.class)
            .block();
    }

    public ListStackOverflowAnswerResponse getStackOverflowAnswerResponse(String questionId) {
        return webClient.get()
            .uri("/questions/{id}/answers", questionId)
            .retrieve()
            .bodyToMono(ListStackOverflowAnswerResponse.class)
            .block();
    }

    public ListStackOverflowCommentResponse getStackOverflowCommentResponse(String questionId) {
        return webClient.get()
            .uri("/questions/{id}/comments", questionId)
            .retrieve()
            .bodyToMono(ListStackOverflowCommentResponse.class)
            .block();
    }


}
