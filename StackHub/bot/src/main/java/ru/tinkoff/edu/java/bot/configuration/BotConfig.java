package ru.tinkoff.edu.java.bot.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.tinkoff.edu.java.bot.service.bot.StackHubBot;

@Configuration
@Data
@PropertySource("application.properties")
public class BotConfig {


    @Value("${bot.token}")
    String token;
    @Value("${bot.name}")
    String name;

    @Bean
    BotSession telegramBotsApi() throws TelegramApiException {
        return new TelegramBotsApi(DefaultBotSession.class).registerBot(
            new StackHubBot(token, name));
    }
}
