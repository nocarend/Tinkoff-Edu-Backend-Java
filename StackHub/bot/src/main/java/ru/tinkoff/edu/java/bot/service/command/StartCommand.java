package ru.tinkoff.edu.java.bot.service.command;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.service.CommandHandlerService;

/**
 * Start command that start a bot and show a message.
 */
@RequiredArgsConstructor
@Controller
public class StartCommand implements Command {

    private final CommandHandlerService handler;

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
        handler.start(chatId);
        return new SendMessage(chatId, "Hello there!");
    }
}
