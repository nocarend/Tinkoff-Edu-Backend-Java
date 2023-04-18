package ru.tinkoff.edu.java.scrapper.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private long chatId;
    private List<Link> links;

    public User(long chatId) {
        this.chatId = chatId;
        links = new ArrayList<>();
    }

    public void addLink(Link link) {
        links.add(link);
    }
}
