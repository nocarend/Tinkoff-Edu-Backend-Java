package ru.tinkoff.edu.java.bot.service;

import java.net.URI;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.dto.response.CommandResponse;
import ru.tinkoff.edu.java.bot.dto.response.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.response.ListCommandsResponse;
import ru.tinkoff.edu.java.bot.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.bot.service.command.Command;

@RequiredArgsConstructor
@Service
public class CommandHandlerService {

    private final WebClient scrapperClient;

    public LinkResponse trackLink(@NonNull URI url, @NonNull String chatId) {
        return scrapperClient.post()
            .uri("/links")
            .header("tgChatId", chatId)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(new AddLinkRequest(url)), AddLinkRequest.class)
            .retrieve()
            .bodyToMono(LinkResponse.class)
            .block();
    }

    public LinkResponse untrackLink(@NonNull URI url, @NonNull String chatId) {
        var response = scrapperClient.method(HttpMethod.DELETE)
            .uri("/links")
            .header("tgChatId", chatId)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(new RemoveLinkRequest(url)), RemoveLinkRequest.class)
            .retrieve()
            .bodyToMono(LinkResponse.class)
            .block();
        return response;
    }

    public void start(@NonNull String chatId) {
        scrapperClient.post().uri("/tg-chat/{chatId}", chatId);
    }

    public ListLinksResponse list(@NonNull String chatId) {
        return scrapperClient.get().uri("/links").header("tgChatId", chatId)
            .retrieve().bodyToMono(
                ListLinksResponse.class).block();
    }

    public ListCommandsResponse help(List<? extends Command> commands) {
        return new ListCommandsResponse(commands.stream()
            .map(command -> new CommandResponse(command.command(), command.description()))
            .toList());
    }

}
