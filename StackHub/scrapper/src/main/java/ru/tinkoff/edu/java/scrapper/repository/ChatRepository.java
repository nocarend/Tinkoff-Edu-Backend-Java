package ru.tinkoff.edu.java.scrapper.repository;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.repository.dto.ListChatsRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.repository.mapper.ChatListRowMapper;

@Repository
@RequiredArgsConstructor
public class ChatRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ChatListRowMapper chatListRowMapper;

    @Transactional
    public void add(Chat chat) {
        Map<String, Object> params = new HashMap<>(
            Map.of("chat_id", chat.getChatId(), "link_id", chat.getLinkId()));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        jdbcTemplate.update("insert into chat(chat_id, link_id) values (:chat_id, :link_id)",
            paramSource);
    }

    @Transactional
    public void remove(long trackId) {
        Map<String, Object> params = new HashMap<>(
            Map.of("track_id", trackId));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        jdbcTemplate.update("delete from chat where track_id = :track_id", paramSource);
    }

    public ListChatsRepositoryResponse findAll() {
        return jdbcTemplate.queryForObject("select * from chat", chatListRowMapper);
    }

    public ListChatsRepositoryResponse findByChatId(long chatId) {
        Map<String, Object> params = new HashMap<>(
            Map.of("chat_id", chatId));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        return jdbcTemplate.queryForObject("select * from chat where chat_id = :chat_id",
            new SqlParameterSource[]{paramSource}, chatListRowMapper);
    }
}