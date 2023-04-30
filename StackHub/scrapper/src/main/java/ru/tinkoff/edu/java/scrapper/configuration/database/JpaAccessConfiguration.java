package ru.tinkoff.edu.java.scrapper.configuration.database;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaChatService;
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaLinkService;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {

    @Bean
    public LinkService linkService(JpaLinkRepository jpaLinkRepository) {
        return new JpaLinkService(jpaLinkRepository);
    }

    @Bean
    public ChatService chatService(JpaChatRepository jpaChatService,
        LinkService jpaLinkService) {
        return new JpaChatService(jpaChatService, jpaLinkService);
    }
}
