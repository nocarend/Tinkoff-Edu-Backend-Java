package ru.tinkoff.edu.java.bot.service.command;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.service.CommandHandlerService;
import ru.tinkoff.edu.java.bot.telegram.Commands;

@RequiredArgsConstructor
@Controller
public class HelpCommand implements Command {

    private final CommandHandlerService handler;
    private final Commands commands;

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
        return new SendMessage(chatId, handler.help(commands.toList()).toString());
    }
}
