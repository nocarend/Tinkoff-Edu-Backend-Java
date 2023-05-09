package ru.tinkoff.edu.java.bot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.telegram.StackHubBot;

@Service
@RequiredArgsConstructor
@Log4j2
public class Updater {

    private final StackHubBot bot;

    public void update(LinkUpdateRequest updateRequest) {
        log.info("Message taken for updating " + updateRequest);
        updateRequest.tgChatIds().forEach(id -> {
            try {
                bot.execute(new SendMessage(id.toString(), updateRequest.description()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        });
    }
}
