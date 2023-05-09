package ru.tinkoff.edu.java.bot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.service.dto.LinkUpdate;

@Service
@Log4j2
@RequiredArgsConstructor
public class ScrapperQueueListener {

    private final Updater updater;

    @RabbitListener(queues = "${app.queue}")
    public void receiver(LinkUpdate update) {
        updater.update(
            new LinkUpdateRequest(update.linkId(), update.url(), update.message(), update.chats()));
    }

    @RabbitListener(queues = "${app.queue}.dlq")
    public void processFailedMessages(Message message) {
        log.info("Received failed message: {}", message.toString());
    }
}