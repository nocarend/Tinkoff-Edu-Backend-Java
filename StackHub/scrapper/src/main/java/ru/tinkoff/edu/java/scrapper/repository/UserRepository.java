package ru.tinkoff.edu.java.scrapper.repository;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.model.User;

@Repository
public class UserRepository {

    private final List<User> userList;

    public UserRepository() {
        List<User> users = new ArrayList<>();
        users.add(createUser(7777, new ArrayList<>(
            List.of(new Link(URI.create("1")), new Link(URI.create("2")),
                new Link(URI.create("3"))))));
        users.add(createUser(1111,
            new ArrayList<>(List.of(new Link(URI.create("1")), new Link(URI.create("2"))))));
        users.add(createUser(222, new ArrayList<>(List.of(new Link(URI.create("1"))))));
        users.add(createUser(333, new ArrayList<>()));
        users.add(createUser(444, new ArrayList<>()));
        this.userList = users;
    }

    public User createUser(long chatId, List<Link> links) {
        return new User(chatId, links);
    }

    public User createUser(long chatId) {
        if (userList.stream().anyMatch(user -> user.getChatId() == chatId)) {
            throw new IllegalArgumentException();
        }
        User user = new User(chatId);
        userList.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public List<Link> getFromChatId(long chatId) {
        for (var user : userList) {
            if (user.getChatId() == chatId) {
                return user.getLinks();
            }
        }
        return new ArrayList<>();
    }

    public boolean contains(long chatId) {
        return userList.stream().anyMatch(user -> user.getChatId() == chatId);
    }

    public boolean containsLink(long chatId, URI url) {
        return userList.stream().filter(user -> user.getChatId() == chatId).findFirst()
            .filter(user -> user.getLinks().stream().anyMatch(link -> link.getUrl().equals(url)))
            .isPresent();
    }
}