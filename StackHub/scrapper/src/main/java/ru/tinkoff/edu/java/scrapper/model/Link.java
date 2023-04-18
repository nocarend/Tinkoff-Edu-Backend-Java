package ru.tinkoff.edu.java.scrapper.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Link {

    private long id;

    private URI url;

    private List<User> users;

    public Link(URI url) {
        id = url.hashCode();
        this.url = url;
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

}
