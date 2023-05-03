package ru.tinkoff.edu.java.bot.service.bot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.service.command.HelpCommand;
import ru.tinkoff.edu.java.bot.telegram.Commands;
import ru.tinkoff.edu.java.bot.telegram.StackHubBot;


@ExtendWith(MockitoExtension.class)
class StackHubBotTest {

    @InjectMocks
    private StackHubBot bot;
    @Mock
    private HelpCommand helpCommand;
    @Mock
    private String token;
    @Mock
    private String name;
    @Mock
    private Commands commands;
    private Update update = new Update();
    private Message message = new Message();

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