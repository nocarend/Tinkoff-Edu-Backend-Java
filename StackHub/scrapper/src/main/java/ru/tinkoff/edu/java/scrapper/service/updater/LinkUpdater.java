package ru.tinkoff.edu.java.scrapper.service.updater;

import java.util.List;
import ru.tinkoff.edu.java.scrapper.service.updater.dto.LinkUpdate;

public interface LinkUpdater {
    List<LinkUpdate> update();
}