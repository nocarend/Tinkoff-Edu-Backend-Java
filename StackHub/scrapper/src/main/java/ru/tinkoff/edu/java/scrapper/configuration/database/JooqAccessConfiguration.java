package ru.tinkoff.edu.java.scrapper.configuration.database;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.repository.jooq.JooqChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jooq.JooqLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.jooq.JooqChatService;
import ru.tinkoff.edu.java.scrapper.service.jooq.JooqLinkService;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jooq")
@RequiredArgsConstructor
public class JooqAccessConfiguration {

    @Bean
    public LinkService linkService(JooqLinkRepository jooqLinkRepository) {
        return new JooqLinkService(jooqLinkRepository);
    }

    @Bean
    public ChatService chatService(JooqChatRepository jooqChatService,
        LinkService jooqLinkService) {
        return new JooqChatService(jooqChatService, jooqLinkService);
    }
}
