package ru.tinkoff.edu.java.scrapper.repository;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;

@Repository
public class ChatRepository {

    private final List<Chat> chatList;

    public ChatRepository() {
        List<Chat> chats = new ArrayList<>();
        chats.add(createUser(7777, new ArrayList<>(
            List.of(new Link(URI.create("1")), new Link(URI.create("2")),
                new Link(URI.create("3"))))));
        chats.add(createUser(1111,
            new ArrayList<>(List.of(new Link(URI.create("1")), new Link(URI.create("2"))))));
        chats.add(createUser(222, new ArrayList<>(List.of(new Link(URI.create("1"))))));
        chats.add(createUser(333, new ArrayList<>()));
        chats.add(createUser(444, new ArrayList<>()));
        this.chatList = chats;
    }

    public Chat createUser(long chatId, List<Link> links) {
        return new Chat(chatId, links);
    }

    public Chat createUser(long chatId) {
        if (chatList.stream().anyMatch(chat -> chat.getId() == chatId)) {
            throw new IllegalArgumentException();
        }
        Chat chat = new Chat(chatId);
        chatList.add(chat);
        return chat;
    }

    public List<Chat> getAllUsers() {
        return chatList;
    }

    public List<Link> getFromChatId(long chatId) {
        for (var user : chatList) {
            if (user.getId() == chatId) {
                return user.getLinks();
            }
        }
        return new ArrayList<>();
    }

    public boolean contains(long chatId) {
        return chatList.stream().anyMatch(chat -> chat.getId() == chatId);
    }

    public boolean containsLink(long chatId, URI url) {
        return chatList.stream().filter(chat -> chat.getId() == chatId).findFirst()
            .filter(chat -> chat.getLinks().stream().anyMatch(link -> link.getUrl().equals(url)))
            .isPresent();
    }
}