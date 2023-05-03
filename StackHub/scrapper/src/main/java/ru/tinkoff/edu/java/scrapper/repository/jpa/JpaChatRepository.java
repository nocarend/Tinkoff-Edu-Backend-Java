package ru.tinkoff.edu.java.scrapper.repository.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.repository.ChatRepository;

public interface JpaChatRepository extends JpaRepository<Chat, Long>, ChatRepository {

    @Override
    default void add(long chatId, long linkId) {
        save(new Chat().setChatId(chatId).setLinkId(linkId));
    }

    @Override
    default void removeByTrackId(long trackId) {
        var chat = findById(trackId);
        chat.ifPresent(this::delete);
    }

    @Override
    default void removeByChatId(long chatId) {
        var chats = findAllChats();
        chats.stream().filter(chat -> chat.getChatId() == chatId).forEach(this::delete);
    }

    @Override
    default List<Chat> findAllChats() {
        return findAll();
    }

    @Override
    default List<Chat> findByChatId(long chatId) {
        var chats = findAllChats();
        return chats.stream().filter(chat -> chat.getChatId() == chatId).toList();
    }
}
