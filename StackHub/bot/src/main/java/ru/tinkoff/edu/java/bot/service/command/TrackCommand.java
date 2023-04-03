package ru.tinkoff.edu.java.bot.service.command;


import java.net.URI;
import java.util.Objects;
import org.springframework.http.MediaType;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.dto.response.LinkResponse;

public class TrackCommand implements Command {

    @Override
    public String command() {
        return "/track";
    }

    @Override
    public String description() {
        return "Start url tracking. Example: /track https://github.com/sanyarnd/tinkoff-java-course-2022/";
    }

    @Override
    public SendMessage handle(Update update) {
        String chatId = message(update).getChatId().toString();
        String text = message(update).getText();
        URI url = URI.create(text.substring(command().length()).trim());
        if (url.toString().isEmpty()) {
            return new SendMessage(chatId, "Link's length must be more than zero.");
        }
        String response = Objects.requireNonNull(webClient.post()
            .uri("/links")
            .header("tgChatId", chatId)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(new AddLinkRequest(url)), AddLinkRequest.class)
            .retrieve()
            .bodyToMono(LinkResponse.class)
            .block()).toString();
        if (response.isEmpty()) {
            return new SendMessage(chatId, "Link has been already added.");
        }
        return new SendMessage(chatId, "Link successfully added");
    }
}
