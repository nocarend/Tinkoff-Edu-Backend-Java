package ru.tinkoff.edu.java.bot.service.bot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.service.command.CommandUtils;

class StackHubBotTest {

    private String token;
    private String name;
    private StackHubBot bot = new StackHubBot(token, name);
    private Update update = new Update();
    private Message message = new Message();

    @Test
    void specialMessage_Test() {
        Chat chat = new Chat();
        chat.setId(904448521L);
        message.setChat(chat);
        message.setText("/Bullshit");
        update.setMessage(message);
        Assertions.assertEquals("Wrong command!", CommandUtils.execute(bot, update));
    }
}