package ru.tinkoff.edu.java.scrapper.db;

import java.sql.Timestamp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.ScrapperApplication;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkRepository;

@SpringBootTest(classes = ScrapperApplication.class, properties = {
    "app.database-access-type=jdbc"
})
@Transactional
public class JdbcChatRepositoryTest extends IntegrationEnvironment {

    @Autowired
    private JdbcChatRepository chatRepository;

    @Autowired
    private JdbcLinkRepository linkRepository;

    @Test
    void addTest() {
        linkRepository.add("https://github.com/nocarend/Tinkoff-Edu-Backend-Java",
            new Timestamp(10));
        var linkId = linkRepository.findAllLinks().get(0).getId();
        chatRepository.add(123, linkId);
        Assertions.assertFalse(chatRepository.findByChatId(123).isEmpty());
    }

    @Test
    void removeTest() {
        linkRepository.add("https://github.com/nocarend/Tinkoff-Edu-Backend-Java",
            new Timestamp(10));
        var linkId = linkRepository.findByUrl(
            "https://github.com/nocarend/Tinkoff-Edu-Backend-Java").get().getId();
        chatRepository.add(123, linkId);
        var trackId = chatRepository.findAllChats().get(0).getTrackId();
        chatRepository.removeByTrackId(trackId);
        Assertions.assertTrue(chatRepository.findByChatId(123).isEmpty());
    }

    @Test
    void findAllTest() {
        linkRepository.add("https://github.com/nocarend/Tinkoff-Edu-Backend-Java",
            new Timestamp(10));
        var id = linkRepository.findAllLinks().get(0).getId();
        chatRepository.add(123, id);
        chatRepository.add(133, id);
        chatRepository.add(143, id);
        var result = chatRepository.findAllChats();
        System.out.println(result);
        Assertions.assertAll(
            () -> Assertions.assertEquals(
                new Chat().setTrackId(result.get(0).getTrackId()).setChatId(123)
                    .setLinkId(id), result.get(0)),
            () -> Assertions.assertEquals(
                new Chat().setTrackId(result.get(1).getTrackId()).setChatId(133)
                    .setLinkId(id), result.get(1)),
            () -> Assertions.assertEquals(
                new Chat().setTrackId(result.get(2).getTrackId()).setChatId(143)
                    .setLinkId(id), result.get(2)));
    }

    @Test
    void findByIdTest() {
        linkRepository.add("https://github.com/nocarend/Tinkoff-Edu-Backend-Java",
            new Timestamp(10));
        var linkId = linkRepository.findByUrl(
            "https://github.com/nocarend/Tinkoff-Edu-Backend-Java").get().getId();
        chatRepository.add(123, linkId);
        var trackId = chatRepository.findAllChats().get(0).getTrackId();
        Assertions.assertEquals(chatRepository.findByChatId(123).get(0),
            new Chat().setChatId(123).setTrackId(trackId).setLinkId(linkId));
    }
}
