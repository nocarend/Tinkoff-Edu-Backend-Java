package ru.tinkoff.edu.java.bot.telegram;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.tinkoff.edu.java.bot.service.command.HelpCommand;

@Service
@Getter
@Setter
public class StackHubBot extends TelegramLongPollingBot {

    private final Commands commands;
    private final HelpCommand helpCommand;
    private String token;
    private String name;

    @SneakyThrows
    public StackHubBot(@Qualifier("botToken") String token, @Qualifier("botName") String name,
        Commands commands, HelpCommand helpCommand) {
        this.token = token;
        this.name = name;
        this.commands = commands;
        this.helpCommand = helpCommand;
        new TelegramBotsApi(DefaultBotSession.class).registerBot(this);
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return name;
    }


    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (helpCommand.supports(update)) {
            execute(helpCommand.handle(update));
            return;
        }
        for (var command : commands.toList()) {
            if (command.supports(update)) {
                execute(command.handle(update));
                return;
            }
        }
        String errorMessage = "Wrong command!";
        execute(new SendMessage(update.getMessage().getChatId().toString(), errorMessage));
        throw new IllegalArgumentException();
    }
}
