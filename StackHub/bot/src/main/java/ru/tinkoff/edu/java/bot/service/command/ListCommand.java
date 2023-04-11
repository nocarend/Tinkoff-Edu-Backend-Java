package ru.tinkoff.edu.java.bot.service.command;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.service.CommandHandlerService;

@RequiredArgsConstructor
@Controller
public class ListCommand implements Command {

    private final CommandHandlerService handler;

    @Override
    public String command() {
        return "/list";
    }

    @Override
    public String description() {
        return "Show tracked links.";
    }

    @Override
    public SendMessage handle(Update update) {
        String chatId = message(update).getChatId().toString();
        var response = handler.list(chatId);
        if (response == null || response.size() == 0) {
            return new SendMessage(chatId, "Your list is empty.");
        }
        return new SendMessage(chatId, handler.list(chatId).toString());
    }
}
