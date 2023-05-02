package ru.tinkoff.edu.java.bot.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMqConfiguration {

    private final ApplicationConfig applicationConfig;

    @Bean
    Queue messagesQueue() {
        return QueueBuilder.nonDurable(applicationConfig.queue())
            .withArgument("x-dead-letter-exchange", applicationConfig.exchange() + ".dlq")
            .build();
    }

    @Bean
    public Queue queue() {
        return new Queue(applicationConfig.queue());
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(applicationConfig.exchange());
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(applicationConfig.bind());
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue(applicationConfig.queue() + ".dlq");
    }

    @Bean
    public DirectExchange deadLetterDirectExchange() {
        return new DirectExchange(applicationConfig.exchange() + ".dlq");
    }

    @Bean
    public Binding deadLetterBinding(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange)
            .with(applicationConfig.bind() + ".dlq");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
