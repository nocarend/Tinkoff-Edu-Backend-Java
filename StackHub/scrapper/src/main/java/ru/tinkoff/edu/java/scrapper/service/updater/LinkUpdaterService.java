package ru.tinkoff.edu.java.scrapper.service.updater;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.linkparser.ExternalParser;
import ru.tinkoff.edu.java.linkparser.url.Github;
import ru.tinkoff.edu.java.linkparser.url.StackOverflow;
import ru.tinkoff.edu.java.scrapper.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.updater.dto.LinkUpdate;


@Service
@RequiredArgsConstructor
public class LinkUpdaterService implements LinkUpdater {

    private final LinkService linkService;
    private final ChatService chatService;

    private final GitHubClient gitHubClient;
    private final StackOverflowClient stackOverflowClient;

    @Override
    public List<LinkUpdate> update() {
        var links = linkService.findAll(new Timestamp(System.currentTimeMillis()));
        List<LinkUpdate> updates = new ArrayList<>();
        for (var link : links) {
            ExternalParser externalParser = new ExternalParser(link.getUrl().toString());
            var value = externalParser.parse();
            switch (value) {
                case Github github -> {
                    if (!gitHubClient.getGitHubResponse(github.username(), github.repositoryName())
                        .updatedAt().toLocalDateTime()
                        .equals(link.getUpdatedAt().toLocalDateTime())) {
                        var chats = chatService.getChatsFromLinkId(link.getId());
                        updates.add(new LinkUpdate(link.getId(), link.getUrl().toString(),
                            "New updates from Github", chats));
                        linkService.setCurrentUpdateTime(chats);
//                        telegramBotClient.sendUpdate(link.getId(), link.getUrl(),
//                            "New updates from Github!",
//                            chats);
                    }
                }
                case StackOverflow stackOverflow -> {
                    if (!stackOverflowClient.getStackOverflowResponse(stackOverflow.questionId())
                        .activity().toLocalDateTime()
                        .equals(link.getUpdatedAt().toLocalDateTime())) {
                        var chats = chatService.getChatsFromLinkId(link.getId());
                        updates.add(new LinkUpdate(link.getId(), link.getUrl().toString(),
                            "New updates from Github", chats));
                        linkService.setCurrentUpdateTime(chats);
//                        telegramBotClient.sendUpdate(link.getId(), link.getUrl(),
//                            "New comments and answers from StackOverflow!",
//                            chats);
                    }
                }
                default -> throw new NotImplementedException();
            }
        }
        return updates;
    }
}
