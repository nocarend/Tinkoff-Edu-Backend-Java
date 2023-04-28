package ru.tinkoff.edu.java.scrapper.repository.jpa;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.LinkRepository;

public interface JpaLinkRepository extends JpaRepository<Link, Long>, LinkRepository {

    @Modifying
    @Query(value = "insert into link(url, updated_at) values (:url, :updated_at)", nativeQuery = true)
    void add(@Param("url") String url, @Param("updated_at") Timestamp updatedAt);

    @Modifying
    @Query(value = "delete from link where url = :url", nativeQuery = true)
    void remove(@Param("url") String url);

    @Modifying
    @Query(value = "select * from link", nativeQuery = true)
    List<Link> findAllLinks();

    @Modifying
    @Query(value = "select t from link t where t.id = :id", nativeQuery = true)
    Optional<Link> findById(@Param("id") long id);

    @Modifying
    @Query(value = "select t from link t where t.url = :url", nativeQuery = true)
    Optional<Link> findByUrl(@Param("url") String url);

    @Modifying
    @Query(value = "update link set updated_at = :updated_at where id = :id", nativeQuery = true)
    void setNewUpdateTime(@Param("id") long id, @Param("updated_at") Timestamp updatedAt);
}
