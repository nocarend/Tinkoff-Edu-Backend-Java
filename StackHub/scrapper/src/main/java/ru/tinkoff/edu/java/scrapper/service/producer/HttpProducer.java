package ru.tinkoff.edu.java.scrapper.service.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.client.TelegramBotClient;
import ru.tinkoff.edu.java.scrapper.service.updater.dto.LinkUpdate;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "false")
public class HttpProducer implements Producer {

    private final TelegramBotClient botClient;

    @Override
    public void send(LinkUpdate update) {
        botClient.sendUpdate(update);
    }
}
