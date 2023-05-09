package ru.tinkoff.edu.java.scrapper.service.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.service.updater.dto.LinkUpdate;

@Service
@RequiredArgsConstructor
@Log4j2
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "true")
public class ScrapperQueueProducer implements Producer {

    private final RabbitTemplate rabbitTemplate;
    private final ApplicationConfig applicationConfig;

    public void send(LinkUpdate update) {
        log.info("Message sent " + update);
        rabbitTemplate.convertAndSend(applicationConfig.exchange(), applicationConfig.bind(),
            update);
    }
}