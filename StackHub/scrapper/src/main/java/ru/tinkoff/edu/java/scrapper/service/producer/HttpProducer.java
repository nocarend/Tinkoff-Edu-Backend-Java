package ru.tinkoff.edu.java.scrapper.service.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.client.TelegramBotClient;
import ru.tinkoff.edu.java.scrapper.service.updater.dto.LinkUpdate;

@Service
@RequiredArgsConstructor
@Log4j2
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "false")
public class HttpProducer implements Producer {

    private final TelegramBotClient botClient;

    @Override
    public void send(LinkUpdate update) {
        log.info("Message sent " + update);
        botClient.sendUpdate(update);
    }
}
