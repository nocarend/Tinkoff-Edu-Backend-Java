package ru.tinkoff.edu.java.bot.service.command;


import java.net.URI;
import java.util.Objects;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.dto.response.LinkResponse;

public class UntrackCommand implements Command {

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
        String response = Objects.requireNonNull(webClient.method(HttpMethod.DELETE)
            .uri("/links")
            .header("tgChatId", chatId)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(new RemoveLinkRequest(url)), RemoveLinkRequest.class)
            .retrieve()
            .bodyToMono(LinkResponse.class)
            .block()).toString();
        if (response.isEmpty()) {
            return new SendMessage(chatId, "Link doesn't exist.");
        }
        return new SendMessage(chatId, "Link successfully deleted.");
    }
}
