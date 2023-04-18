package ru.tinkoff.edu.java.bot.service.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.service.CommandHandlerService;
import ru.tinkoff.edu.java.bot.telegram.Commands;

@ExtendWith(MockitoExtension.class)
class ListCommandTest {

    private Update update = new Update();
    private Message message = new Message();
    @InjectMocks
    private ListCommand listCommand;
    @Mock
    private CommandHandlerService commandHandlerService;
    @Mock
    private Commands commands;

    private CommandHandlerStub commandHandlerStub = new CommandHandlerStub();

    {
        message.setText("/list");
    }

    @Test
    void empty_List_Test() {
        Chat chat = new Chat();
        chat.setId(777L);
        message.setChat(chat);
        update.setMessage(message);
        Assertions.assertEquals("Your list is empty.", listCommand.handle(update).getText());
    }

    @Test
    void threeItems_List_Test() {
        Chat chat = new Chat();
        chat.setId(77777L);
        message.setChat(chat);
        update.setMessage(message);
        Assertions.assertEquals("1. 123\n"
            + "2. 321\n"
            + "3. 777\n", commandHandlerStub.list(chat.getId().toString()).toString());
    }
}