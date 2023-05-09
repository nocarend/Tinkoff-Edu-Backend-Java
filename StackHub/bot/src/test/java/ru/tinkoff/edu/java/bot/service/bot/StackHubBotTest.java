package ru.tinkoff.edu.java.bot.service.bot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.telegram.StackHubBot;


@SpringBootTest
class StackHubBotTest {

    private final Update update = new Update();
    private final Message message = new Message();
    @Autowired
    private StackHubBot bot;

    @Test
    void specialMessage_Test() {
        Chat chat = new Chat();
        chat.setId(904448521L);
        message.setChat(chat);
        message.setText("/Bullshit");
        update.setMessage(message);
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
            () -> bot.onUpdateReceived(update));
    }
}