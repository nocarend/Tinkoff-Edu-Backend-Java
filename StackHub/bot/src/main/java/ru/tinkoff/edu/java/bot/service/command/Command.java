package ru.tinkoff.edu.java.bot.service.command;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {

    String command();

    String description();

    SendMessage handle(Update update);

    default Message message(Update update) {
        return update.getMessage();
    }

    default boolean supports(Update update) {
        return CommandUtils.trimmedString(message(update).getText(), command().length())
            .equals(command());
    }

}