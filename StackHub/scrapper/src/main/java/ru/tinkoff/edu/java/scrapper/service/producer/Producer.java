package ru.tinkoff.edu.java.scrapper.service.producer;

import ru.tinkoff.edu.java.scrapper.service.updater.dto.LinkUpdate;

public interface Producer {

    void send(LinkUpdate update);
}
