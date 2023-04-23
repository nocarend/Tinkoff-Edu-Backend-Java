package ru.tinkoff.edu.java.scrapper.repository.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.repository.ChatRepository;

public interface JpaChatRepository extends JpaRepository<Chat, Long>, ChatRepository {

    @Override
    @Query(value = "delete from chat where chat_id = :chat_id", nativeQuery = true)
    void removeByChatId(@Param("chat_id") long chatId);

    @Override
    @Query(value = "insert into chat(chat_id, link_id) values (:chat_id, :link_id)", nativeQuery = true)
    void add(@Param("chat_id") long chatId, @Param("link_id") long linkId);

    @Override
    @Query(value = "delete from chat where track_id = :track_id", nativeQuery = true)
    void removeByTrackId(@Param("track_id") long trackId);

    @Override
    @Query(value = "select * from chat", nativeQuery = true)
    List<Chat> findAllChats();

    @Override
    @Query(value = "select t from chat t where t.chat_id = :chat_id", nativeQuery = true)
    List<Chat> findByChatId(@Param("chat_id") long chatId);
}
