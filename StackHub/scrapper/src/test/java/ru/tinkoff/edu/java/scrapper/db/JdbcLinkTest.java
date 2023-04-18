package ru.tinkoff.edu.java.scrapper.db;

import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.repository.ChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.LinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.dto.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.repository.dto.LinkRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.repository.dto.ListLinksRepositoryResponse;

@SpringBootTest
public class JdbcLinkTest extends IntegrationEnvironment {

    @Autowired
    private LinkRepository linkRepository;

    {
//        linkRepository.add(new AddLinkRequest(URI.create("aboba"), new Timestamp(10)));
//        linkRepository.add(new AddLinkRequest(URI.create("bebra"), new Timestamp(10)));
//        linkRepository.add(new AddLinkRequest(URI.create("1"), new Timestamp(10)));
//        linkRepository.add(new AddLinkRequest(URI.create("2"), new Timestamp(10)));
//        linkRepository.add(new AddLinkRequest(URI.create("3"), new Timestamp(10)));

    }

    @Transactional
    @Rollback
    @Test
    void addTest() {
        linkRepository.add(new AddLinkRequest(URI.create("added"), new Timestamp(10)));
        Assertions.assertFalse(linkRepository.findByUrl(URI.create("added")).isEmpty());
    }

    @Transactional
    @Rollback
    @Test
    void removeTest() {
        linkRepository.add(new AddLinkRequest(URI.create("added"), new Timestamp(10)));
        linkRepository.remove(URI.create("added"));
        Assertions.assertTrue(linkRepository.findByUrl(URI.create("added")).isEmpty());
    }

    @Transactional
    @Rollback
    @Test
    void findAllTest() {
        linkRepository.add(new AddLinkRequest(URI.create("added1"), new Timestamp(10)));
        linkRepository.add(new AddLinkRequest(URI.create("added2"), new Timestamp(10)));
        linkRepository.add(new AddLinkRequest(URI.create("added3"), new Timestamp(10)));
        Assertions.assertEquals(linkRepository.findAll(),
            new ListLinksRepositoryResponse(new ArrayList<>(
                List.of(new LinkRepositoryResponse(1, URI.create("added1"), new Timestamp(10))))));
    }

    @Transactional
    @Rollback
    @Test
    void findByIdTest() {
        linkRepository.add(new AddLinkRequest(URI.create("added1"), new Timestamp(10)));
        Assertions.assertEquals(linkRepository.findById(1L).get(0),
            new LinkRepositoryResponse(1, URI.create("added1"), new Timestamp(10)));
    }

    @Transactional
    @Rollback
    @Test
    void findByUrl() {
        linkRepository.add(new AddLinkRequest(URI.create("added1"), new Timestamp(10)));
        Assertions.assertEquals(linkRepository.findByUrl(URI.create("added1")).get(0),
            new LinkRepositoryResponse(1, URI.create("added1"), new Timestamp(10)));
    }
}