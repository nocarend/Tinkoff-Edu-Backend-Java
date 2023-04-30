package ru.tinkoff.edu.java.scrapper.service.jdbc;

import java.sql.Timestamp;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.linkparser.ExternalParser;
import ru.tinkoff.edu.java.linkparser.url.Github;
import ru.tinkoff.edu.java.linkparser.url.StackOverflow;
import ru.tinkoff.edu.java.scrapper.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.client.TelegramBotClient;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.LinkUpdater;


@Service
@RequiredArgsConstructor
public class JdbcLinkUpdaterService implements LinkUpdater {

    private final LinkService linkService;
    private final ChatService chatService;

    private final TelegramBotClient telegramBotClient;
    private final GitHubClient gitHubClient;
    private final StackOverflowClient stackOverflowClient;

    @Override
    public int update() {
        var links = linkService.findAll(new Timestamp(System.currentTimeMillis()));
        int numberOfUpdatedLinks = 0;
        for (var link : links) {
            ExternalParser externalParser = new ExternalParser(link.getUrl().toString());
            var value = externalParser.parse();
            switch (value) {
                case Github github -> {
                    if (!gitHubClient.getGitHubResponse(github.username(), github.repositoryName())
                        .updatedAt().toLocalDateTime()
                        .equals(link.getUpdatedAt().toLocalDateTime())) {
                        numberOfUpdatedLinks++;
                        var chats = chatService.getChatsFromLinkId(link.getId());
                        linkService.setCurrentUpdateTime(chats);
                        telegramBotClient.sendUpdate(link.getId(), link.getUrl(),
                            "New updates from Github!",
                            chats);
                    }
                }
                case StackOverflow stackOverflow -> {
                    if (!stackOverflowClient.getStackOverflowResponse(stackOverflow.questionId())
                        .activity().toLocalDateTime()
                        .equals(link.getUpdatedAt().toLocalDateTime())) {
                        numberOfUpdatedLinks++;
                        var chats = chatService.getChatsFromLinkId(link.getId());
                        linkService.setCurrentUpdateTime(chats);
                        telegramBotClient.sendUpdate(link.getId(), link.getUrl(),
                            "New comments and answers from StackOverflow!",
                            chats);
                    }
                }
                default -> throw new NotImplementedException();
            }
        }
        return numberOfUpdatedLinks;
    }
}