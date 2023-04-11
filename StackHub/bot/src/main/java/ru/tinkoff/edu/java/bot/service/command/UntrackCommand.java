package ru.tinkoff.edu.java.bot.service.command;


import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.service.CommandHandlerService;

@RequiredArgsConstructor
@Controller
public class UntrackCommand implements Command {

    private final CommandHandlerService handler;

    @Override
    public String command() {
        return "/untrack";
    }

    @Override
    public String description() {
        return "Stop url tracking.";
    }

    @Override
    public SendMessage handle(Update update) {
        String chatId = message(update).getChatId().toString();
        String text = message(update).getText();
        URI url = URI.create(text.substring(command().length()).trim());
        if (url.toString().isEmpty()) {
            return new SendMessage(chatId, "Link's length must be more than zero.");
        }
        String response = String.valueOf(handler.untrackLink(url, chatId));
        return new SendMessage(chatId,
            response.isEmpty() ? "Link doesn't exist." : "Link successfully deleted.");
    }
}
