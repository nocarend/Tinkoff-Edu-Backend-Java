package ru.tinkoff.edu.java.scrapper.db;

import java.net.URI;
import java.sql.Timestamp;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.repository.ChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.LinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.dto.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.repository.dto.ChatRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.repository.dto.ListChatsRepositoryResponse;

@SpringBootTest
public class JdbcChatTest extends IntegrationEnvironment {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private LinkRepository linkRepository;

    @Transactional
    @Rollback
    @Test
    void addTest() {
        linkRepository.add(new AddLinkRequest(URI.create("5"), new Timestamp(10)));
        chatRepository.add(new Chat().setChatId(123).setLinkId(1));
        Assertions.assertFalse(chatRepository.findByChatId(123).tracks().isEmpty());
    }

    @Transactional
    @Rollback
    @Test
    void removeTest() {
        linkRepository.add(new AddLinkRequest(URI.create("5"), new Timestamp(10)));
        chatRepository.add(new Chat().setChatId(123).setLinkId(1));
        chatRepository.remove(1);
        Assertions.assertTrue(chatRepository.findByChatId(123).tracks().isEmpty());
    }

    @Transactional
    @Rollback
    @Test
    void findAllTest() {
        linkRepository.add(new AddLinkRequest(URI.create("5"), new Timestamp(10)));
        chatRepository.add(new Chat().setChatId(123).setLinkId(1));
        chatRepository.add(new Chat().setChatId(133).setLinkId(1));
        chatRepository.add(new Chat().setChatId(143).setLinkId(1));

        Assertions.assertEquals(chatRepository.findAll(), new ListChatsRepositoryResponse(
            List.of(new ChatRepositoryResponse(1, 123, 1), new ChatRepositoryResponse(2, 133, 1),
                new ChatRepositoryResponse(3, 143, 1))));
    }

    @Transactional
    @Rollback
    @Test
    void findByIdTest() {
        linkRepository.add(new AddLinkRequest(URI.create("5"), new Timestamp(10)));
        chatRepository.add(new Chat().setChatId(123).setLinkId(1));
        Assertions.assertEquals(chatRepository.findByChatId(123),
            new ListChatsRepositoryResponse(List.of(new ChatRepositoryResponse(1, 123, 1))));
    }
}
