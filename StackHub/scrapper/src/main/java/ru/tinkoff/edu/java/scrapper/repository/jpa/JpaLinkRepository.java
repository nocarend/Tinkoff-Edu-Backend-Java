package ru.tinkoff.edu.java.scrapper.repository.jpa;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.edu.java.linkparser.ExternalParser;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.LinkRepository;

public interface JpaLinkRepository extends JpaRepository<Link, Long>, LinkRepository {

    @Override
    default void add(String url, Timestamp updatedAt) {
        var result = new ExternalParser(url).parse();
        if (result == null) {
            throw new IllegalArgumentException();
        }
        if (findByUrl(url).isPresent()) {
            return;
        }
        save(new Link().setUrl(url).setUpdatedAt(updatedAt));
    }

    @Override
    default void remove(String url) {
        var link = findByUrl(url);
        link.ifPresent(this::delete);
    }

    @Override
    default List<Link> findAllLinks() {
        return findAll();
    }

    @Override
    default Optional<Link> findByUrl(String url) {
        var links = findAll();
        return links.stream().filter(link -> Objects.equals(link.getUrl().toString(), url))
            .findFirst();
    }

    @Override
    default void setNewUpdateTime(long id, Timestamp updatedAt) {
        var link = findById(Long.valueOf(id));
        link.ifPresent(value -> value.setUpdatedAt(updatedAt));
    }
}