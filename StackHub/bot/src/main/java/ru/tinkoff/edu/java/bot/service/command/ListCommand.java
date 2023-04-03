package ru.tinkoff.edu.java.bot.service.command;


import java.util.Objects;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.dto.response.ListLinksResponse;

public class ListCommand implements Command {

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
        System.out.println(update.toString());
        String chatId = message(update).getChatId().toString();
        return new SendMessage(chatId, Objects.requireNonNull(
            webClient.get().uri("/links").header("tgChatId", chatId).retrieve().bodyToMono(
                ListLinksResponse.class).block()).toString());
    }
}
