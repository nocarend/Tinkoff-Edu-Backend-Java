package ru.tinkoff.edu.java.bot.service.command;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {

    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String description() {
        return "Start the bot.";
    }

    @Override
    public SendMessage handle(Update update) {
        String chatId = message(update).getChatId().toString();
        webClient.post().uri("/tg-chat/{chatId}", chatId);
        return new SendMessage(chatId, "Hello there!");
    }
}
