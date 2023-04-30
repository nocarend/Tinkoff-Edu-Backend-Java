package ru.tinkoff.edu.java.scrapper.repository.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.repository.ChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.mapper.ChatRowMapper;

@Repository
@RequiredArgsConstructor
public class JdbcChatRepository implements ChatRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ChatRowMapper chatRowMapper;

    @Override
    public void removeByChatId(long chatId) {
        Map<String, Object> params = new HashMap<>(
            Map.of("chat_id", chatId));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        jdbcTemplate.update("delete from chat where chat_id = :chat_id", paramSource);
    }

    @Transactional
    public void add(long chatId, long linkId) {
        Map<String, Object> params = new HashMap<>(
            Map.of("chat_id", chatId, "link_id", linkId));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        jdbcTemplate.update("insert into chat(chat_id, link_id) values (:chat_id, :link_id)",
            paramSource);
    }

    @Transactional
    public void removeByTrackId(long trackId) {
        Map<String, Object> params = new HashMap<>(
            Map.of("track_id", trackId));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        jdbcTemplate.update("delete from chat where track_id = :track_id", paramSource);
    }

    @Transactional(readOnly = true)
    public List<Chat> findAllChats() {
        return jdbcTemplate.query("select * from chat", new HashMap<>(),
            chatRowMapper);
    }

    @Transactional(readOnly = true)
    public List<Chat> findByChatId(long chatId) {
        Map<String, Object> params = new HashMap<>(
            Map.of("chat_id", chatId));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        return jdbcTemplate.query("select * from chat where chat_id = :chat_id",
            paramSource, chatRowMapper);
    }
}