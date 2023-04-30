package ru.tinkoff.edu.java.scrapper.service.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.service.updater.dto.LinkUpdate;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "true")
public class ScrapperQueueProducer implements Producer {

    private final RabbitTemplate rabbitTemplate;
    private final ApplicationConfig applicationConfig;

    public void send(LinkUpdate update) {
        rabbitTemplate.convertAndSend(applicationConfig.exchange(), applicationConfig.bind(),
            update);
    }
}