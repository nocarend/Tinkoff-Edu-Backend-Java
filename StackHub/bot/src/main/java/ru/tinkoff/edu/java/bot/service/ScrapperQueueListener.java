package ru.tinkoff.edu.java.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdateRequest;
import ru.tinkoff.edu.java.scrapper.service.updater.dto.LinkUpdate;

@Service
@RabbitListener(queues = "${app.queue}")
@RequiredArgsConstructor
public class ScrapperQueueListener {

    private final Updater updater;

    @RabbitHandler
    public void receiver(LinkUpdate update) {
        updater.update(
            new LinkUpdateRequest(update.linkId(), update.url(), update.message(), update.chats()));
    }
}