package ru.tinkoff.edu.java.bot.service.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.service.command.CommandUtils;

public class StackHubBot extends TelegramLongPollingBot implements Bot {


    private final String token;
    private final String name;

    public StackHubBot(String token, String name) {
        this.token = token;
        this.name = name;
    }

    @Override
    public void close() {
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public void onUpdateReceived(Update update) {
        CommandUtils.execute(this, update);
    }
}
