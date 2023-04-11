package ru.tinkoff.edu.java.bot.telegram;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.command.Command;
import ru.tinkoff.edu.java.bot.service.command.ListCommand;
import ru.tinkoff.edu.java.bot.service.command.StartCommand;
import ru.tinkoff.edu.java.bot.service.command.TrackCommand;
import ru.tinkoff.edu.java.bot.service.command.UntrackCommand;

@Component
@RequiredArgsConstructor
public class Commands {

    private final ListCommand list;
    private final StartCommand start;
    private final TrackCommand track;
    private final UntrackCommand untrack;

    public List<? extends Command> toList() {
        return new ArrayList<>(List.of(list, start, track, untrack));
    }
}
