package ru.tinkoff.edu.java.bot.service.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

class ListCommandTest {

    private Update update = new Update();
    private Message message = new Message();
    private ListCommand command = new ListCommand();

    {
        message.setText("/list");
    }

    @Test
    void empty_List_Test() {
        Chat chat = new Chat();
        chat.setId(777L);
        message.setChat(chat);
        update.setMessage(message);
        Assertions.assertEquals("Your list is empty.", command.handle(update).getText());
    }

    @Test
    void oneItem_List_Test() {
        Chat chat = new Chat();
        chat.setId(222L);
        message.setChat(chat);
        update.setMessage(message);
        Assertions.assertEquals("1. 1\n", command.handle(update).getText());
    }

    @Test
    void twoItems_List_Test() {
        Chat chat = new Chat();
        chat.setId(1111L);
        message.setChat(chat);
        update.setMessage(message);
        Assertions.assertEquals("1. 1\n"
            + "2. 2\n", command.handle(update).getText());
    }

    @Test
    void threeItems_List_Test() {
        Chat chat = new Chat();
        chat.setId(7777L);
        message.setChat(chat);
        update.setMessage(message);
        Assertions.assertEquals("1. 1\n"
            + "2. 2\n"
            + "3. 3\n", command.handle(update).getText());
    }
}