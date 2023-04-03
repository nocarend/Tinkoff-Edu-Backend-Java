package ru.tinkoff.edu.java.bot.service.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public class CommandUtils {

    public static final List<Command> COMMANDS = new ArrayList<>(List.of(new StartCommand(),
        new HelpCommand(), new TrackCommand(), new UntrackCommand(), new ListCommand()));

    private CommandUtils() {
        throw new AssertionError();
    }

    public static String commandsToString() {
        String result = COMMANDS.stream().map(CommandUtils::commandToString)
            .collect(Collectors.joining());
        return result;
    }

    public static List<BotCommand> commandsToTelegram() {
        return COMMANDS.stream().map(Command::toApiCommand).toList();
    }

    public static String commandToString(Command command) {
        return command.command() + " - " + command.description() + "\n";
    }

    public static String trimmedString(String string, int commandLength) {
        return String.format("%-20s", string.trim())
            .substring(0, commandLength);
    }

    @SneakyThrows
    public static String execute(TelegramLongPollingBot bot, Update update) {
        for (var command : COMMANDS) {
            if (command.supports(update)) {
                bot.execute(command.handle(update));
                return update.getMessage().getText();
            }
        }
        String errorMessage = "Wrong command!";
        bot.execute(new SendMessage(update.getMessage().getChatId().toString(), errorMessage));
        return errorMessage;
    }
}
