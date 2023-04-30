package ru.tinkoff.edu.java.scrapper.db;

import java.net.URI;
import java.sql.Timestamp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.ScrapperApplication;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkRepository;

@Transactional
@SpringBootTest(classes = ScrapperApplication.class, properties = {
    "app.database-access-type=jdbc"
})
public class JdbcLinkRepositoryTest extends IntegrationEnvironment {

    @Autowired
    private JdbcLinkRepository linkRepository;


    @Test
    void addTest() {
        linkRepository.add("https://github.com/nocarend/Tinkoff-Edu-Backend-Java",
            new Timestamp(10));
        Assertions.assertFalse(
            linkRepository.findByUrl("https://github.com/nocarend/Tinkoff-Edu-Backend-Java")
                .isEmpty());
    }

    @Test
    void removeTest() {
        linkRepository.add("https://github.com/nocarend/Tinkoff-Edu-Backend-Java",
            new Timestamp(10));
        linkRepository.remove("https://github.com/nocarend/Tinkoff-Edu-Backend-Java");
        Assertions.assertTrue(
            linkRepository.findByUrl("https://github.com/nocarend/Tinkoff-Edu-Backend-Java")
                .isEmpty());
    }

    @Test
    void findAllTest() {
        linkRepository.add("https://github.com/nocarend/Tinkoff-Edu-Backend-Java",
            new Timestamp(10));
        linkRepository.add("https://github.com/nocarend/OOP", new Timestamp(10));
        linkRepository.add("https://github.com/dmitry-irtegov/NSU-Python2023", new Timestamp(10));
        var result = linkRepository.findAllLinks();
        Assertions.assertAll(
            () -> Assertions.assertEquals(new Link().setId(result.get(0).getId())
                    .setUrl(URI.create("https://github.com/nocarend/Tinkoff-Edu-Backend-Java"))
                    .setUpdatedAt(new Timestamp(10)),
                result.get(0)),
            () -> Assertions.assertEquals(new Link().setId(result.get(1).getId())
                    .setUrl(URI.create("https://github.com/nocarend/OOP"))
                    .setUpdatedAt(new Timestamp(10)),
                result.get(1)),
            () -> Assertions.assertEquals(new Link().setId(result.get(2).getId())
                    .setUrl(URI.create("https://github.com/dmitry-irtegov/NSU-Python2023"))
                    .setUpdatedAt(new Timestamp(10)),
                result.get(2)));
    }

    @Test
    void findByIdTest() {
        linkRepository.add("https://github.com/nocarend/OOP", new Timestamp(10));
        var links = linkRepository.findAllLinks();
        var result = linkRepository.findById(links.get(0).getId()).get();
        Assertions.assertEquals(result,
            new Link().setId(result.getId()).setUrl(URI.create("https://github.com/nocarend/OOP"))
                .setUpdatedAt(new Timestamp(10)));
    }

    @Test
    void findByUrl() {
        linkRepository.add("https://github.com/nocarend/OOP", new Timestamp(10));
        var result = linkRepository.findByUrl("https://github.com/nocarend/OOP").get();
        Assertions.assertEquals(result,
            new Link().setId(result.getId()).setUrl(URI.create("https://github.com/nocarend/OOP"))
                .setUpdatedAt(new Timestamp(10)));
    }
}