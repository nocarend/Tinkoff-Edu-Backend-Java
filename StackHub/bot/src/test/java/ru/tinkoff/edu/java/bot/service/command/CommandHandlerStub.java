package ru.tinkoff.edu.java.bot.service.command;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import ru.tinkoff.edu.java.bot.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.bot.service.CommandHandlerService;
import ru.tinkoff.edu.java.scrapper.dto.response.LinkResponse;

public class CommandHandlerStub extends CommandHandlerService {

    private final Map<String, List<String>> map = new HashMap<>(
        Map.of("77777", new ArrayList<>(List.of("123", "321", "777"))));

    public CommandHandlerStub() {
        super(null);
    }

    @Override
    public ListLinksResponse list(@NonNull String chatId) {
        var links = map.get(chatId).stream().map(url -> new LinkResponse(-1, URI.create(url)))
            .toList();
        return new ListLinksResponse(links, links.size());
    }
}
