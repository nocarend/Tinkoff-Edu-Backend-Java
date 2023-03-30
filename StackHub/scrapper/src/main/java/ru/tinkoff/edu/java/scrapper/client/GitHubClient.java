package ru.tinkoff.edu.java.scrapper.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.response.client.GitHubResponse;

@Getter
@Service
@Setter
public class GitHubClient {

    private final WebClient webClient;

    public GitHubClient(@Qualifier("githubClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public GitHubResponse getGitHubResponse(String username, String repositoryName) {
        return webClient.get()
            .uri("/repos/{username}/{repositoryName}/hooks", username, repositoryName)
            .retrieve()
            .bodyToMono(GitHubResponse.class)
            .block();
    }
}
