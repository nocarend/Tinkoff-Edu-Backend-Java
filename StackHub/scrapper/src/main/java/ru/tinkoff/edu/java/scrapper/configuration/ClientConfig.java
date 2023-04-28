package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {

    @Value("${github.base-url}")
    private String gitHubBaseUrl;
    @Value("${stackoverflow.base-url}")
    private String stackOverflowBaseUrl;
    @Value("${bot.base-url}")
    private String botBaseUrl;

    @Bean("githubClient")
    public WebClient getGitHubClient() {
        return WebClient.builder().baseUrl(gitHubBaseUrl).build();
    }

    @Bean("stackoverflowClient")
    public WebClient getStackOverflowClient() {
        return WebClient.builder().baseUrl(stackOverflowBaseUrl).build();
    }

    @Bean("botClient")
    public WebClient getBotClient() {
        return WebClient.builder().baseUrl(botBaseUrl).build();
    }
}
