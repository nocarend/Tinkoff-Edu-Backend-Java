package ru.tinkoff.edu.java.bot.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Data
public class BotConfig {


    @Value("${bot.token}")
    String token;
    @Value("${bot.name}")
    String name;
    @Value("${bot.scrapper.url}")
    String scrapperUrl;

    @Bean("botToken")
    public String botToken() {
        return token;
    }

    @Bean("botName")
    public String botName() {
        return name;
    }

    @Bean
    public WebClient scrapperClient() {
        return WebClient.builder().baseUrl(scrapperUrl).build();
    }
}
