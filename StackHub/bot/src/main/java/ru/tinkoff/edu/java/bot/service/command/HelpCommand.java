package ru.tinkoff.edu.java.bot.service.command;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HelpCommand implements Command {

    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String description() {
        return "Show all commands presented in the bot.";
    }

    @Override
    public SendMessage handle(Update update) {
        String chatId = message(update).getChatId().toString();
        return new SendMessage(chatId, CommandUtils.commandsToString());
    }
}
