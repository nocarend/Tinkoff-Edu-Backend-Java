package ru.tinkoff.edu.java.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.telegram.StackHubBot;

@Service
@RequiredArgsConstructor
public class Updater {

    private final StackHubBot bot;

    public void update(LinkUpdateRequest updateRequest) {
        updateRequest.tgChatIds().forEach(id -> {
            try {
                bot.execute(new SendMessage(id.toString(), updateRequest.description()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        });
    }
}
