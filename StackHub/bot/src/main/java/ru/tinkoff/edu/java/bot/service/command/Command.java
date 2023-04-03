package ru.tinkoff.edu.java.bot.service.command;


import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public interface Command {

    WebClient webClient = WebClient.builder().baseUrl("http://localhost:8180").build();

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

    default BotCommand toApiCommand() {
        return new BotCommand(command(), description());
    }

}