package ru.tinkoff.edu.java.scrapper.db;

import java.sql.Timestamp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.ScrapperApplication;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkRepository;

@Transactional
@SpringBootTest(classes = ScrapperApplication.class, properties = {
    "app.database-access-type=jpa"
})
public class JpaLinkRepositoryTest extends IntegrationEnvironment {

    @Autowired
    private JpaLinkRepository linkRepository;


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
        System.out.println(linkRepository.findAllLinks());
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
                    .setUrl(("https://github.com/nocarend/Tinkoff-Edu-Backend-Java"))
                    .setUpdatedAt(new Timestamp(10)),
                result.get(0)),
            () -> Assertions.assertEquals(new Link().setId(result.get(1).getId())
                    .setUrl(("https://github.com/nocarend/OOP"))
                    .setUpdatedAt(new Timestamp(10)),
                result.get(1)),
            () -> Assertions.assertEquals(new Link().setId(result.get(2).getId())
                    .setUrl(("https://github.com/dmitry-irtegov/NSU-Python2023"))
                    .setUpdatedAt(new Timestamp(10)),
                result.get(2)));
    }

    @Test
    void findByIdTest() {
        linkRepository.add("https://github.com/nocarend/OOP", new Timestamp(10));
        var links = linkRepository.findAllLinks();
        var result = linkRepository.findById(Long.valueOf(links.get(0).getId())).get();
        Assertions.assertEquals(result,
            new Link().setId(result.getId()).setUrl(("https://github.com/nocarend/OOP"))
                .setUpdatedAt(new Timestamp(10)));
    }

    //    @Rollback
    @Test
    void findByUrl() {
        linkRepository.add("https://github.com/nocarend/OOP", new Timestamp(10));
        var result = linkRepository.findByUrl("https://github.com/nocarend/OOP").get();
        Assertions.assertEquals(result,
            new Link().setId(result.getId()).setUrl(("https://github.com/nocarend/OOP"))
                .setUpdatedAt(new Timestamp(10)));
    }
}