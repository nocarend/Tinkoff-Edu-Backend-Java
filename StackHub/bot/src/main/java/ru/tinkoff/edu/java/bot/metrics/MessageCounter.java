package ru.tinkoff.edu.java.bot.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

public class MessageCounter {

    private final Counter counter;

    public MessageCounter(MeterRegistry registry) {
        counter = Counter.builder("message_counter")
            .register(registry);
    }

    public void increment() {
        counter.increment();
    }
}
