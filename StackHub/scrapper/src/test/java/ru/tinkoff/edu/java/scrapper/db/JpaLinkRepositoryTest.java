package ru.tinkoff.edu.java.scrapper.db;

import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.repository.dto.LinkRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.repository.dto.ListLinksRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkRepository;

@SpringBootTest
public class JpaLinkRepositoryTest extends IntegrationEnvironment {

    @Autowired
    private JpaLinkRepository linkRepository;


    @Transactional
    @Rollback
    @Test
    void addTest() {
        linkRepository.add("added", new Timestamp(10));
        Assertions.assertFalse(linkRepository.findByUrl("added").isEmpty());
    }

    @Transactional
    @Rollback
    @Test
    void removeTest() {
        linkRepository.add("added", new Timestamp(10));
        linkRepository.remove("added");
        Assertions.assertTrue(linkRepository.findByUrl("added").isEmpty());
    }

    @Transactional
    @Rollback
    @Test
    void findAllTest() {
        linkRepository.add("added1", new Timestamp(10));
        linkRepository.add("added2", new Timestamp(10));
        linkRepository.add("added3", new Timestamp(10));
        Assertions.assertEquals(linkRepository.findAllLinks(),
            new ListLinksRepositoryResponse(new ArrayList<>(
                List.of(new LinkRepositoryResponse(1, URI.create("added1"), new Timestamp(10))))));
    }

    @Transactional
    @Rollback
    @Test
    void findByIdTest() {
        linkRepository.add("added1", new Timestamp(10));
        Assertions.assertEquals(linkRepository.findById(1L),
            new LinkRepositoryResponse(1, URI.create("added1"), new Timestamp(10)));
    }

    @Transactional
    @Rollback
    @Test
    void findByUrl() {
        linkRepository.add("added1", new Timestamp(10));
        Assertions.assertEquals(linkRepository.findByUrl("added1"),
            new LinkRepositoryResponse(1, URI.create("added1"), new Timestamp(10)));
    }
}