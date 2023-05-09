package ru.tinkoff.edu.java.bot.configuration;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.service.dto.LinkUpdate;

/**
 * Message broker's configuration.
 */
@Configuration
@RequiredArgsConstructor
public class RabbitMqConfiguration {

    private final ApplicationConfig applicationConfig;

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange(applicationConfig.exchange() + ".dlx");
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(applicationConfig.exchange());
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.nonDurable(applicationConfig.queue() + ".dlq").build();
    }

    @Bean
    Queue queue() {
        return QueueBuilder.nonDurable(applicationConfig.queue())
            .withArgument("x-dead-letter-exchange", applicationConfig.exchange() + ".dlx")
            .withArgument("x-dead-letter-routing-key", applicationConfig.bind() + ".dlb")
            .build();
    }

    @Bean
    Binding deadLetterBind(Queue deadLetterQueue, DirectExchange deadLetterExchange) {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange)
            .with(applicationConfig.bind() + ".dlb");
    }

    @Bean
    Binding bind(Queue deadLetterQueue, DirectExchange deadLetterExchange) {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange)
            .with(applicationConfig.bind());
    }

    /**
     * Map two identical classes with different names.
     *
     * @return mapper.
     */
    @Bean
    public ClassMapper classMapper() {
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("ru.tinkoff.edu.java.scrapper.service.updater.dto.LinkUpdate",
            LinkUpdate.class);

        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("ru.tinkoff.edu.java.scrapper.service.updater.dto.*");
        classMapper.setIdClassMapping(mappings);
        return classMapper;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
