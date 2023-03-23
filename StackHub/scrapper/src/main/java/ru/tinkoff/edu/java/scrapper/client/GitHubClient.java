package ru.tinkoff.edu.java.scrapper.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.response.client.GitHubResponse;

@Getter
@Service
@Setter
public class GitHubClient {

    @Qualifier("githubClient")
    @Autowired
    private WebClient webClient;

    public GitHubResponse getGitHubResponse(String username, String repositoryName) {
        return webClient.get()
            .uri("/repos/{username}/{repositoryName}", username, repositoryName)
            .retrieve()
            .bodyToMono(GitHubResponse.class)
            .block();
    }
}
