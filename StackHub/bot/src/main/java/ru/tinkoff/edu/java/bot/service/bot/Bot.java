package ru.tinkoff.edu.java.bot.service.bot;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Bot extends AutoCloseable {
    @Override
    void close();
}