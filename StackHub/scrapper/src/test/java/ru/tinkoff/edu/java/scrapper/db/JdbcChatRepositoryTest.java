package ru.tinkoff.edu.java.scrapper.db;

import java.sql.Timestamp;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.repository.dto.ChatRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.repository.dto.ListChatsRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkRepository;

@SpringBootTest
public class JdbcChatRepositoryTest extends IntegrationEnvironment {

    @Autowired
    private JdbcChatRepository chatRepository;

    @Autowired
    private JdbcLinkRepository linkRepository;

    @Transactional
    @Rollback
    @Test
    void addTest() {
        linkRepository.add("5", new Timestamp(10));
        chatRepository.add(123, 1);
        Assertions.assertFalse(chatRepository.findByChatId(123).isEmpty());
    }

    @Transactional
    @Rollback
    @Test
    void removeTest() {
        linkRepository.add("5", new Timestamp(10));
        chatRepository.add(123, 1);
        chatRepository.removeByTrackId(1);
        Assertions.assertTrue(chatRepository.findByChatId(123).isEmpty());
    }

    @Transactional
    @Rollback
    @Test
    void findAllTest() {
        linkRepository.add("5", new Timestamp(10));
        chatRepository.add(123, 1);
        chatRepository.add(133, 1);
        chatRepository.add(143, 1);

        Assertions.assertEquals(chatRepository.findAllChats(), new ListChatsRepositoryResponse(
            List.of(new ChatRepositoryResponse(1, 123, 1), new ChatRepositoryResponse(2, 133, 1),
                new ChatRepositoryResponse(3, 143, 1))));
    }

    @Transactional
    @Rollback
    @Test
    void findByIdTest() {
        linkRepository.add("5", new Timestamp(10));
        chatRepository.add(123, 1);
        Assertions.assertEquals(chatRepository.findByChatId(123),
            new ListChatsRepositoryResponse(List.of(new ChatRepositoryResponse(1, 123, 1))));
    }
}
