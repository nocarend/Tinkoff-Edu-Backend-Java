package ru.tinkoff.edu.java.scrapper.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Chat {

    private long id;
    private List<Link> links;

    public Chat(long chatId) {
        this.id = chatId;
        links = new ArrayList<>();
    }

    public void addLink(Link link) {
        links.add(link);
    }
}
