package ru.tinkoff.edu.java.scrapper.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
import ru.tinkoff.edu.java.scrapper.model.Link;

public interface LinkRepository {

    void add(@Param("url") String url, @Param("updated_at") Timestamp updatedAt);

    void remove(String url);

    List<Link> findAllLinks();

    Optional<Link> findById(@Param("id") long id);

    Optional<Link> findByUrl(@Param("url") String url);

    void setNewUpdateTime(@Param("id") long id, @Param("updated_at") Timestamp updatedAt);
}
